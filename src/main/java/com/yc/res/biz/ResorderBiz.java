package com.yc.res.biz;

import com.yc.res.bean.Resorder;
import com.yc.res.vo.CartItem;

import java.util.Map;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-04-27 20:17
 */
public interface ResorderBiz {
   // public void completeOrder (Resorder resorder, Map<Integer,CartItem> shopCart);
public Integer completeOrder(Resorder resoder, Map<Integer, CartItem> shopCart);
}
