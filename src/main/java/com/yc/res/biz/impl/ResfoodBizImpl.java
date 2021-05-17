package com.yc.res.biz.impl;

import com.yc.res.bean.Resfood;
import com.yc.res.biz.ResfoodBiz;
import com.yc.res.dao.ResfoodDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @program: res_mvc_boot
 * @description:
 * @author: hgdd
 * @create: 2021-05-04 18:48
 */
@Service
@Transactional
public class ResfoodBizImpl implements ResfoodBiz {
    @Autowired
    ResfoodDao resfoodDao;
    @Override
    @Transactional(readOnly = true)
    public List<Resfood> findAll() {
        return resfoodDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Resfood findByFid(Integer fid) {
      Resfood rf=new Resfood();
      rf.setFid(fid);
      Example<Resfood> example=Example.of(rf);
        Optional<Resfood> opt=resfoodDao.findOne(example);
        return opt.get();
    }
}
