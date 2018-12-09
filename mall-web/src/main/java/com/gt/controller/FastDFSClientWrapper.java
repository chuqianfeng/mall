package com.gt.controller;

import java.io.IOException;
import java.io.InputStream;

import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.gt.config.FdfsConfig;
import com.gt.utils.CommonUtil;
import com.gt.utils.ServerResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

/**
 * 工具类
 */
@Component
public class FastDFSClientWrapper {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private FdfsConfig fdfsConfig;

    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile((InputStream)file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
        return getResAccessUrl(storePath);
    }

    /**
     * 封装文件完整URL地址
     * @param storePath
     * @return
     */
    private String getResAccessUrl(StorePath storePath) {
//        String fileUrl =  fdfsConfig.getResHost() + ":" + fdfsConfig.getStoragePort() + "/" + storePath.getFullPath();
        String fileUrl =   "/" + storePath.getFullPath();
        return fileUrl;
    }

    /**
     * 删除文件
     * @param fileUrl 文件访问地址
     * @return
     */
    public ServerResponse deleteFile(String fileUrl) {
        ServerResponse sp = ServerResponse.createByFail();
        if (CommonUtil.isEmpty(fileUrl)) {
            sp = ServerResponse.createByFail("图片地址为空");
            return sp;
        }
        StorePath storePath = StorePath.praseFromUrl(fileUrl);
        storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        sp = ServerResponse.createBySuccss();
        return sp;
    }
}
