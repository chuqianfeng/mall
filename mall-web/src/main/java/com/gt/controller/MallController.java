package com.gt.controller;

import com.alibaba.fastjson.JSONObject;
import com.gt.entity.Member;
import com.gt.service.IMemberService;
import com.gt.utils.CommonUtil;
import com.gt.utils.HttpClientUtil;
import com.gt.utils.RemoteUrl;
import com.gt.utils.ServerResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Administrator on 2018/9/13.
 */
@Controller
@RequestMapping("/mall")
public class MallController {
    private Logger logger = Logger.getLogger(MallController.class);//记录日志

    @Autowired
    private IMemberService memberService;

    /**
     *
     * @param request
     * @param response
     * @param code
     * @throws IOException
     */
    @RequestMapping("/18IS2018/getOpenid")
    public void getOpenid(HttpServletRequest request, HttpServletResponse response, @RequestParam String code) throws IOException {
        ServerResponse sp = ServerResponse.createByFail();
        String openid = "";
        try {
            if(CommonUtil.isEmpty(code)){
                sp = ServerResponse.createByFail("缺少必要参数");
                CommonUtil.write(response,sp);
            }else {
                String AppID = "wx5825b338b09ffa6b";
                String AppSecret = "6d390ad7efca692e5369cd3c356b110b";
                String url = RemoteUrl.GETOPENID+"?appid="+AppID+"&secret="+AppSecret+"&js_code="+code+"&grant_type=authorization_code";
                String msg = HttpClientUtil.httpGet(url, null);
                JSONObject jsonObject = JSONObject.parseObject(msg);
                if(CommonUtil.isNotEmpty(jsonObject.get("openid"))){
                    openid = jsonObject.getString("openid");
                    sp = ServerResponse.createBySuccss("操作成功！",openid);
                }
                System.out.println(msg);
                CommonUtil.write(response,sp);
            }

        }catch (Exception e){
            e.printStackTrace();
            CommonUtil.write(response,sp);
        }
    }

    /**
     * 插入粉丝 信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/18IS2018/insertMemberInfo")
    public void insertMemberInfo(HttpServletRequest request, HttpServletResponse response,  Member member) throws IOException{
        ServerResponse sp = ServerResponse.createByFail();
        try{
            System.out.println(member);
            logger.error("进入/18IS2018/insertMemberInfo，参数为："+JSONObject.toJSONString(member));
            sp = memberService.insertMemberInfor(member);


        }catch (Exception e){
            e.printStackTrace();
            CommonUtil.write(response,sp);
        }

    }
    /**
     * 查询粉丝信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/18IS2018/queryByOpenid")
    public void queryByOpenid(HttpServletRequest request, HttpServletResponse response,  String openid) throws IOException{
        ServerResponse sp = ServerResponse.createByFail();
        try{
            if(CommonUtil.isEmpty(openid)){
                sp = ServerResponse.createByFail("缺少参数!!");
                CommonUtil.write(response,sp);
            }else {
                Member member = memberService.queryByOpenid(openid);
                sp = ServerResponse.createBySuccss(member);
                CommonUtil.write(response,sp);
            }
        }catch (Exception e){
            e.printStackTrace();
            CommonUtil.write(response,sp);
        }

    }



}