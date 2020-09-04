package com.pinyougou.sellergoods.service;
import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface BrandService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbBrand> findAll();

	PageResult findPage(int page, int rows);

    void save(TbBrand tbBrand);

	void deleteBrand(Long[] id);

	TbBrand findOne(Long id);

	PageResult search(TbBrand tbBrand, int page, int rows);

    List<Map> getBrandList();
}
