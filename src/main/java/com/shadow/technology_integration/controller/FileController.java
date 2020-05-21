package com.shadow.technology_integration.controller;

import com.shadow.technology_integration.domain.common.ResponseResult;
import com.shadow.technology_integration.domain.nums.ResponseCodeEnum;
import com.shadow.technology_integration.exception.BusinessException;
import com.shadow.technology_integration.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * 类描述：文件上传与下载Controller
 *
 * @author shadow
 * @date 2020/5/6
 */
@RestController
@RequestMapping("/api/files")
@Slf4j
public class FileController {

    @Resource(name = "localFileService")
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult<String> uploadFile(@NotNull MultipartFile file) {
        try {
            fileService.uploadFile(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            log.error("文件上传失败");
            throw new BusinessException(ResponseCodeEnum.FILE_UPLOAD_FAILURE, e);
        }
        return ResponseResult.success(file.getOriginalFilename());
    }
}
