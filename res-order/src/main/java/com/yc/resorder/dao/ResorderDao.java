package com.yc.resorder.dao;

import com.yc.res.bean.Resorder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-04-28 19:48
 */
public interface ResorderDao extends JpaRepository<Resorder,Integer> {

}
