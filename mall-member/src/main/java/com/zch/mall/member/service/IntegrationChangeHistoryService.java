package com.zch.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zch.common.utils.PageUtils;
import com.zch.mall.member.entity.IntegrationChangeHistoryEntity;

import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author zhaocuihuo
 * @email Cassius20002300@163.com
 * @date 2022-10-08 20:50:17
 */
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

