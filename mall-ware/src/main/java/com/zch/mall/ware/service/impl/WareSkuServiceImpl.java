package com.zch.mall.ware.service.impl;

import com.zch.common.utils.R;
import com.zch.mall.ware.feign.ProductFeignService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zch.common.utils.PageUtils;
import com.zch.common.utils.Query;

import com.zch.mall.ware.dao.WareSkuDao;
import com.zch.mall.ware.entity.WareSkuEntity;
import com.zch.mall.ware.service.WareSkuService;

import javax.annotation.Resource;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Resource
    ProductFeignService feignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        /**
         * skuId:
         * wareId:
         */
        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();
        String skuId = (String) params.get("skuId");
        String wareId = (String) params.get("wareId");
        if (!StringUtils.isEmpty(skuId)){
            wrapper.eq("sku_id",skuId);
        }
        if (!StringUtils.isEmpty(wareId)){
            wrapper.eq("ware_id",wareId);
        }
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    //TODO 出现异常不回滚
    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        //判断如果没有记录就添加
        List<WareSkuEntity> wareSkuEntities = this.baseMapper.selectList(new QueryWrapper<WareSkuEntity>().eq("sku_id", skuId)
                .eq("ware_id", wareId));
        if (wareSkuEntities == null || wareSkuEntities.size() == 0){
            WareSkuEntity wareSkuEntity = new WareSkuEntity();
            wareSkuEntity.setSkuId(skuId);
            wareSkuEntity.setWareId(wareId);
            wareSkuEntity.setStock(skuNum);
            wareSkuEntity.setStockLocked(0);
            try {
                R info = feignService.info(skuId);
                if (info.getCode() == 0){
                    wareSkuEntity.setSkuName((String) info.get("skuName"));
                }
            }catch (Exception e){

            }
            this.baseMapper.insert(wareSkuEntity);
        }else {
            this.baseMapper.addStock(skuId,wareId,skuNum);
        }

    }

}