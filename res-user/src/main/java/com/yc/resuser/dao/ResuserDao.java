package com.yc.resuser.dao;


import com.yc.res.bean.Resuser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-04-28 19:49
 */
public interface ResuserDao extends JpaRepository<Resuser,Integer> {

}
