package com.shadow.technology_integration.service.impl;

import com.shadow.technology_integration.domain.nums.ResponseCodeEnum;
import com.shadow.technology_integration.exception.BusinessException;
import com.shadow.technology_integration.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 类描述：本地文件上传与下载
 *
 * @author shadow
 * @date 2020/5/6
 */
@Service("localFileService")
@Slf4j
public class LocalFileServiceImpl implements FileService {

    /**
     * 存储空间
     */
    private static final String BUCKET = "D:\\test";

    @Override
    public void uploadFile(InputStream inputStream, String filename) {

        // 拼接文件的存储路径
        String storagePath = BUCKET + "\\" + filename;

        try (
                // JDK8 TWR 不能关闭外部资源的
                InputStream innerInputStream = inputStream;
                FileOutputStream outputStream = new FileOutputStream(new File(storagePath))
        ) {
            // 拷贝缓冲区
            byte[] buffer = new byte[1025];
            // 读取文件流长度
            int len = 0;
            // 循环读取inputStream中数据写入到outputStream
            while ((len = innerInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            // 冲刷流
            outputStream.flush();
        } catch (Exception e) {
            log.info("上传文件失败", e);
            throw new BusinessException(ResponseCodeEnum.FILE_UPLOAD_FAILURE, e);
        }
    }

    @Override
    public void uploadFile(File file) {
        try {
            uploadFile(new FileInputStream(file), file.getName());
        } catch (Exception e) {
            log.info("文件上传失败", e);
            throw new BusinessException(ResponseCodeEnum.FILE_UPLOAD_FAILURE, e);
        }
    }
}
