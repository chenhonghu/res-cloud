package com.yc.resorder.dao;

import com.yc.res.bean.Resfood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-04-28 19:47
 */
public interface ResfoodDao extends JpaRepository<Resfood,Integer> {

}
