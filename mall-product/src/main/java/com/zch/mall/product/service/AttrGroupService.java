package com.zch.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zch.common.utils.PageUtils;
import com.zch.mall.product.entity.AttrGroupEntity;
import com.zch.mall.product.vo.AttrGroupWithAttrsVo;
import com.zch.mall.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author zhaocuihuo
 * @email Cassius20002300@163.com
 * @date 2022-10-06 21:01:02
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long catelogId);

    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsBycatelogId(Long catelogId);
}

