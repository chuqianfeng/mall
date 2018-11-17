package com.gt.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.dao.mapper.PictureDAO;
import com.gt.entity.Picture;
import com.gt.service.IPictureService;
import com.gt.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/10/5.
 */
@Service
public class PictureServiceImpl implements IPictureService{

    @Autowired
    private PictureDAO pictureDAO;

    @Override
    public ServerResponse insertPic(Picture picture) {
        ServerResponse sp = ServerResponse.createByFail();
        Integer index = pictureDAO.insert(picture);
        if(index> 0){
            sp = ServerResponse.createBySuccss();
        }
        return sp;
    }

    @Override
    public List<Picture> queryPicList() {

        Wrapper<Picture> wrapper = new EntityWrapper<>();
        wrapper.eq("status",1);
        wrapper.orderBy("id", true);
        List<Picture> list = pictureDAO.selectList(wrapper);
        return list;
    }
}