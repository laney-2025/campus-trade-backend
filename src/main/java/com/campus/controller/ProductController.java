package com.campus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.campus.common.Result;
import com.campus.entity.Product;
import com.campus.service.ProductService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/pending")
    public Result<IPage<Product>> pending(@RequestParam(defaultValue = "1") Long current,
                                          @RequestParam(defaultValue = "10") Long size) {
        return Result.ok(productService.pagePending(current, size));
    }

    @PostMapping("/{id}/approve")
    public Result<Void> approve(@PathVariable Long id, @RequestParam Long operatorId) {
        productService.approve(id, operatorId);
        return Result.ok();
    }

    @PostMapping("/{id}/reject")
    public Result<Void> reject(@PathVariable Long id, @RequestParam Long operatorId) {
        productService.reject(id, operatorId);
        return Result.ok();
    }
}
