package com.shadow.technology_integration.controller;

import com.shadow.technology_integration.domain.common.PageQuery;
import com.shadow.technology_integration.domain.common.PageResult;
import com.shadow.technology_integration.domain.common.ResponseResult;
import com.shadow.technology_integration.domain.dto.UserDTO;
import com.shadow.technology_integration.domain.dto.UserExcelExportDTO;
import com.shadow.technology_integration.domain.dto.UserQueryDTO;
import com.shadow.technology_integration.domain.nums.ResponseCodeEnum;
import com.shadow.technology_integration.domain.vo.UserVO;
import com.shadow.technology_integration.service.ExcelService;
import com.shadow.technology_integration.service.UserService;
import com.shadow.technology_integration.util.InsertValidationGroup;
import com.shadow.technology_integration.util.UpdateValidationGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shadow
 * @date 2020/4/15
 */
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExcelService excelService;

    @PostMapping("")
    @CacheEvict(cacheNames = "users-cache", allEntries = true)
    public ResponseResult save(@Validated(InsertValidationGroup.class) @RequestBody UserDTO userDTO) {
        int insertCount = userService.save(userDTO);
        if (insertCount == 0) {
            return ResponseResult.failure(ResponseCodeEnum.INSERT_FAILURE);
        }
        return ResponseResult.success();
    }

    @PutMapping("/{id}")
    public ResponseResult update(@PathVariable Long id, @Validated(UpdateValidationGroup.class) @RequestBody UserDTO userDTO) {
        int updateCount = userService.update(id, userDTO);
        if (updateCount == 0) {
            return ResponseResult.failure(ResponseCodeEnum.UPDATE_FAILURE);
        }
        return ResponseResult.success();
    }

    @GetMapping("/list")
    @Cacheable(cacheNames = "users-cache")
    public ResponseResult<PageResult<List<UserVO>>> listByQuery(@RequestParam("page") Integer page,
                                                                @RequestParam("size") Integer size,
                                                                @Validated UserQueryDTO query) {
        log.info("没有走缓存");
        // 构造查询条件
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setPageNo(page);
        pageQuery.setPageSize(size);
        pageQuery.setQuery(query);

        // 查询
        PageResult<List<UserDTO>> pageResult = userService.listByQuery(pageQuery);

        // 实体转换
        List<UserVO> userVOList = Optional
                .ofNullable(pageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDTO -> {
                    UserVO userVO = new UserVO();

                    BeanUtils.copyProperties(userDTO, userVO);
                    // 对特殊字段做处理
                    userVO.setPassword("******");
                    if (!StringUtils.isEmpty(userDTO.getPhone())) {
                        userVO.setPhone(userDTO.getPhone()
                                .replaceAll("(\\d{3})\\d{4}(\\d{4})"
                                        , "$1****$2"));
                    }

                    return userVO;
                })
                .collect(Collectors.toList());

        // 封装返回结果
        PageResult<List<UserVO>> result = new PageResult<>();
        BeanUtils.copyProperties(pageResult, result);
        result.setData(userVOList);
        return ResponseResult.success(result);
    }

    @GetMapping("/list2")
    @Cacheable(cacheNames = "users2-cache")
    public ResponseResult<PageResult<List<UserVO>>> listByQuery2(@RequestParam("page") Integer page,
                                                                 @RequestParam("size") Integer size,
                                                                 @Validated UserQueryDTO query) {
        log.info("没有走缓存");
        // 构造查询条件
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setPageNo(page);
        pageQuery.setPageSize(size);
        pageQuery.setQuery(query);

        // 查询
        PageResult<List<UserDTO>> pageResult = userService.listByQuery(pageQuery);

        // 实体转换
        List<UserVO> userVOList = Optional
                .ofNullable(pageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDTO -> {
                    UserVO userVO = new UserVO();

                    BeanUtils.copyProperties(userDTO, userVO);
                    // 对特殊字段做处理
                    userVO.setPassword("******");
                    if (!StringUtils.isEmpty(userDTO.getPhone())) {
                        userVO.setPhone(userDTO.getPhone()
                                .replaceAll("(\\d{3})\\d{4}(\\d{4})"
                                        , "$1****$2"));
                    }

                    return userVO;
                })
                .collect(Collectors.toList());

        // 封装返回结果
        PageResult<List<UserVO>> result = new PageResult<>();
        BeanUtils.copyProperties(pageResult, result);
        result.setData(userVOList);
        return ResponseResult.success(result);
    }

    @GetMapping("/export")
    public ResponseResult<Boolean> export(@Validated UserQueryDTO userQueryDTO,
                                          @NotEmpty String filename) {
        // 数据导出
//        excelService.export(userQueryDTO,filename);
        excelService.asyncExport(userQueryDTO,filename);
        return ResponseResult.success(Boolean.TRUE);
    }

}
