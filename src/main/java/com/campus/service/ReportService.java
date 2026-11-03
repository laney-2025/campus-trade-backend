package com.campus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.entity.Report;

public interface ReportService extends IService<Report> {
    IPage<Report> pageReports(Long current, Long size, Integer status);
    void handleViolation(Long id, Long handlerId, String handleResult);
    void dismiss(Long id, Long handlerId, String handleResult);
}
