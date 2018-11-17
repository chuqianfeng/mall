package com.gt.controller;


import com.gt.entity.Picture;
import com.gt.service.IPictureService;
import com.gt.utils.CommonUtil;
import com.gt.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private IPictureService pictureService;


    @Autowired
    private FastDFSClientWrapper dfsClient;

    @RequestMapping("/fdfs_upload")
    public void fdfsUpload(@RequestParam("file") MultipartFile[] files,
                             HttpServletRequest request,@RequestParam Map<String,Object> map)  throws Exception {
        ServerResponse sp = ServerResponse.createByFail();
        try {
            Double price = 0.0;

            List<String> list = new ArrayList<String>();
            if(CommonUtil.isNotEmpty(map.get("price"))){
                price = CommonUtil.toDouble(map.get("price"));
            }
            if (files != null && files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    MultipartFile file = files[i];
                    // 保存文件
                    String fileUrl = dfsClient.uploadFile(file);
                    System.out.println("&&&&&&&&&&"+fileUrl);
                    Picture picture = new Picture();
                    picture.setCreatTime(new Date());
                    picture.setPrice(price);
                    picture.setPicName(list.get(i));
                    picture.setStatus(1);
                    sp = pictureService.insertPic(picture);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
