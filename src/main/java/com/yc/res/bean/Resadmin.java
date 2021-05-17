package com.yc.res.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-04-27 18:49
 */
@Data
@Entity
@Table
public class Resadmin implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  Integer raid;
    @Column
    private String raname;
    @Column
    private String rapwd;
}
