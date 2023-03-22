package com.mossflower.antifraud.student.service;

import com.mossflower.antifraud.entity.FraudAssessment;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/27 8:56
 */
public interface StudentFraudAssessmentService {

    /**
     * 保存诈骗评估
     *
     * @param fraudAssessment 诈骗评估实体
     * @return 保存结果
     */
    Boolean saveOne(FraudAssessment fraudAssessment);
}
