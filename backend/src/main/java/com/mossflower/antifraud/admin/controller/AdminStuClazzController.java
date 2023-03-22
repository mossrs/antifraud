package com.mossflower.antifraud.admin.controller;

import com.mossflower.antifraud.admin.service.AdminStuClazzService;
import com.mossflower.antifraud.common.R;
import com.mossflower.antifraud.entity.StuClazz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/8/13 17:41
 */
@RestController
@RequestMapping("/admins/stuClazz")
public class AdminStuClazzController {

    @Autowired
    private AdminStuClazzService adminStuClazzService;

    @GetMapping("/list")
    public R<List<StuClazz>> getStuClazzList() {
        List<StuClazz> stuClazzList = adminStuClazzService.getStuClazzList();
        return R.ok("获取成功", stuClazzList);
    }

}
