package com.mossflower.antifraud.student.controller;

import com.mossflower.antifraud.common.R;
import com.mossflower.antifraud.entity.Student;
import com.mossflower.antifraud.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author z's'b
 */
@RestController
@RequestMapping("/students")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public R<String> getOne(String studentCard, String password) {
        String token = studentService.getOne(studentCard, password);
        return token == null ? R.err("登录失败") : R.ok("登录成功", token);
    }

    @GetMapping("/getInfo")
    public R<Student> getInfo() {
        Student student = studentService.getStudentByStudentCard();
        return student == null ? R.err("获取失败") : R.ok("获取成功", student);
    }
}