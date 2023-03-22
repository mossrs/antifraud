package com.mossflower.antifraud.admin.service;

import com.mossflower.antifraud.dto.AdminPortraitDto;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/8/21 12:18
 */
public interface AdminPortraitService {
    /**
     * 获取用户画像
     * @param stuCard 学号
     * @return 用户画像列表
     */
    AdminPortraitDto getPortrait(String stuCard);
}
