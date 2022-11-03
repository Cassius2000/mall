package com.zch.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zch.common.utils.PageUtils;
import com.zch.mall.product.entity.SpuInfoDescEntity;
import com.zch.mall.product.entity.SpuInfoEntity;
import com.zch.mall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author zhaocuihuo
 * @email Cassius20002300@163.com
 * @date 2022-10-06 21:01:02
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);
}

