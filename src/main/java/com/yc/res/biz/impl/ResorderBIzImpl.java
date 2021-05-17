package com.yc.res.biz.impl;

import com.yc.res.bean.Resorder;
import com.yc.res.vo.OrderStatusEnum;
import com.yc.res.bean.Resorderitem;
import com.yc.res.biz.ResorderBiz;
import com.yc.res.dao.ResorderDao;
import com.yc.res.dao.ResorderitemDao;
import com.yc.res.vo.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Map;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-05-04 19:02
 */
@Service
@Transactional
public class ResorderBIzImpl implements ResorderBiz {
/**
 * 订单信息 resoder
 * 购物车，对应订单项 shopCart
 */
    @Autowired
    ResorderDao resorderDao;
    @Autowired
    ResorderitemDao resorderitemDao;

    @Override
    public Integer completeOrder(Resorder resorder, Map<Integer, CartItem> shopCart) {
        resorder.setStatus(OrderStatusEnum.NEW.getCode());
        resorder.setOrdertime(new Timestamp(System.currentTimeMillis()) );
        resorder.setAddress(resorder.getAddress());
        resorder.setDeliverytime(resorder.getDeliveryTime());
        Resorder orderResult=resorderDao.save(resorder);
        if(shopCart !=null && shopCart.size()>0){
            for(Map.Entry<Integer,CartItem> entry:shopCart.entrySet()){
                Resorderitem ri=new Resorderitem();
                ri.setRoid(orderResult.getRoid());
                ri.setNum(entry.getValue().getNum());
                ri.setFid(entry.getKey());
                ri.setDealprice(entry.getValue().getFood().getRealprice());
                resorderitemDao.save(ri);
            }
        }
        return orderResult.getRoid();
    }
}
