package com.yc.res.Controller;

import com.yc.res.bean.Resfood;
import com.yc.res.biz.ResfoodBiz;
import com.yc.res.biz.impl.ResorderBIzImpl;
import com.yc.res.vo.CartItem;
import com.yc.res.vo.JsonModel;
import com.yc.res.vo.YcConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static com.yc.res.vo.YcConstants.RESFOODLIST;

/**
 * @program: res_mvc_boot
 * @description:购物车
 * 购物车中添加菜品到cart和将cart传输到前端分开写
 * 1.灵活
 * 方便清空时的单独操作，灵活性强
 * @author: hgdd
 * @create: 2021-05-04 16:47
 */
@RestController

public class ResorderController {

    @Autowired
    ResfoodBiz resfoodBiz;
    @Autowired
    ResorderBIzImpl resorderBIz;

    @RequestMapping(value = "/orderJson", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel orderJsonOp(HttpSession session, JsonModel jm, HttpServletRequest req) {
        //req 从前端传输过来的数据
        commonOrder(req, session);
        jm.setCode(1);
        return jm;
    }

    private void commonOrder(HttpServletRequest req, HttpSession session) {
        //获取从前端传输过来的数据
        int fid = Integer.parseInt(req.getParameter("fid"));
        int num = Integer.parseInt(req.getParameter("num"));
        List<Resfood> list = null;
        //判断session内是否有所有菜品的数据
        //有则直接复制
        if (session.getAttribute(RESFOODLIST) != null) {
            list = (List<Resfood>) session.getAttribute(RESFOODLIST);
        } else {//没有则重新查，重新存到session里面
            list = resfoodBiz.findAll();
            session.setAttribute(RESFOODLIST, list);
        }
        Resfood food = null;
        //通过迭代筛选出与前端传输过的fid相符合的food数据
        for (Resfood r : list) {
            if (r.getFid() == fid) {
                food = r;
                break;
            }
        }
        //CartItem购物车工具类，能够存储fid num数据，并且计算出小计smallCount
        Map<Integer, CartItem> cart = null;
        //判断是否第一次添加购物车
        //不是的话将之前的数据添加进来
        if (session.getAttribute(YcConstants.CART) != null) {
            cart = (Map<Integer, CartItem>) session.getAttribute(YcConstants.CART);
        } else {
            cart = new HashMap<Integer, CartItem>();
        }
        CartItem ci = null;
        //方法检查 hashMap 中是否存在指定的 key 对应的映射关系。有则true
        //如果购物车中已经有了该样菜品
        //则将其数量加1
        if (cart.containsKey(fid)) {
            ci = cart.get(fid);
            int newnum = ci.getNum() + num;
            ci.setNum(newnum);
        } else {//没有的话将其添加到ci里面（CartItem类型）
            ci = new CartItem();
            ci.setFood(food);
            ci.setNum(num);
        }
        //如果数量小于等于0 移除该样菜品
        //防止数量为负数
        if (ci.getNum() <= 0) {
            cart.remove(fid);
        } else {
            ci.getSmallCount();
            cart.put(fid, ci);
        }
        session.setAttribute(YcConstants.CART, cart);
    }

    @RequestMapping(value = "/getCartInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonModel getCartInfo(HttpServletRequest req, HttpSession session, JsonModel jm) {

        List<CartItem> list = new ArrayList<>();
        //判断购物车cart中是否有东西
        if(session.getAttribute(YcConstants.CART)!=null){
            jm.setCode(1);
            Map<Integer,CartItem> cart=null;
            //有的话将其复制到cart里面传输给前端
            cart= (Map<Integer, CartItem>) session.getAttribute(YcConstants.CART);
            //?????????为什么要用这个方法去将其存储到list里面
            //cart 是一个map类型的集合Map<Integer,CartItem> cartitem 是一个bean类，一个随机产生的integer对应一个cartitem
            //如果将map包装在list里面传到前端，前端需要根据key去获取对应的值
            Set<Integer> sets=cart.keySet();//获取cart里所有的键名放到set集合里
            for(Integer i:cart.keySet()){
                cart.get(i);
            }

            Iterator<Integer> iterator=sets.iterator();//iterator 迭代器
            while(iterator.hasNext()){ //it.hasNext() 用于检测集合中是否还有元素。
                int x=iterator.next();//it.next 返回迭代器的下一个元素，并且更新迭代器的状态。
                list.add(cart.get(x));//将迭代器中取出的键对应的内容取出存到list里面
            }
            jm.setObj(list);
        }else{
            jm.setCode(0);
        }
        return jm;
    }
    @RequestMapping(value = "/delorder",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonModel delorder(HttpSession session,JsonModel jm,Resfood food){
        //获取前端传输的food中的fid
        int fid=food.getFid();
        Map<Integer,CartItem> cart=null;
        //判断购物车是否为空
        if(session.getAttribute(YcConstants.CART)!=null){
            cart= (Map<Integer, CartItem>) session.getAttribute(YcConstants.CART);
        }else{
            cart=new HashMap<Integer, CartItem>();
        }//判断cart中是否有fid对应的菜品
        if(cart.containsKey(fid)){
            //有就移除
            cart.remove(fid);
            jm.setCode(1);
        }else{//没有就返回失败
            jm.setCode(0);
        }
        session.setAttribute(YcConstants.CART,cart);
        return jm;
    }
    @RequestMapping(value = "/clearAll",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonModel clearAllOp(HttpSession session,JsonModel jm){
        session.removeAttribute(YcConstants.CART);//移除cart键对应的session内容
        jm.setCode(1);
        return jm;
    }
}
