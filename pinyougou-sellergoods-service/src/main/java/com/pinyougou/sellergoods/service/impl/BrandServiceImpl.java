package com.pinyougou.sellergoods.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class BrandServiceImpl implements BrandService {

	@Resource
	private TbBrandMapper brandMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbBrand> findAll() {
		return brandMapper.selectByExample(null);
	}

	@Override
	public PageResult findPage(int page, int rows) {
		PageHelper.startPage(page, rows);
		/**
		 * 有两个接收结果的，page，pageinfo
		 */
		Page tbBrands = (Page<TbBrand>)brandMapper.selectByExample(null);
		return new PageResult(tbBrands.getTotal(), tbBrands.getResult());
	}

	@Override
	public void save(TbBrand tbBrand) {
		if (tbBrand!=null&&tbBrand.getId()!=null){
			brandMapper.updateByPrimaryKeySelective(tbBrand);
		}else {
			brandMapper.insert(tbBrand);
		}

	}

	@Override
	public TbBrand findOne(Long id) {
		return brandMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageResult search(TbBrand tbBrand, int page, int rows) {
		PageHelper.startPage(page, rows);
		/**
		 * 有两个接收结果的，page，pageinfo
		 */

		TbBrandExample example = new TbBrandExample();
		TbBrandExample.Criteria criteria = example.createCriteria();
		if (tbBrand!=null){
			if (tbBrand.getName()!=null&&!"".equals(tbBrand.getName())){
				criteria.andNameLike("%"+tbBrand.getName()+"%");
			}
			if (tbBrand.getFirstChar()!=null&&!"".equals(tbBrand.getFirstChar())){
				criteria.andFirstCharEqualTo(tbBrand.getFirstChar());
			}
		}
		Page tbBrands = (Page<TbBrand>)brandMapper.selectByExample(example);
		return new PageResult(tbBrands.getTotal(), tbBrands.getResult());
	}

	@Override
	public List<Map> getBrandList() {

		return brandMapper.getBrandList();
	}

	@Override
	public void deleteBrand(Long[] ids) {
		if (ids!=null&&ids.length>=1){
			for (Long id:ids){
				brandMapper.deleteByPrimaryKey(id);
			}
		}
	}


}
