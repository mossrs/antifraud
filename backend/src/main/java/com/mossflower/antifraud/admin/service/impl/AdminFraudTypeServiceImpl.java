package com.mossflower.antifraud.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mossflower.antifraud.admin.service.AdminFraudTypeService;
import com.mossflower.antifraud.common.G;
import com.mossflower.antifraud.entity.FraudTag;
import com.mossflower.antifraud.entity.FraudType;
import com.mossflower.antifraud.mapper.FraudTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/29 11:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminFraudTypeServiceImpl implements AdminFraudTypeService {

    @Autowired
    private FraudTypeMapper fraudTypeMapper;

    @Override
    public List<String> getFraudTypeList() {
        QueryWrapper<FraudType> qw = new QueryWrapper<>();
        qw.select("type_name").orderByDesc("id");
        List<FraudType> fraudTypes = fraudTypeMapper.selectList(qw);
        G.removeDecodeJwt();
        return fraudTypes.stream().map(FraudType::getTypeName).collect(Collectors.toList());
    }
}
