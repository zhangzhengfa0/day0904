package com.pinyougou.manager.controller;

import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.sellergoods.service.TypeTemplateService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {
    @Resource
    private TypeTemplateService typeTemplateService;

    @RequestMapping("/search")
    public PageResult search(@RequestBody TbTypeTemplate tbTypeTemplate,int page,int rows){
        return typeTemplateService.search(tbTypeTemplate,page,rows);
    }

    @RequestMapping("/dele")
    public Result dele(@RequestBody Long[] ids){
        try {
            typeTemplateService.dele(ids);
            return new Result(true, "删除成功");
        }catch (Exception e){
            return new Result(false, "删除失败");
        }
    }

    @RequestMapping("/save")
    public Result save(@RequestBody TbTypeTemplate tbTypeTemplate){
        try {
            typeTemplateService.save(tbTypeTemplate);
            return new Result(true,"编辑成功");
        }catch (Exception e){
            return new Result(false,"编辑失败");
        }
    }

    @RequestMapping("/findOne")
    public TbTypeTemplate findOne(Long id){
        return  typeTemplateService.findOne(id);
    }

    @RequestMapping("/getTypeList")
    public List<Map> getTypeList(){
        return typeTemplateService.getTypeList();
    }
}
