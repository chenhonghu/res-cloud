package com.yc.res.biz;

import com.yc.res.bean.Resfood;

import java.util.List;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-04-27 20:19
 */
public interface ResfoodBiz {
    public List<Resfood> findAll();

    public Resfood findByFid(Integer fid);
}
