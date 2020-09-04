package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojogroup.Specification;
import entity.PageResult;

import java.util.List;
import java.util.Map;

public interface SpecificationService {
    PageResult search(TbSpecification tbSpecification, int page, int rows);

    void dele(Long[] ids);

    void save(Specification specification);

    Specification findOne(Long id);

    List<Map> getSpecList();

}
