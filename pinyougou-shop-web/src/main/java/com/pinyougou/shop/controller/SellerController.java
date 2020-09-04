package com.pinyougou.shop.controller;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Resource
    private SellerService sellerService;


    @RequestMapping("/checkSellerId")
    public Result checkSellerId(String sellerId){
        Integer count = sellerService.getSellerIdCount(sellerId);
        if(count>=1){
            return new Result(false, "账户名称已存在");
        }
        return new Result(true, "可以使用");
    }

    @RequestMapping("/save")
    public Result save(@RequestBody TbSeller tbSeller){
        try {
            sellerService.save(tbSeller);
            return new Result(true,"编辑成功");
        }catch (Exception e){
            return new Result(false,"编辑失败");
        }
    }
}
