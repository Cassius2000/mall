package com.zch.mall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zch.mall.product.entity.ProductAttrValueEntity;
import com.zch.mall.product.service.ProductAttrValueService;
import com.zch.mall.product.vo.AttrRespVo;
import com.zch.mall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zch.mall.product.entity.AttrEntity;
import com.zch.mall.product.service.AttrService;
import com.zch.common.utils.PageUtils;
import com.zch.common.utils.R;

import javax.annotation.Resource;


/**
 * 商品属性
 *
 * @author zhaocuihuo
 * @email Cassius20002300@163.com
 * @date 2022-10-06 21:01:02
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @Resource
    ProductAttrValueService productAttrValueService;

    ///base/listforspu/11
    @GetMapping("/base/listforspu/{spuId}")
    public R baseListAttrSpu(@PathVariable("spuId") Long spuId){
       List<ProductAttrValueEntity> entityList =  productAttrValueService.baseListAttrSpu(spuId);
        return R.ok().put("data",entityList);
    }

    //base/list/225
    @GetMapping("/{attrType}/list/{catelogId}")
    public R baseAttrList(@RequestParam Map<String,Object> params,
                          @PathVariable("catelogId") Long catelogId,
                          @PathVariable("attrType") String attrType){
        PageUtils page = attrService.queryBaseAttrPage(params,catelogId,attrType);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId){
//		AttrEntity attr = attrService.getById(attrId);
      AttrRespVo respVo =  attrService.getAttrInfo(attrId);

        return R.ok().put("attr", respVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attr){
		attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrVo attr){
		attrService.updateAttr(attr);

        return R.ok();
    }

    ///product/attr/update/11"
    @PostMapping("/update/{spuId}")
    public R updateSpuAttr(@PathVariable("spuId") Long spuId,
                           @RequestBody List<ProductAttrValueEntity> entities){

       productAttrValueService.updateSpuAttr(spuId,entities);

        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
