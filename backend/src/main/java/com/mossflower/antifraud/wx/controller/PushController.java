package com.mossflower.antifraud.wx.controller;

import com.mossflower.antifraud.common.R;
import com.mossflower.antifraud.entity.FraudSubject;
import com.mossflower.antifraud.wx.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/9/21 19:54
 */
@RestController
@RequestMapping("/wx")
public class PushController {

    @Autowired
    private PushService pushService;

    @GetMapping("/push")
    public R<List<FraudSubject>> push() {
        List<FraudSubject> randomFraudSubject = pushService.getRandomFraudSubject();
        return R.ok("获取成功", randomFraudSubject);
    }

}
