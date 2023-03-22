package com.mossflower.antifraud.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mossflower.antifraud.admin.service.AdminService;
import com.mossflower.antifraud.common.G;
import com.mossflower.antifraud.entity.Admin;
import com.mossflower.antifraud.mapper.AdminMapper;
import com.mossflower.antifraud.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * @author z's'b
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public String login(String adminCard, String password) {
        HashMap<String, String> map = new HashMap<>();
        LambdaQueryWrapper<Admin> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Admin::getAdminCard, adminCard).eq(Admin::getPassword, password);
        Admin selectAdmin = adminMapper.selectOne(lqw);
        if (selectAdmin != null) {
            map.put("adminId", selectAdmin.getId().toString());
            return JwtUtil.generateToken(map);
        }
        return null;
    }

    @Override
    public Boolean logout() {
        String adminId = G.getDecodeJwt().getClaim("adminId").asString();
        Admin admin = adminMapper.selectById(adminId);
        G.removeDecodeJwt();
        return admin != null;
    }
}