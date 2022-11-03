package com.zch.mall.product.vo;

import com.zch.mall.product.entity.AttrEntity;
import com.zch.mall.product.entity.AttrGroupEntity;
import lombok.Data;

import java.util.List;

@Data
public class AttrGroupWithAttrsVo extends AttrGroupEntity {

    private List<AttrEntity> attrs;

}
