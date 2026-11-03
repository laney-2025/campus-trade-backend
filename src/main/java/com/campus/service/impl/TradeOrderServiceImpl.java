package com.campus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.entity.TradeOrder;
import com.campus.mapper.TradeOrderMapper;
import com.campus.service.TradeOrderService;
import org.springframework.stereotype.Service;

@Service
public class TradeOrderServiceImpl extends ServiceImpl<TradeOrderMapper, TradeOrder> implements TradeOrderService {
}
