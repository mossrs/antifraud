package com.mossflower.antifraud.admin.service;

import java.util.List;
import java.util.Map;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/8/11 17:01
 */
public interface AdminStatisticsService {

    /**
     * 块一
     * 统计学生总数
     * @return Long 统计数量
     */
    Long countStu();

    /**
     * 块二
     * 统计被诈骗的学生总数
     * @return Long 统计数量
     */
    Long countFraudStu();

    /**
     * 块三
     * 统计推送题目总数量
     * @return Long 统计数量
     */
    Long countFraudSubject();

    /**
     * 块四
     * 统计人均完成题目数量
     * @return Long 统计数量
     */
    Long countFinishedFraudSubject();

    /**
     * 分析并返回各诈骗类型的预警程度(优秀，良好，一般，警告，危险)
     * @return Map<String, Long> 各诈骗类型的预警程度
     */
    Map<String, String> countFraudLevel();

    /**
     * 折线图
     * 统计一周的答题走势
     * @return List<Long> 统计一周的答题走势
     */
    List<List<Long>> countMonthFraudSubjectTrend();

    /**
     * 柱状图
     * 统计各诈骗类型上当受骗的男女人数
     * @return Map<String, Map<String, Long>> 各诈骗类型上当受骗的男女人数
     */
    Map<String, Map<String, Long>> countFraudSexRatio();

    /**
     * 饼图 内圈受骗人数 和 外圈未受骗人数
     * 统计各类型受骗和未受骗的人数比例  百分数字符串
     * @return Map<String, List<Long>> 各类型受骗和未受骗的人数比例
     */
    Map<String, List<Long>> countFraud2AntiFraudRatio();


}
