package com.mossflower.antifraud.student.controller;

import com.mossflower.antifraud.common.R;
import com.mossflower.antifraud.entity.FraudAssessment;
import com.mossflower.antifraud.student.service.StudentFraudAssessmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/27 9:32
 */


@RestController
@RequestMapping("/stus/fraudAssessment")
@Validated
@Slf4j
public class StudentFraudAssessmentController {
    @Autowired
    private StudentFraudAssessmentService studentFraudAssessmentService;

    @PostMapping("/save")
    public R<Object> save(@RequestBody FraudAssessment fraudAssessment) {
        Boolean flag = studentFraudAssessmentService.saveOne(fraudAssessment);
        return flag ? R.ok("保存成功"): R.err("保存失败");
    }

}
