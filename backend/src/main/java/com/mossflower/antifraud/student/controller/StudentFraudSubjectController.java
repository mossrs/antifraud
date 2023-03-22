package com.mossflower.antifraud.student.controller;

import com.mossflower.antifraud.common.R;
import com.mossflower.antifraud.entity.FraudSubject;
import com.mossflower.antifraud.student.service.StudentFraudSubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/27 11:31
 */
@RequestMapping("/stus/fraudSubject")
@RestController
@Slf4j
@Validated
public class StudentFraudSubjectController {

    @Autowired
    private StudentFraudSubjectService studentFraudSubjectService;

    @GetMapping("/list")
    public R<List<FraudSubject>> getFraudSubjectList(String fraudType) {
        List<FraudSubject> list = studentFraudSubjectService.getFraudSubjectList(fraudType);
        return list.size() == 0 ? R.err("暂无数据") : R.ok("获取成功", list);
    }
}
