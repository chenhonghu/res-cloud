package com.yc.res.biz.impl;

import com.yc.res.bean.Resuser;
import com.yc.res.biz.ResuserBiz;
import com.yc.res.dao.ResuserDao;
import com.yc.res.util.Encrypt;
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
 * @create: 2021-04-28 19:50
 */
@Service
@Transactional
public class ResuserBizImpl implements ResuserBiz {
    @Autowired
   private  ResuserDao resuserDao;

    @Override
    public Resuser login(Resuser resuser) {
        resuser.setPwd(Encrypt.md5(resuser.getPwd()));
        Example<Resuser> example =Example.of(resuser);
        Optional<Resuser> optional =resuserDao.findOne(example);
        return optional.get();
    }
}
