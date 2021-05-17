package com.yc.res.res_mvc_boot;

import com.yc.res.bean.Resorder;
import com.yc.res.biz.ResorderBiz;
import com.yc.res.biz.impl.ResfoodBizImpl;
import com.yc.res.dao.ResuserDao;
import com.yc.res.vo.CartItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-04-28 20:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class test01 {
    @Autowired
    ResuserDao resuserDao;
    @Autowired
    public ResfoodBizImpl resfoodBiz;
    @Autowired
    ResorderBiz resorderBiz;



    @Test
    public void testFindByid(){
        System.out.println(resfoodBiz.findByFid(1));
    }
    @Test
    public void testcompleteOrder(){
        Resorder rs=new Resorder();
        rs.setAddress("a");
//        rs.setDeliverytime(new Timestamp(System.currentTimeMillis()));
        rs.setOrdertime(new Timestamp(System.currentTimeMillis()));
        rs.setTel("11111111111");
        rs.setStatus(0);
        rs.setUserid(1);
        rs.setPs("无");
        //购物车
        Map<Integer, CartItem> cart=new HashMap<Integer ,CartItem>();
        Integer fid1=11;
        CartItem ct1=new CartItem();
        ct1.setFood(resfoodBiz.findByFid(fid1));
        ct1.setNum(2);

        Integer fid2=12;
        CartItem ct2=new CartItem();
        ct2.setFood(resfoodBiz.findByFid(fid2));
        ct2.setNum(5);
        cart.put(fid1,ct1);
        cart.put(fid2,ct2);
        resorderBiz.completeOrder(rs,cart);
    }

}
