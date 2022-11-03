package com.zch.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zch.common.to.SkuReductionTo;
import com.zch.common.utils.PageUtils;
import com.zch.mall.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author zhaocuihuo
 * @email Cassius20002300@163.com
 * @date 2022-10-08 20:32:29
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuReduction(SkuReductionTo skuReductionTo);
}

