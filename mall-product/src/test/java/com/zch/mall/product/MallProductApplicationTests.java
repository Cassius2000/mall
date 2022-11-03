package com.zch.mall.product;

import com.netflix.client.ClientException;
import com.zch.mall.product.entity.BrandEntity;
import com.zch.mall.product.service.BrandService;
import com.zch.mall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MallProductApplicationTests {

    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;


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

    @Test
    public void testFindParentPath(){
        Long[] catelogPath = categoryService.findCatelogPath(255L);
        log.info("完整路径：{}", Arrays.asList(catelogPath));
    }

}
