package com.yc.res.dao;

import com.yc.res.bean.Resuser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-04-28 19:49
 */
public interface ResuserDao extends JpaRepository<Resuser,Integer> {

}
