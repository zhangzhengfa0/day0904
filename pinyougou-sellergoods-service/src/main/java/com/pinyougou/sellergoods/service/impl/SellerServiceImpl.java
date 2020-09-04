package com.pinyougou.sellergoods.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSellerMapper;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.pojo.TbSellerExample;
import com.pinyougou.sellergoods.service.SellerService;
import entity.PageResult;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SellerServiceImpl implements SellerService {

    @Resource
    private TbSellerMapper tbSellerMapper;
    @Override
    public Integer getSellerIdCount(String sellerId) {
        return tbSellerMapper.getSellerIdCount(sellerId);
    }

    @Override
    public void save(TbSeller tbSeller) {
        if (tbSeller!=null){
            /**
             * 商家的状态
             */
            tbSeller.setStatus("0");
            tbSeller.setCreateTime(new Date());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(tbSeller.getPassword());
            tbSeller.setPassword(password);
            tbSellerMapper.insertSelective(tbSeller);
        }
    }

    @Override
    public PageResult search(TbSeller tbSeller, int page, int rows) {
        PageHelper.startPage(page,rows);
        TbSellerExample example = new TbSellerExample();
        TbSellerExample.Criteria criteria = example.createCriteria();
        if(tbSeller!=null){
            if(tbSeller.getStatus()!=null&&!"".equals(tbSeller.getStatus())){
                criteria.andStatusEqualTo(tbSeller.getStatus());
            }
            if(tbSeller.getName()!=null&&!"".equals(tbSeller.getName())){
                criteria.andNameLike("%"+tbSeller.getName()+"%");
            }
            if(tbSeller.getNickName()!=null&&!"".equals(tbSeller.getNickName())){
                criteria.andNickNameLike("%"+tbSeller.getNickName()+"%");
            }
        }
        Page<TbSeller> pageinfo = (Page<TbSeller>)tbSellerMapper.selectByExample(example);
        return new PageResult(pageinfo.getTotal(), pageinfo.getResult());
    }

    @Override
    public TbSeller findOne(String sellerId) {
        return tbSellerMapper.selectByPrimaryKey(sellerId);
    }

    @Override
    public void updateStatus(String sellerId, String status) {
        tbSellerMapper.updateStatus(sellerId,status);
    }


}
