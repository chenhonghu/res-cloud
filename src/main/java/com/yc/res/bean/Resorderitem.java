package com.yc.res.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-04-27 18:53
 */
@Data
@Entity
@Table
public class Resorderitem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roiid;
    @Column
    private Integer roid;
    @Column
    private Integer fid;
    @Column
    private BigDecimal dealprice;
    @Column
    private Integer num;


}
