package com.pinyougou.sellergoods.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbTypeTemplateMapper;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.pojo.TbTypeTemplateExample;
import com.pinyougou.sellergoods.service.TypeTemplateService;
import entity.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {
    @Resource
    private TbTypeTemplateMapper tbTypeTemplateMapper;
    @Override
    public PageResult search(TbTypeTemplate tbTypeTemplate, int page, int rows) {
        PageHelper.startPage(page, rows);
        TbTypeTemplateExample example = new TbTypeTemplateExample();
        TbTypeTemplateExample.Criteria criteria = example.createCriteria();
        if(tbTypeTemplate!=null){
            if(tbTypeTemplate.getName()!=null&&!"".equals(tbTypeTemplate.getName())){
                criteria.andNameLike("%"+tbTypeTemplate.getName()+"%");
            }
        }

        Page<TbTypeTemplate> tbTypeTemplates = (Page<TbTypeTemplate>)tbTypeTemplateMapper.selectByExample(example);
        return new PageResult(tbTypeTemplates.getTotal(), tbTypeTemplates.getResult());
    }

    @Override
    public void dele(Long[] ids) {
        if (ids!=null&&ids.length>=1){
            for (Long id:ids){
                tbTypeTemplateMapper.deleteByPrimaryKey(id);
            }

        }
    }

    @Override
    public void save(TbTypeTemplate tbTypeTemplate) {
        if (tbTypeTemplate!=null){
            if (tbTypeTemplate.getId()!=null){
                tbTypeTemplateMapper.updateByPrimaryKeySelective(tbTypeTemplate);
            }else{
                System.out.println(tbTypeTemplate.getCustomAttributeItems());
                tbTypeTemplateMapper.insertSelective(tbTypeTemplate);
            }
        }
    }

    @Override
    public TbTypeTemplate findOne(Long id) {
        return tbTypeTemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Map> getTypeList() {
        return tbTypeTemplateMapper.selectOptionList();
    }

}
