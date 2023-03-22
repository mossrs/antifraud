package com.mossflower.antifraud.admin.controller;

import com.mossflower.antifraud.admin.service.AdminPortraitService;
import com.mossflower.antifraud.common.R;
import com.mossflower.antifraud.dto.AdminPortraitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/8/21 12:33
 */
@RestController
@RequestMapping("/admin/portrait")
public class AdminPortraitController {

    @Autowired
    private AdminPortraitService adminPortraitService;

    @GetMapping("/get")
    public R<AdminPortraitDto> getPortrait(String stuCard) {
        AdminPortraitDto portrait = adminPortraitService.getPortrait(stuCard);
        return R.ok("查询成功", portrait);
    }
}
