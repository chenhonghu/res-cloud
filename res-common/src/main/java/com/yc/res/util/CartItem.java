package com.yc.res.util;

import com.yc.res.bean.Resfood;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-05-04 18:56
 */
public class CartItem implements Serializable {
    private Resfood food;
    private int num;
    private BigDecimal smallCount;

    public BigDecimal getSmallCount(){
        this.smallCount=food.getRealprice().multiply(new BigDecimal(num));
        return smallCount;
    }

    public Resfood getFood() {
        return food;
    }

    public void setFood(Resfood food) {
        this.food = food;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setSmallCount(BigDecimal smallCount) {
        this.smallCount = smallCount;
    }
}
