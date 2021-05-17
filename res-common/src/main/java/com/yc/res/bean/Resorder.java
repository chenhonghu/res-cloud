package com.yc.res.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-04-27 18:51
 */

@Entity
@Table
public class Resorder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roid;
    @Column
    private Integer userid;
    @Column
    private String address;
    @Column
    private String tel;
    @Column
    private Timestamp ordertime;
    @Column
    private Timestamp deliverytime;//PO中用的是 Timestamp
    @Column
    private String ps;
    @Column
    private Integer status;
    @Transient//忽略，如果有get方法这个注解自动失效，解决方案：在get上也加一个@Transient注解
    private String deliverytimeString;
    public Timestamp getDeliveryTime(){
        if(deliverytimeString!=null){
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date d= null;
            try {
                d = df.parse(deliverytimeString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            deliverytime=new Timestamp(d.getTime());
        }else{
            Date d=new Date();
        }
        deliverytime =new Timestamp(deliverytime.getTime());
        return deliverytime;
    }

    public Integer getRoid() {
        return roid;
    }

    public void setRoid(Integer roid) {
        this.roid = roid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Timestamp getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Timestamp ordertime) {
        this.ordertime = ordertime;
    }


    public void setDeliverytime(Timestamp deliverytime) {
        this.deliverytime = deliverytime;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Transient
    public String getDeliverytimeString() {
        return deliverytimeString;
    }

    public void setDeliverytimeString(String deliverytimeString) {
        this.deliverytimeString = deliverytimeString;
    }
}
