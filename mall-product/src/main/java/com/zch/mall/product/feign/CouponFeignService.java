package com.zch.mall.product.feign;

import com.zch.common.to.SkuReductionTo;
import com.zch.common.to.SpuBoundTo;
import com.zch.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("mall-coupon")
public interface CouponFeignService {


    @PostMapping("coupon/spubounds/save")
    R saveBounds(@RequestBody SpuBoundTo spuBoundTo);

    @PostMapping("coupon/skufullreduction/saveInfo")
    R saveSkuReduction( @RequestBody SkuReductionTo skuReductionTo);
}
