package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbTypeTemplate;
import entity.PageResult;

import java.util.List;
import java.util.Map;

public interface TypeTemplateService {
    PageResult search(TbTypeTemplate tbTypeTemplate, int page, int rows);

    void dele(Long[] ids);

    void save(TbTypeTemplate tbTypeTemplate);

    TbTypeTemplate findOne(Long id);

    List<Map> getTypeList();
}
