package com.shadow.technology_integration.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.shadow.technology_integration.domain.common.PageQuery;
import com.shadow.technology_integration.domain.common.PageResult;
import com.shadow.technology_integration.domain.dto.UserDTO;
import com.shadow.technology_integration.domain.dto.UserExcelExportDTO;
import com.shadow.technology_integration.domain.dto.UserQueryDTO;
import com.shadow.technology_integration.service.ExcelService;
import com.shadow.technology_integration.service.FileService;
import com.shadow.technology_integration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类描述：
 *
 * @author shadow
 * @date 2020/5/11
 */
@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {

    @Resource(name = "localFileService")
    private FileService fileService;

    @Autowired
    private UserService userService;

    /**
     * 执行数据库查询和excel导出，将数据写入到outputStream中
     *
     * @param outputStream
     * @param userQueryDTO
     */
    private void export(OutputStream outputStream, UserQueryDTO userQueryDTO) {
        // step1 需要创建一个EasyExcel导出对象
        ExcelWriter excelWriter = EasyExcelFactory.write(outputStream, UserExcelExportDTO.class).build();
        // step2 分批加载数据
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setQuery(userQueryDTO);
        //
        pageQuery.setPageSize(4);
        int pageNo = 0;
        PageResult<List<UserDTO>> pageResult;

        do {
            pageQuery.setPageNo(++pageNo);
            log.info("开始导出第{}页的数据",pageNo);
            pageResult = userService.listByQuery(pageQuery);

            // 数据转换：UserDTO -> UserExcelExportDTO
            List<UserExcelExportDTO> list = Optional.ofNullable(pageResult.getData())
                    .map(List::stream)
                    .orElseGet(Stream::empty)
                    .map(userDTO -> {
                        UserExcelExportDTO userExcelExportDTO = new UserExcelExportDTO();
                        BeanUtils.copyProperties(userDTO, userExcelExportDTO);
                        return userExcelExportDTO;
                    }).collect(Collectors.toList());

            // step3 导出分批加载的数据
            // 将数据写入到不同的sheet页中
            WriteSheet writeSheet = EasyExcelFactory.writerSheet(pageNo, String.format("第%d页", pageNo)).build();
            excelWriter.write(list,writeSheet);
            log.info("结束导出第{}页的数据",pageNo);

            // 总页数大于当前页 说明还有数据 需要再次执行
        } while (pageResult.getTotalPage() > pageNo);
        // step4 收尾，执行finish，才会关闭Excel文件流
        excelWriter.finish();
        log.info("导出成功");
    }


    @Override
    public void export(UserQueryDTO userQueryDTO, String filename) {
        // 输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 实现数据导出Excel中
        export(byteArrayOutputStream, userQueryDTO);
        // 输入流
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        // 实现文件上传
        fileService.uploadFile(byteArrayInputStream, filename);
    }

    /**
     * 借助@Async注解，使用线程池执行方法
     * @param query
     * @param filename
     */
    @Async("exportServiceExecutor")
    @Override
    public void asyncExport(UserQueryDTO query, String filename) {
        export(query, filename);
    }
}
