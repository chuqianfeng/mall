package com.gt.service;

import com.gt.entity.Picture;
import com.gt.utils.ServerResponse;

import java.util.List;

/**
 * Created by Administrator on 2018/10/5.
 */
public interface IPictureService {

    public ServerResponse insertPic(Picture picture);

    public List<Picture> queryPicList(Integer claId);

    public Picture getPicById(Integer picId);

    public  ServerResponse updateByPicId(Integer picId);

}
