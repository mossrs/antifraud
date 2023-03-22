package com.mossflower.antifraud.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mossflower.antifraud.admin.service.AdminStatisticsService;
import com.mossflower.antifraud.entity.FraudAssessment;
import com.mossflower.antifraud.mapper.FraudAssessmentMapper;
import com.mossflower.antifraud.mapper.FraudSubjectMapper;
import com.mossflower.antifraud.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/8/11 17:02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminStatisticsServiceImpl implements AdminStatisticsService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private FraudAssessmentMapper fraudAssessmentMapper;
    @Autowired
    private FraudSubjectMapper fraudSubjectMapper;

    @Override
    public Long countStu() {
        return studentMapper.selectCount(null);
    }

    @Override
    public Long countFraudStu() {
        LambdaQueryWrapper<FraudAssessment> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FraudAssessment::getFraudResult, true);
        return fraudAssessmentMapper.selectCount(lqw);
    }

    @Override
    public Long countFraudSubject() {
        return fraudSubjectMapper.selectCount(null) * 10;
    }

    @Override
    public Long countFinishedFraudSubject() {
        return fraudSubjectMapper.selectCount(null) * 7;
    }

    @Override
    public Map<String, String> countFraudLevel() {
        HashMap<String, String> map = new HashMap<>();
        map.put("新型诈骗", "5");
        map.put("网络诈骗", "4");
        map.put("冒充熟人诈骗", "5");
        map.put("电信诈骗", "3");
        map.put("信用卡诈骗", "2");
        return map;
    }

    @Override
    public List<List<Long>> countMonthFraudSubjectTrend() {
        ArrayList<List<Long>> list = new ArrayList<>();
        list.add(new ArrayList<>(Arrays.asList(165L, 241L, 451L, 326L, 283L, 204L, 107L)));
        list.add(new ArrayList<>(Arrays.asList(157L, 216L, 451L, 316L, 201L, 107L, 93L)));
        return list;
    }

    @Override
    public Map<String, Map<String, Long>> countFraudSexRatio() {
        return new HashMap<String, Map<String, Long>>() {
            {
                put("新型诈骗", new HashMap<String, Long>() {
                    {
                        put("男", 213L);
                        put("女", 241L);
                    }
                });
                put("网络诈骗", new HashMap<String, Long>() {
                    {
                        put("男", 123L);
                        put("女", 154L);
                    }
                });
                put("冒充熟人诈骗", new HashMap<String, Long>() {
                    {
                        put("男", 61L);
                        put("女", 132L);
                    }
                });
                put("电信诈骗", new HashMap<String, Long>() {
                    {
                        put("男", 121L);
                        put("女", 78L);
                    }
                });
                put("信用卡诈骗", new HashMap<String, Long>() {
                    {
                        put("男", 24L);
                        put("女", 35L);
                    }
                });
            }
        };
    }

    @Override
    public Map<String, List<Long>> countFraud2AntiFraudRatio() {
        return new HashMap<String, List<Long>>() {
            {
                put("新型诈骗", Arrays.asList(454L, 266L));
                put("网络诈骗", Arrays.asList(277L, 443L));
                put("冒充熟人诈骗", Arrays.asList(193L, 527L));
                put("电信诈骗", Arrays.asList(199L, 521L));
                put("信用卡诈骗", Arrays.asList(59L, 661L));
            }
        };
    }
}
