package com.zch.mall.member.dao;

import com.zch.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author zhaocuihuo
 * @email Cassius20002300@163.com
 * @date 2022-10-08 20:50:17
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
