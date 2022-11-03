package com.zch.mall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zch.common.valid.AddGroup;
import com.zch.common.valid.UpdateGroup;
import com.zch.common.valid.UpdateStatusGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zch.mall.product.entity.BrandEntity;
import com.zch.mall.product.service.BrandService;
import com.zch.common.utils.PageUtils;
import com.zch.common.utils.R;

import javax.validation.Valid;


/**
 * 品牌
 *
 * @author zhaocuihuo
 * @email Cassius20002300@163.com
 * @date 2022-10-06 21:01:02
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody BrandEntity brand){
		brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand){
		brandService.updateDetail(brand);

        return R.ok();
    }

    @RequestMapping("/update/showStatus")
    public R updateShowStatus(@Validated(UpdateStatusGroup.class) @RequestBody BrandEntity brand){
        UpdateWrapper<BrandEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("brand_id",brand.getBrandId());
        updateWrapper.set("show_status",brand.getShowStatus());
        brandService.update(updateWrapper);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
