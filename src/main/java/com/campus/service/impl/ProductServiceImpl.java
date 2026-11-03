package com.campus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.entity.Product;
import com.campus.mapper.ProductMapper;
import com.campus.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public IPage<Product> pagePending(Long current, Long size) {
        Page<Product> page = new Page<>(current, size);
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getStatus, 0);
        wrapper.orderByDesc(Product::getCreateTime);
        return this.page(page, wrapper);
    }

    @Override
    public void approve(Long id, Long operatorId) {
        Product product = getById(id);
        product.setStatus(1);
        product.setOperatorId(operatorId);
        product.setOperateTime(new Date());
        updateById(product);
    }

    @Override
    public void reject(Long id, Long operatorId) {
        Product product = getById(id);
        product.setStatus(2);
        product.setOperatorId(operatorId);
        product.setOperateTime(new Date());
        updateById(product);
    }
}
