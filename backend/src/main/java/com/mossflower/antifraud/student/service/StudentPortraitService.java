package com.mossflower.antifraud.student.service;

import com.mossflower.antifraud.dto.StuPortraitDto;
import com.mossflower.antifraud.entity.Portrait;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/8/1 20:01
 */
public interface StudentPortraitService {

    /**
     * 根据学生id获取学生的画像信息
     * @return 学生的画像信息
     */
    StuPortraitDto getPortrait();

}
