package com.mossflower.antifraud.admin.controller;

import com.mossflower.antifraud.common.R;
import com.mossflower.antifraud.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author z's'b
 * @version 1.0
 */
@RestController
@RequestMapping("/admins")
@Validated
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public R<String> login(String adminCard, String password) {
        String token = adminService.login(adminCard, password);
        return token == null ? R.err("登录失败") : R.ok("登录成功", token);
    }

    @GetMapping("/logout")
    public R<String> logout() {
        Boolean flag = adminService.logout();
        return flag ? R.ok("退出成功") : R.err("退出失败");
    }

}
