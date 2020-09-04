package com.pinyougou.sellergoods.service.impl;

import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.pojo.TbItemCatExample;
import com.pinyougou.sellergoods.service.ItemCatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Resource
    private TbItemCatMapper tbItemCatMapper;
    @Override
    public List<TbItemCat> findByParentId(Long parentId) {

        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        return tbItemCatMapper.selectByExample(example);
    }
    List<Long> idss = null;
    @Override
    public void deleteByIds(Long[] ids) {
        idss = new ArrayList();
        /**
         * 告诉一个id，想查询这个id还有没有子集
         * 递归
         */
        if (ids!=null){
            for (Long id : ids) {
                getIdsById(id);
            }
        }
        if (idss.size()>=1){
            for (Long o:idss){
                tbItemCatMapper.deleteByPrimaryKey(o);
            }
        }
    }

    @Override
    public void save(TbItemCat tbItemCat) {
        if (tbItemCat!=null){
            if (tbItemCat.getId()!=null){
                tbItemCatMapper.updateByPrimaryKeySelective(tbItemCat);
            }else {
                tbItemCatMapper.insertSelective(tbItemCat);
            }
        }
    }

    @Override
    public TbItemCat findOne(Long id) {
        return tbItemCatMapper.selectByPrimaryKey(id);
    }

    private void getIdsById(Long id){

        idss.add(id);

        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        if (list!=null&&list.size()>=1){
            for (TbItemCat cat : list) {
                idss.add(cat.getId());
            }
        }
    }
}
