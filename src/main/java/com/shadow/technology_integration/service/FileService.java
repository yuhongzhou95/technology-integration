package com.shadow.technology_integration.service;

import java.io.File;
import java.io.InputStream;

/**
 * 类描述：文件的上传与下载Service
 *
 * @author shadow
 * @date 2020/5/6
 */
public interface FileService {

    /**
     * 上传文件
     * @param inputStream 输入流
     * @param filename 文件名
     */
    void uploadFile(InputStream inputStream,String filename);

    /**
     * 上传文件
     * @param file
     */
    void uploadFile(File file);
}
