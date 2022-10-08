package com.zch.mall.order.dao;

import com.zch.mall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author zhaocuihuo
 * @email Cassius20002300@163.com
 * @date 2022-10-08 20:58:03
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
