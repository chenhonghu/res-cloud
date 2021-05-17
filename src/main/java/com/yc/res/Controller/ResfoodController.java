package com.yc.res.Controller;

import com.yc.res.bean.Resfood;
import com.yc.res.bean.Resorder;
import com.yc.res.bean.Resuser;
import com.yc.res.biz.impl.ResfoodBizImpl;
import com.yc.res.biz.impl.ResorderBIzImpl;
import com.yc.res.vo.CartItem;
import com.yc.res.vo.JsonModel;
import com.yc.res.vo.YcConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.cert.Extension;
import java.util.List;
import java.util.Map;

import static com.yc.res.vo.YcConstants.RESFOODLIST;

/**
 * @program: res_mvc_boot
 * @description:菜品的查询
 * @author: hgdd
 * @create: 2021-04-29 21:19
 */
@RestController
@Slf4j
@Transactional
public class ResfoodController {
@Autowired
    ResorderBIzImpl resorderBIz;
    @Autowired
   private ResfoodBizImpl resfoodBizimpl;
    @RequestMapping(value = "comfirmOrder",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonModel confirmOrder(HttpSession session, Resorder resorder,JsonModel jm){
        System.out.println(resorder.getAddress());
        System.out.println(resorder.getDeliveryTime());
        if(session.getAttribute(YcConstants.LOGINUSER)==null){
            jm.setCode(0);
            jm.setMsg("user has not been logined....");
            return jm;
        }
        Resuser resuser = (Resuser) session.getAttribute(YcConstants.LOGINUSER);
        resorder.setUserid(resuser.getUserid());
        if(session.getAttribute(YcConstants.CART)==null|| ((Map<Integer,CartItem>)session.getAttribute(YcConstants.CART)).size()<=0){
            jm.setCode(0);
            jm.setMsg("you have not buy any thing....");
            return jm;
        }
        Map<Integer , CartItem> cart= (Map<Integer, CartItem>) session.getAttribute(YcConstants.CART);
       try {
           resorderBIz.completeOrder(resorder, cart);
           session.removeAttribute(YcConstants.CART);
           jm.setCode(1);
       }catch (Exception e){
           jm.setCode(0);
           jm.setMsg(e.getMessage());
       }
        return jm;
    }
    @GetMapping("/foodsfindAll")
    public JsonModel foodsFinaAll(HttpSession session, JsonModel jsonModel){
        //获取所有菜品信息存到list里
      List<Resfood> list=resfoodBizimpl.findAll();
      //存到session里 键RESFOODLIST 内容 list
        // 用RESFOODLIST 代替字符串作为键 是为了便于统一管理键 键声明在YcConstants
      session.setAttribute(RESFOODLIST,list);
      //jsonModel 以json格式存储
      jsonModel.setCode(1);
      jsonModel.setObj(list);
      return jsonModel;
    }
    @RequestMapping(value = "/findfood" ,method = {RequestMethod.GET,RequestMethod.POST})
    public JsonModel findFood(HttpSession session,Resfood food,JsonModel jm){
        List<Resfood>list=null;
        //RESFOODLIST session的键
        //如果session里 这个RESFOODLIST键对应的内容不为空，则将其内容复制到list里
        if(session.getAttribute(RESFOODLIST)!=null){
            list=(List<Resfood>) session.getAttribute(RESFOODLIST);
        }else{//为空的话则重新从数据库查询所有的菜品并将其保存在RESFOODLIST键对应的值内
            list=resfoodBizimpl.findAll();
            session.setAttribute(RESFOODLIST,list);
        }
        //将list的所有菜品迭代，选出根据fid对应的菜品
        for(Resfood r :list){
            if(food.getFid()==r.getFid()){
                jm.setCode(1);
                jm.setObj(r);
                return jm;
            }
        }
    jm.setCode(0);
        return jm;
}
}
