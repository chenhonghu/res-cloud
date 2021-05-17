package com.yc.res.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-04-27 18:50
 */
@Data
@Entity
@Table
public class Resfood implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fid;
    @Column
    private String fname;
    @Column
    private BigDecimal normprice;
    @Column
    private BigDecimal realprice;
    @Column
    private String detail;
    @Column
    private String fphoto;
}
