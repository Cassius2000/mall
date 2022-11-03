package com.zch.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zch.common.utils.PageUtils;
import com.zch.mall.product.entity.AttrEntity;
import com.zch.mall.product.vo.AttrGroupRelationVo;
import com.zch.mall.product.vo.AttrRespVo;
import com.zch.mall.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author zhaocuihuo
 * @email Cassius20002300@163.com
 * @date 2022-10-06 21:01:02
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String attrType);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(AttrGroupRelationVo[] vos) ;

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrGroupId);
}

