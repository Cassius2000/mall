package com.zch.mall.ware.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PurchaseVo {
    @NotNull
    private  Long id; //采购单id

    private List<PurchaseItemDoneVo> items;
}
