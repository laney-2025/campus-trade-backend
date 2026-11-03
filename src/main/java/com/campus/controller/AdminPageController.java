package com.campus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.Result;
import com.campus.entity.Product;
import com.campus.entity.Report;
import com.campus.entity.TradeOrder;
import com.campus.service.ProductService;
import com.campus.service.ReportService;
import com.campus.service.TradeOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminPageController {

    @Resource
    private ProductService productService;

    @Resource
    private ReportService reportService;

    @Resource
    private TradeOrderService tradeOrderService;

    @GetMapping("/product/pending")
    public Result<IPage<Product>> productPending(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size
    ) {
        return Result.ok(productService.pagePending(current, size));
    }

    @GetMapping("/report/page")
    public Result<IPage<Report>> reportPage(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size
    ) {
        // 全部状态，status传null
        return Result.ok(reportService.pageReports(current, size, null));
    }

    @GetMapping("/order/page")
    public Result<IPage<TradeOrder>> orderPage(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size
    ) {
        Page<TradeOrder> page = new Page<>(current, size);
        LambdaQueryWrapper<TradeOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(TradeOrder::getCreateTime);
        return Result.ok(tradeOrderService.page(page, wrapper));
    }
}
