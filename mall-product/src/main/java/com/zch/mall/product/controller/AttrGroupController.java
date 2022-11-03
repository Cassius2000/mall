package com.zch.mall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zch.mall.product.dao.AttrAttrgroupRelationDao;
import com.zch.mall.product.entity.AttrEntity;
import com.zch.mall.product.service.AttrAttrgroupRelationService;
import com.zch.mall.product.service.AttrService;
import com.zch.mall.product.service.CategoryService;
import com.zch.mall.product.vo.AttrGroupRelationVo;
import com.zch.mall.product.vo.AttrGroupWithAttrsVo;
import com.zch.mall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zch.mall.product.entity.AttrGroupEntity;
import com.zch.mall.product.service.AttrGroupService;
import com.zch.common.utils.PageUtils;
import com.zch.common.utils.R;

import javax.annotation.Resource;


/**
 * 属性分组
 *
 * @author zhaocuihuo
 * @email Cassius20002300@163.com
 * @date 2022-10-06 21:01:02
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private CategoryService categoryService;

    @Resource
    AttrService attrService;

    @Resource
    AttrAttrgroupRelationService relationService;


//    /product/attrgroup/attr/relation"
    @PostMapping("/attr/relation")
    public R attrRelation(@RequestBody List<AttrGroupRelationVo> vos){

        relationService.saveBatch(vos);
        return R.ok();

    }

    // /{catelogId}/withattr
    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("catelogId") Long catelogId){
        List<AttrGroupWithAttrsVo> vos = attrGroupService.getAttrGroupWithAttrsBycatelogId(catelogId);
        return R.ok().put("data",vos);
    }

    ///product/attrgroup/attr/relation/delete
    @PostMapping("attr/relation/delete")
    public R deleteRelation(@RequestBody AttrGroupRelationVo[] vos){
        attrService.deleteRelation(vos);
        return R.ok();
    }
    ///attrgroup/1/attr/relation"
    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId){
      List<AttrEntity> entities =  attrService.getRelationAttr(attrgroupId);
      return R.ok().put("data",entities);
    }

   // "/product/attrgroup/1/noattr/relation"
    @GetMapping("/{attrGroupId}/noattr/relation")
    public R noattrRelation(@RequestParam Map<String, Object> params ,
                            @PathVariable("attrGroupId") Long attrGroupId){
       PageUtils pageUtils =  attrService.getNoRelationAttr(params,attrGroupId);

       return R.ok().put("page",pageUtils);
    }
    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params ,
                  @PathVariable("catelogId") Long catelogId){
//        PageUtils page = attrGroupService.queryPage(params);
        PageUtils page = attrGroupService.queryPage(params, catelogId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();
        Long[] path = categoryService.findCatelogPath(catelogId);
        attrGroup.setCatelogPath(path);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
