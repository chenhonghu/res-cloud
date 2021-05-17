package com.yc.res.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-04-27 18:55
 */
@Data
@Entity
@Table
public class Resuser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;
    @Column
    private String username;
    @Column
    private String pwd;
    @Column
    private String email;
}
