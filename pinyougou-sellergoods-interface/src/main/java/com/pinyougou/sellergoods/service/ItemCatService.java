package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {
    List<TbItemCat> findByParentId(Long parenId);

    void deleteByIds(Long[] ids);

    void save(TbItemCat tbItemCat);

    TbItemCat findOne(Long id);
}
