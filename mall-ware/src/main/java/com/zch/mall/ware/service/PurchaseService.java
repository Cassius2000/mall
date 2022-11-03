package com.zch.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zch.common.utils.PageUtils;
import com.zch.mall.ware.entity.PurchaseEntity;
import com.zch.mall.ware.vo.MergeVo;
import com.zch.mall.ware.vo.PurchaseItemDoneVo;
import com.zch.mall.ware.vo.PurchaseVo;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author zhaocuihuo
 * @email Cassius20002300@163.com
 * @date 2022-10-08 21:02:17
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageUnreceivePurchase(Map<String, Object> params);

    void mergePurchase(MergeVo mergeVo);

    void received(List<Long> ids);

    void done(PurchaseVo doneVo);

}

