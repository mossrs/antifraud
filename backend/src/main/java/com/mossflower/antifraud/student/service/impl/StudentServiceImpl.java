package com.mossflower.antifraud.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mossflower.antifraud.common.G;
import com.mossflower.antifraud.entity.Student;
import com.mossflower.antifraud.mapper.StudentMapper;
import com.mossflower.antifraud.student.service.StudentService;
import com.mossflower.antifraud.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * @author z's'b
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public String getOne(String studentCard, String password) {
        HashMap<String, String> map = new HashMap<>();
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Student::getStudentCard, studentCard).eq(Student::getPassword, password);
        Student selectStu = studentMapper.selectOne(lqw);
        if (selectStu != null) {
            map.put("studentId", selectStu.getId().toString());
            map.put("studentCard", selectStu.getStudentCard());
            return JwtUtil.generateToken(map);
        }
        return null;
    }

    @Override
    public Student getStudentByStudentCard() {
        String studentCard = G.getDecodeJwt().getClaim("studentCard").asString();
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Student::getStudentCard, studentCard);
        G.removeDecodeJwt();
        return studentMapper.selectOne(lqw);
    }
}
