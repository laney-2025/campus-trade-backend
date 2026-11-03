package com.campus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.entity.Report;
import com.campus.mapper.ReportMapper;
import com.campus.service.ReportService;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {

    @Override
    public IPage<Report> pageReports(Long current, Long size, Integer status) {
        Page<Report> page = new Page<>(current, size);
        LambdaQueryWrapper<Report> wrapper = new LambdaQueryWrapper<>();
        if(status != null){
            wrapper.eq(Report::getStatus, status);
        }
        wrapper.orderByDesc(Report::getCreateTime);
        return this.page(page, wrapper);
    }

    @Override
    public void handleViolation(Long id, Long handlerId, String handleResult) {
        Report report = getById(id);
        report.setStatus(1);
        report.setOperatorId(handlerId);
        report.setOperateTime(new Date());
        updateById(report);
    }

    @Override
    public void dismiss(Long id, Long handlerId, String handleResult) {
        Report report = getById(id);
        report.setStatus(2);
        report.setOperatorId(handlerId);
        report.setOperateTime(new Date());
        updateById(report);
    }
}
