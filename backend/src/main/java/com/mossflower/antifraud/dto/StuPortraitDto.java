package com.mossflower.antifraud.dto;

import com.mossflower.antifraud.entity.Portrait;
import lombok.Getter;
import lombok.Setter;


/**
 * @author z's'b
 * @version 1.0
 * @date 2022/8/21 9:59
 */
@Getter
@Setter
public class StuPortraitDto extends Portrait {
    private String tips;
}
