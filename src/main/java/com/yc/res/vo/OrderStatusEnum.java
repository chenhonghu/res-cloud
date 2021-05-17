package com.yc.res.vo;

import lombok.Getter;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-05-07 20:46
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),FINSHED(1,"已完结"),CANCEL(2,"已取消");
    private Integer code;
    private String message;
    OrderStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
