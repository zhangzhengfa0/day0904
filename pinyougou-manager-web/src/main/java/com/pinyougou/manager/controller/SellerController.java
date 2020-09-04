package com.pinyougou.manager.controller;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import entity.PageResult;
import entity.Result;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Resource
    private SellerService sellerService;

    @RequestMapping("/search")
    public PageResult search(@RequestBody TbSeller tbSeller,int page,int rows){
        return sellerService.search(tbSeller,page,rows);
    }
    @RequestMapping("/findOne")
    public TbSeller findOne(String sellerId){
        return sellerService.findOne(sellerId);
    }
    @RequestMapping("/updateStatus")
    public Result updateStatus(String sellerId,String status){
        try {
            sellerService.updateStatus(sellerId,status);
            return new Result(true,"审核成功");
        }catch (Exception e){
            return new Result(false,"审核失败");
        }
    }

}
