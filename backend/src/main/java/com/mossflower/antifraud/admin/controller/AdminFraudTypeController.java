package com.mossflower.antifraud.admin.controller;

import com.mossflower.antifraud.admin.service.AdminFraudTypeService;
import com.mossflower.antifraud.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/29 11:58
 */
@RestController
@RequestMapping("/admins/fraudType")
public class AdminFraudTypeController {

    @Autowired
    private AdminFraudTypeService adminFraudTypeService;

    @GetMapping("/list")
    public R<List<String>> getFraudTypeList() {
        List<String> fraudTypeList = adminFraudTypeService.getFraudTypeList();
        return fraudTypeList.size() == 0 ? R.err("暂无数据") : R.ok("获取成功", fraudTypeList);
    }
}
