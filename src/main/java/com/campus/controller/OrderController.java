package com.campus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.Result;
import com.campus.entity.TradeOrder;
import com.campus.service.TradeOrderService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/order")
public class OrderController {

    @Resource
    private TradeOrderService tradeOrderService;

    @GetMapping("/page")
    public Result<IPage<TradeOrder>> page(@RequestParam(defaultValue = "1") Long current,
                                          @RequestParam(defaultValue = "10") Long size) {
        LambdaQueryWrapper<TradeOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(TradeOrder::getCreateTime);
        Page<TradeOrder> page = new Page<>(current, size);
        return Result.ok(tradeOrderService.page(page, wrapper));
    }

    @PostMapping("/{id}/cancel")
    public Result<Void> forceCancel(@PathVariable Long id) {
        TradeOrder order = tradeOrderService.getById(id);
        if (order == null) {
            return Result.fail("订单不存在");
        }
        // 实体字段是 orderStatus，不是 status
        order.setOrderStatus(3);
        tradeOrderService.updateById(order);
        return Result.ok();
    }
}
