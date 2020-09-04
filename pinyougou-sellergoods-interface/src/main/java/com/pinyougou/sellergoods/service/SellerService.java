package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbSeller;
import entity.PageResult;

public interface SellerService {
    Integer getSellerIdCount(String sellerId);

    void save(TbSeller tbSeller);

    PageResult search(TbSeller tbSeller, int page, int rows);

    TbSeller findOne(String sellerId);

    void updateStatus(String sellerId, String status);
}
