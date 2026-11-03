package com.campus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.entity.Product;

public interface ProductService extends IService<Product> {
    IPage<Product> pagePending(Long current, Long size);
    void approve(Long id, Long operatorId);
    void reject(Long id, Long operatorId);
}
