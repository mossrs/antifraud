package com.mossflower.antifraud.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mossflower.antifraud.common.G;
import com.mossflower.antifraud.entity.FraudAssessment;
import com.mossflower.antifraud.mapper.FraudAssessmentMapper;
import com.mossflower.antifraud.student.service.StudentFraudAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/27 8:56
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class StudentFraudAssessmentServiceImpl implements StudentFraudAssessmentService {

    @Autowired
    private FraudAssessmentMapper fraudAssessmentMapper;

    @Override
    public Boolean saveOne(FraudAssessment fraudAssessment) {
        LambdaQueryWrapper<FraudAssessment> lqw = new LambdaQueryWrapper<>();
        String studentCard = G.getDecodeJwt().getClaim("studentCard").asString();
        lqw.eq(FraudAssessment::getStudentCard, studentCard)
                .eq(FraudAssessment::getFraudType, fraudAssessment.getFraudType())
                .eq(FraudAssessment::getIsDelete, false);
        FraudAssessment oldFa = fraudAssessmentMapper.selectOne(lqw);
        if (oldFa != null) {
            oldFa.setIsDelete(true);
            fraudAssessmentMapper.updateById(oldFa);
        }
        String tags = "碰瓷', '网购', '信用卡', '网游', '路边搭讪', '熟人', '网络', '电话', '身份证', '政府', '防范', '客服聊天', '公安', '暑期工', '中介', '短信', '改签', '机票', '链接', '网聊', '验证码', '客服电话', '中奖', '学分', '央行', '免费', '司法', '办理信用卡', '网贷', '网银', '转账', '网恋', '不明链接', '网上捐款', '女朋友', '微信', '借钱', '理赔', 'QQ', '罚款', '医保', '红包', '投票', '不明文件', '冒充家人', '陌生来电', '刷好评', '快递', '银行', '刷单', '培训班', '上司转账', '演唱会', '返现', '理财', '密码泄露', '税收', '涉黑', '客服', '陌生人搭讪', '广告', '贷款', '网站";
        String newTags =  tags.replaceAll("[',]+", "");
        List<String> asList = Arrays.asList(newTags.split(" "));
        ArrayList<String> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(asList.size() + 1); i++) {
            list.add(asList.get(random.nextInt(asList.size())));
        }
        String tagString = String.join("，", list);
        fraudAssessment.setStudentCard(studentCard);
        fraudAssessment.setFraudTags(tagString);
        fraudAssessment.setAntiFraudScore(random.nextInt(101));
        G.removeDecodeJwt();
        return fraudAssessmentMapper.insert(fraudAssessment) > 0;
    }


}
