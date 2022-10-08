package com.zch.mall.product;

import com.zch.mall.product.entity.BrandEntity;
import com.zch.mall.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void BrandTest(){

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("菠萝");
        brandService.save(brandEntity);
        System.out.println("保存成功-----》");
    }

}
