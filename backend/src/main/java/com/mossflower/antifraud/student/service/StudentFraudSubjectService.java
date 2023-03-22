package com.mossflower.antifraud.student.service;

import com.mossflower.antifraud.entity.FraudSubject;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/27 11:18
 */
public interface StudentFraudSubjectService {

    /**
     * 根据诈骗类型 返回10条诈骗题目
     *
     * @param fraudType 诈骗类型
     * @return 诈骗题目列表
     */
    List<FraudSubject> getFraudSubjectList(String fraudType);

}
