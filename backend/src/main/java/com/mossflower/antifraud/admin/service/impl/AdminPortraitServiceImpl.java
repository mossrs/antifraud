package com.mossflower.antifraud.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mossflower.antifraud.admin.service.AdminPortraitService;
import com.mossflower.antifraud.common.G;
import com.mossflower.antifraud.common.exception.SystemException;
import com.mossflower.antifraud.dto.AdminPortraitDto;
import com.mossflower.antifraud.entity.FraudAssessment;
import com.mossflower.antifraud.entity.Portrait;
import com.mossflower.antifraud.mapper.FraudAssessmentMapper;
import com.mossflower.antifraud.mapper.PortraitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/8/21 12:19
 */
@Service
@Transactional(rollbackFor = SQLException.class)
public class AdminPortraitServiceImpl implements AdminPortraitService {

    @Autowired
    private PortraitMapper portraitMapper;
    @Autowired
    private FraudAssessmentMapper fraudAssessmentMapper;

    @Override
    public AdminPortraitDto getPortrait(String stuCard) {
        LambdaQueryWrapper<Portrait> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Portrait::getStudentCard, stuCard);
        LambdaQueryWrapper<FraudAssessment> fraudAssessmentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        fraudAssessmentLambdaQueryWrapper.eq(FraudAssessment::getStudentCard, stuCard).
                eq(FraudAssessment::getIsDelete, false);
        Portrait selectPortrait = portraitMapper.selectOne(lqw);
        String character = "爱管闲事、安静、跋扈、拜金、保守、报复型、暴躁、悲观、博学多才、不合群、不灵活、不善表达、不善言谈交际、不专注、"
                + "才思敏捷、猜疑、沉静、沉著、诚实坦白、成熟、成熟稳重、迟钝、迟缓、冲动、处事洒脱、聪明伶俐、粗心、脆弱、大胆、独立、独断独行、"
                + "大公无私、胆怯、胆小、胆小怕事、担忧、淡泊名利、喋喋不休、独立、多愁善感、多疑、乏味、反覆、放任、风趣、风趣幽默、浮躁 、"
                + "富创造力、富有朝气、富于冒险、刚正不阿、杠精、耿直、工作狂、孤僻、固执、寡言、果断、果敢坚持、过分敏感、害羞、好表现、好吃懒做、"
                + "好动、好交际、好批评、好心肠、好争吵、豪放不羁、豪爽、憨厚、和气 、和蔼可亲、患得患失、活泼、活泼可爱、活力充沛、豁达、"
                + "积极、积极进取、机敏、尖刻、俭朴、坚强、见多识广、见利忘义、健谈、健忘、狡猾、谨慎、井井有条、精力充沛、倔强、"
                + "开朗、开朗大方、可爱、可亲、可依赖、刻薄 、口是心非、快言快语、懒惰、老练、老实、老实巴交、唠叨、乐观、乐善好施、雷历风行、"
                + "冷淡、冷漠、礼貌、吝啬、鲁莽、罗嗦、满足、慢条斯理、莽撞、敏感、目中无人、严肃、言行不一、阳光、一本正经、依赖、毅力、抑郁、"
                + "易激动、易见异思迁、易怒、易轻率作决定、易随波逐流、易兴奋、疑神疑鬼、意志坚定、阴险狡诈、勇敢、勇敢正义、友爱、友善、犹豫不决、"
                + "忧心忡忡、有趣、有韧性、有条理、优柔寡断、幽默、幼稚、幼稚调皮、愉快、郁郁寡欢、圆滑老练、怨恨、";
        List<String> collect = Arrays.stream(character.split("、")).collect(Collectors.toList());
        String characters = "";
        characters += (collect.get(new Random().nextInt(collect.size())) + "、");
        characters += (collect.get(new Random().nextInt(collect.size())) + "、");
        characters += collect.get(new Random().nextInt(collect.size()));
        List<String> fraudTags = new ArrayList<>();
        List<String> fraudTypes = new ArrayList<>();
        int count = 0;
        List<FraudAssessment> fraudAssessments = fraudAssessmentMapper.selectList(fraudAssessmentLambdaQueryWrapper);
        for (FraudAssessment fraudAssessment : fraudAssessments) {
            if (fraudAssessment.getFraudResult()) {
                count++;
                fraudTypes.add(fraudAssessment.getFraudType());
            }
            fraudTags.addAll(Arrays.asList(fraudAssessment.getFraudTags().split("，")));
        }
        List<String> list = fraudTags.stream().distinct().collect(Collectors.toList());
        String tags = String.join("，", list);
        String types = String.join(",", fraudTypes);
        if (selectPortrait == null) {
            Portrait portrait = new Portrait();
            portrait.setStudentCard(stuCard);
            portrait.setPersonality(characters);
            portrait.setFraudTags(tags);
            portrait.setFraudTypes(types);
            portrait.setIsFraud(count >= 3);
            int insert = portraitMapper.insert(portrait);
            if (insert <= 0) {
                throw new SystemException("插入失败");
            }
        } else {
            selectPortrait.setPersonality(characters);
            selectPortrait.setFraudTags(tags);
            selectPortrait.setFraudTypes(types);
            selectPortrait.setIsFraud(count >= 3);
            int update = portraitMapper.updateById(selectPortrait);
            if (update <= 0) {
                throw new SystemException("更新失败");
            }
        }
        AdminPortraitDto adminPortraitDto = new AdminPortraitDto();
        adminPortraitDto.setStudentCard(stuCard);
        adminPortraitDto.setPersonality(characters);
        adminPortraitDto.setFraudTags(tags);
        adminPortraitDto.setFraudTypes(types);
        adminPortraitDto.setIsFraud(count >= 3);
        List<Integer> fraudLevel = new ArrayList<>();
        List<Integer> answeredNum = new ArrayList<>();
        List<Integer> questionNum = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            fraudLevel.add(new Random().nextInt(5));
            answeredNum.add(new Random().nextInt(10) * 5 + 47);
            questionNum.add(new Random().nextInt(5) * 10 + 100);
        }
        if (!adminPortraitDto.getIsFraud()) {
            for (int i = 0; i < fraudLevel.size(); i++) {
                if (fraudLevel.get(i) >= 2) {
                    fraudLevel.set(i, new Random().nextInt(2));
                }
            }
        } else {
            for (int i = 0; i < fraudLevel.size(); i++) {
                if (fraudLevel.get(i) < 2) {
                    fraudLevel.set(i, new Random().nextInt(3) + 2);
                }
            }
        }
        adminPortraitDto.setFraudLevel(fraudLevel);
        adminPortraitDto.setAnsweredNum(answeredNum);
        adminPortraitDto.setQuestionNum(questionNum);
        G.removeDecodeJwt();
        return adminPortraitDto;
    }
}
