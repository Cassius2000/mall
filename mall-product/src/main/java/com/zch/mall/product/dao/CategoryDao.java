package com.zch.mall.product.dao;

import com.zch.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author zhaocuihuo
 * @email Cassius20002300@163.com
 * @date 2022-10-06 21:01:02
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
