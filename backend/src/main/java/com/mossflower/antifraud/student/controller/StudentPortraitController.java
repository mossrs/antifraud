package com.mossflower.antifraud.student.controller;

import com.mossflower.antifraud.common.R;
import com.mossflower.antifraud.dto.StuPortraitDto;
import com.mossflower.antifraud.entity.Portrait;
import com.mossflower.antifraud.student.service.StudentPortraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/8/1 20:19
 */
@RestController
@RequestMapping("/stus/portrait")
public class StudentPortraitController {

    @Autowired
    private StudentPortraitService studentPortraitService;

    @GetMapping("/get")
    public R<StuPortraitDto> getPortrait() {
        StuPortraitDto portrait = studentPortraitService.getPortrait();
        return R.ok("查询成功", portrait);
    }

}
