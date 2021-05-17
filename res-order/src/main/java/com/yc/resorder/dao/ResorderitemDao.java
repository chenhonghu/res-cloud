package com.yc.resorder.dao;

import com.yc.res.bean.Resorderitem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-04-28 19:48
 */
public interface ResorderitemDao extends JpaRepository<Resorderitem,Integer> {
}
