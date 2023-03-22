package com.mossflower.antifraud.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mossflower.antifraud.admin.service.AdminFraudTagService;
import com.mossflower.antifraud.common.G;
import com.mossflower.antifraud.entity.FraudTag;
import com.mossflower.antifraud.mapper.FraudTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/29 11:10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminFraudTagServiceImpl implements AdminFraudTagService {

    @Autowired
    private FraudTagMapper fraudTagMapper;

    @Override
    public List<String> getFraudTagList() {
        QueryWrapper<FraudTag> qw = new QueryWrapper<>();
        qw.select("tag_name").orderByDesc("id");
        List<FraudTag> fraudTags = fraudTagMapper.selectList(qw);
        G.removeDecodeJwt();
        return fraudTags.stream().map(FraudTag::getTagName).collect(Collectors.toList());
    }

    @Override
    public Integer addFraudTag(FraudTag fraudTag) {
        LambdaQueryWrapper<FraudTag> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FraudTag::getTagName, fraudTag.getTagName());
        FraudTag tag = fraudTagMapper.selectOne(lqw);
        G.removeDecodeJwt();
        if (tag != null) {
            return 2;
        }
        return fraudTagMapper.insert(fraudTag);
    }
}