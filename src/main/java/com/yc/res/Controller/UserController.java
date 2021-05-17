package com.yc.res.Controller;

import com.yc.res.bean.Resuser;
import com.yc.res.biz.ResuserBiz;
import com.yc.res.vo.JsonModel;

import com.yc.res.vo.YcConstants;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-04-28 20:40
 */
@RestController
@Slf4j
@Api(value = "账户操作接口", tags = {"账户操作接口", "控制层"})
public class UserController {
@Autowired
    private ResuserBiz biz;
@Autowired
private RedisTemplate<String,Object> redisTemplate;
@RequestMapping(value = "/loginout",method = {RequestMethod.GET,RequestMethod.POST})
public JsonModel loginout(JsonModel jsonModel, HttpSession session){
    //移除登录对应的键值对
   // session.removeAttribute(YcConstants.LOGINUSER);
    redisTemplate.delete("LOGINUSER");
    jsonModel.setCode(1);
    return jsonModel;
}
@RequestMapping(value="/checklogin",method = {RequestMethod.GET,RequestMethod.POST})
public JsonModel checkLogin(JsonModel jm,HttpSession session){
    //判断登录LOGINUSER是否为空 为空则返回。。 不为空则返回登录者数据
    //if(session.getAttribute(YcConstants.LOGINUSER)==null){
      if(redisTemplate.opsForValue().get("LOGINUSER")==null){
        jm.setCode(0);
        jm.setMsg("用户未登录");
    }else {
        jm.setCode(1);
       //Resuser user=(Resuser)session.getAttribute(YcConstants.LOGINUSER);
       Resuser user= (Resuser) redisTemplate.opsForValue().get("LOGINUSER");
       jm.setObj(user);
    }
    return jm;
}
@RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonModel login(HttpSession session,JsonModel jm,String valcode,String username,String pwd){
    //获取前端传输过来的数据 验证
    //验证验证码是否为空
    if(valcode==null||"".equals(valcode)){
        jm.setCode(0);
        jm.setMsg("验证码不能为空");
        return jm;
    }
    //获取valcodeController 存取到session的验证码

    String validateCode=(String) session.getAttribute("validateCode");
    //判断是否一致
    if(!valcode.equalsIgnoreCase(validateCode)){
        jm.setCode(0);
        jm.setMsg("验证错误");
        return jm;
    }
    Resuser ru=new Resuser();
    ru.setUsername(username);
    ru.setPwd(pwd);
    Resuser rs=biz.login(ru);
    if(rs!=null){
        //session.setAttribute(YcConstants.LOGINUSER,rs);
        redisTemplate.opsForValue().set("LOGINUSER",rs);
        jm.setCode(1);
        //if(session.getAttribute(YcConstants.LOGINUSER)!=null){

          if(redisTemplate.opsForValue().get("LOGINUSER")!=null){
              jm.setUrl((String) session.getAttribute(YcConstants.LASTVISITFEDADDR));
        }else{
            jm.setUrl((String) session.getAttribute(YcConstants.HOMEPAGE));
        }
    }else{
        jm.setCode(0);
        jm.setMsg("用户名或密码错误 请重试");
    }
    return jm;
}

}
