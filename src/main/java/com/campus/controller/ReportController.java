package com.campus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.campus.common.Result;
import com.campus.entity.Report;
import com.campus.service.ReportService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/report")
public class ReportController {

    @Resource
    private ReportService reportService;

    @GetMapping("/page")
    public Result<IPage<Report>> page(@RequestParam(defaultValue = "1") Long current,
                                      @RequestParam(defaultValue = "10") Long size,
                                      @RequestParam(required = false) Integer status) {
        return Result.ok(reportService.pageReports(current, size, status));
    }

    @PostMapping("/{id}/violation")
    public Result<Void> handleViolation(@PathVariable Long id,
                                        @RequestParam Long handlerId,
                                        @RequestParam String handleResult) {
        reportService.handleViolation(id, handlerId, handleResult);
        return Result.ok();
    }

    @PostMapping("/{id}/dismiss")
    public Result<Void> dismiss(@PathVariable Long id,
                                @RequestParam Long handlerId,
                                @RequestParam String handleResult) {
        reportService.dismiss(id, handlerId, handleResult);
        return Result.ok();
    }
}
