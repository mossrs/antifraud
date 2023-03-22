package com.mossflower.antifraud.admin.controller;

import com.mossflower.antifraud.admin.service.AdminStatisticsService;
import com.mossflower.antifraud.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/8/11 18:06
 */
@RestController
@RequestMapping("/admins/statistics")
public class AdminStatisticsController {

    @Autowired
    private AdminStatisticsService adminStatisticsService;

    @GetMapping("/cs")
    public R<Long> countStu() {
        Long data = adminStatisticsService.countStu();
        return R.ok("获取成功", data);
    }

    @GetMapping("/cfsu")
    public R<Long> countFraudStu() {
        Long data = adminStatisticsService.countFraudStu();
        return R.ok("获取成功", data);
    }

    @GetMapping("/cfst")
    public R<Long> countFraudSubject() {
        Long data = adminStatisticsService.countFraudSubject();
        return R.ok("获取成功", data);
    }

    @GetMapping("/cffs")
    public R<Long> countFinished() {
        Long data = adminStatisticsService.countFinishedFraudSubject();
        return R.ok("获取成功", data);
    }

    @GetMapping("/cfl")
    public R<Map<String, String>> getFraudLevel() {
        Map<String, String> data = adminStatisticsService.countFraudLevel();
        return R.ok("获取成功", data);
    }

    @GetMapping("/cmfst")
    public R<List<List<Long>>> countMonthFraudSubjectTrend() {
        List<List<Long>> data = adminStatisticsService.countMonthFraudSubjectTrend();
        return R.ok("获取成功", data);
    }

    @GetMapping("/cfsr")
    public R<Map<String, Map<String, Long>>> countFraudSexRatio() {
        Map<String, Map<String, Long>> data = adminStatisticsService.countFraudSexRatio();
        return R.ok("获取成功", data);
    }

    @GetMapping("/cfafr")
    public R<Map<String, List<Long>>> countFraudAgeRatio() {
        Map<String, List<Long>> data = adminStatisticsService.countFraud2AntiFraudRatio();
        return R.ok("获取成功", data);
    }
}
