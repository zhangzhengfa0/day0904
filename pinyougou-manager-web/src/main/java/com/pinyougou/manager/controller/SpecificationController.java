package com.pinyougou.manager.controller;


import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.sellergoods.service.SpecificationService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/specification")
public class SpecificationController {
    @Resource
    private SpecificationService specificationService;

    @RequestMapping("/getSpecList")
    public List<Map> getSpecList(){
        return specificationService.getSpecList();
    }


    @RequestMapping("/search")
    public PageResult search(@RequestBody TbSpecification tbSpecification,int page,int rows){
        return specificationService.search(tbSpecification,page,rows);
    }

    @RequestMapping("/dele")
    public Result dele(@RequestBody Long[] ids){
        try {
            specificationService.dele(ids);
            return new Result(true, "删除成功");
        }catch (Exception e){
            return new Result(false, "删除失败");
        }
    }

    @RequestMapping("/save")
    public Result save(@RequestBody Specification specification){
        try {
            specificationService.save(specification);
            return new Result(true,"编辑成功");
        }catch (Exception e){
            return new Result(false,"编辑失败");
        }
    }

    @RequestMapping("/findOne")
    public Specification findOne(Long id){
        return specificationService.findOne(id);
    }
}
