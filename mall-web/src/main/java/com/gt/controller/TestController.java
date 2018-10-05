package com.gt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2018/9/6.
 */


@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/filesUpload")
    //requestParam要写才知道是前台的那个数组
    public String filesUpload(@RequestParam("myfiles") MultipartFile[] files,
                              HttpServletRequest request,@RequestParam Map<String,Object> map) throws Exception{
        List<String> list = new ArrayList<String>();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                if(file.getInputStream().available() > 1000 * 800){
                    return "";
                }
                list = saveFile(request, file, list);
            }
        }
        //写着测试，删了就可以
        for (int i = 0; i < list.size(); i++) {
            System.out.println("集合里面的数据" + list.get(i));
        }
        log.info("");
        return "";//跳转的页面
    }

    private List<String> saveFile(HttpServletRequest request,
                                  MultipartFile file, List<String> list) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
                // )
                String originalFileNameLeft = file.getOriginalFilename();
                // 新的图片名称
                String newPicName = UUID.randomUUID()
                    + originalFileNameLeft
                    .substring(originalFileNameLeft
                        .lastIndexOf("."));
                System.out.println("1:"+request.getSession().getServletContext()
                    .getRealPath("/"));
                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/" + newPicName;
                filePath = filePath.substring(0,filePath.indexOf("target"))+"src\\main\\webapp\\upload\\"+ newPicName;

                System.out.println(filePath);
                    list.add(file.getOriginalFilename());
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();

                // 转存文件
                file.transferTo(saveDir);
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }



}
