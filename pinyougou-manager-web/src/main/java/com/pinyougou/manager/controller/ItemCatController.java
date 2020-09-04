package com.pinyougou.manager.controller;

import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.sellergoods.service.ItemCatService;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

    @Resource
    private ItemCatService itemCatService;

    @RequestMapping("/findByParentId")
    public List<TbItemCat> findByParentId(Long parentId){
        return itemCatService.findByParentId(parentId);
    }

    @RequestMapping("/deleteByIds")
    public Result deleteByIds(@RequestBody Long[] ids){
        try {
            itemCatService.deleteByIds(ids);
            return new Result(true,"删除成功");
        }catch (Exception e){
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/save")
    public Result save(@RequestBody TbItemCat tbItemCat){
        try {
            itemCatService.save(tbItemCat);
            return new Result(true,"编辑成功");
        }catch (Exception e){
            return new Result(false,"编辑成功");
        }
    }

    @RequestMapping("/findOne")
    public TbItemCat findOne(Long id){
        System.out.println(id);
        return itemCatService.findOne(id);
    }
}
