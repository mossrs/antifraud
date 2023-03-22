package com.mossflower.antifraud.wx.service;

import com.mossflower.antifraud.entity.FraudSubject;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/9/21 20:01
 */
public interface PushService {

    /**
     * 推送随机题目 15道不同类型
     * @return 题目列表
     */
    List<FraudSubject> getRandomFraudSubject();
}
