package com.mossflower.antifraud.admin.controller;

import com.mossflower.antifraud.admin.service.AdminStuService;
import com.mossflower.antifraud.common.R;
import com.mossflower.antifraud.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/15 13:52
 */
@RestController
@RequestMapping("/admins/stu")
@Slf4j
public class AdminStuController {

    @Autowired
    private AdminStuService adminStuService;

    @GetMapping("/list")
    public R<List<Student>> getAllStu(String className) {
        List<Student> list = adminStuService.getAllStu(className);
        return R.ok("查询成功", list);
    }

}
