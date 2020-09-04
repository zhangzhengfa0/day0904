package com.pinyougou.sellergoods.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.sellergoods.service.SpecificationService;
import entity.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Resource
    private TbSpecificationMapper specificationMapper;
    @Resource
    private TbSpecificationOptionMapper specificationOptionMapper;

    @Override
    public PageResult search(TbSpecification tbSpecification, int page, int rows) {
        PageHelper.startPage(page, rows);
        TbSpecificationExample example = new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();
        if(tbSpecification!=null){
            if(tbSpecification.getSpecName()!=null&&!"".equals(tbSpecification.getSpecName())){
                criteria.andSpecNameLike("%"+tbSpecification.getSpecName()+"%");
            }
        }
        Page<TbSpecification> pages = (Page<TbSpecification>)specificationMapper.selectByExample(example);
        return new PageResult(pages.getTotal(), pages.getResult());
    }

    @Override
    public void dele(Long[] ids) {
        if (ids!=null&&ids.length>=1){
            for (Long id:ids){
                /**
                 * 把规格删除，但规格选项要一起删，但是规格选项在另外一个mapper里面，导入规格选项
                 */
                specificationMapper.deleteByPrimaryKey(id);
                /**
                 *按照id删除，直接调用deleteByPrimaryKey这个方法
                 *按照其他字段删除，要是不是自动生成的mapper，自己直接去写，
                 *在自动生成的里面也支持按照某个字段就行删除，和条件查询很相似
                 *删除也需要写一个example
                 */
                TbSpecificationOptionExample example = new TbSpecificationOptionExample();
                TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
                criteria.andSpecIdEqualTo(id);
                specificationOptionMapper.deleteByExample(example);
            }

        }
    }

    @Override
    public void save(Specification specification) {
        if (specification!=null&&specification.getSpecification()!=null){
            if (specification.getSpecification().getId()!=null){
                /**
                 * 修改
                 */
                specificationMapper.updateByPrimaryKey(specification.getSpecification());
                /**
                 * 删除
                 */
                TbSpecificationOptionExample example = new TbSpecificationOptionExample();
                TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
                criteria.andSpecIdEqualTo(specification.getSpecification().getId());
                specificationOptionMapper.deleteByExample(example);

                /**
                 * 删除按照规格一次删除多个
                 * 新增需要一个一个删除
                 */
                if (specification.getSpecificationOptionList()!=null&&specification.getSpecificationOptionList().size()>=1){
                    for (TbSpecificationOption option : specification.getSpecificationOptionList()){
                        option.setSpecId(specification.getSpecification().getId());
                        specificationOptionMapper.insert(option);
                    }
                }
            }else {
                /**
                 * 先新增,将id带回来在规格选项
                 * 新增后把新增的id带回来，在xml中配置
                 */

                specificationMapper.insert(specification.getSpecification());
                List<TbSpecificationOption> optionList = specification.getSpecificationOptionList();
                if (optionList!=null&&optionList.size()>=1){
                    for (TbSpecificationOption specificationOption : optionList){
                        specificationOption.setSpecId(specification.getSpecification().getId());
                        specificationOptionMapper.insert(specificationOption);
                    }
                }
            }
        }
    }

    @Override
    public Specification findOne(Long id) {
        Specification specification = new Specification();
        /**
         * 传递过来的id是规格id，所以要查询规格，直接查询id
         */
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
        specification.setSpecification(tbSpecification);

        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);

        List<TbSpecificationOption> options = specificationOptionMapper.selectByExample(example);
        specification.setSpecificationOptionList(options);
        return specification;
    }

    @Override
    public List<Map> getSpecList() {
        return specificationMapper.selectOptionList();
    }
}
