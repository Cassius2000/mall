package com.zch.mall.product.service.impl;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.zch.mall.product.dao.CategoryBrandRelationDao;
import com.zch.mall.product.service.CategoryBrandRelationService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zch.common.utils.PageUtils;
import com.zch.common.utils.Query;

import com.zch.mall.product.dao.CategoryDao;
import com.zch.mall.product.entity.CategoryEntity;
import com.zch.mall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {


    @Resource
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public Long[] findCatelogPath(Long catelogId) {
      LinkedList<Long> paths = new LinkedList<>();
        LinkedList<Long> parentPaths = findParentPaths(catelogId, paths);
        Collections.reverse(parentPaths);

        return (Long[]) parentPaths.toArray(new Long[0]);
    }

    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(),category.getName());
    }

    private LinkedList<Long> findParentPaths(Long catelogId, LinkedList<Long> paths){

        paths.add(catelogId);
        CategoryEntity id = this.getById(catelogId);
        if (id.getParentCid() != 0){
            findParentPaths(id.getParentCid(),paths);
        }
        return paths;
    }

    @Override
    public void removeMenusByIds(List<Long> asList) {
        //TODO 检查当前要删除的菜单是否被别的地方引用
        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //查找全部的数据
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);

        //拿出所有的一级目录
        List<CategoryEntity> level1Menus = categoryEntities.stream().filter(categoryEntity ->
             categoryEntity.getParentCid() == 0
        ).map((menu) ->{
            menu.setChildren(getChildrens(menu,categoryEntities));
            return menu;
        }).sorted((menu1,menu2) ->{
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        return level1Menus;
    }

    //查找所有的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root , List<CategoryEntity> all){

        List<CategoryEntity> children = all.stream().filter(categoryEntity ->
                categoryEntity.getParentCid().equals(root.getCatId())
        ).map(categoryEntity -> {
            categoryEntity.setChildren(getChildrens(categoryEntity, all));
            return categoryEntity;
        }).sorted((menu1, menu2) -> {
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        return children;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

}