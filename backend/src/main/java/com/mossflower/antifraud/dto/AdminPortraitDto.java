package com.mossflower.antifraud.dto;

import com.mossflower.antifraud.entity.Portrait;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/8/21 12:11
 */
@Getter
@Setter
public class AdminPortraitDto extends Portrait {

    private List<Integer> fraudLevel;

    private List<Integer> answeredNum;

    private List<Integer> questionNum;
}
