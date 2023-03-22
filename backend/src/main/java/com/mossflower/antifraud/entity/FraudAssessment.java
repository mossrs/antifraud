package com.mossflower.antifraud.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.mossflower.antifraud.common.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/17 15:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_fraud_assessment", comment = "诈骗评估表")
public class FraudAssessment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id", comment = "主键", isKey = true, isAutoIncrement = true)
    private Long id;

    @Column(comment = "学号", isNull = false)
    private String studentCard;

    @Column(comment = "诈骗类型")
    private String fraudType;

    @Column(comment = "诈骗场景选择犹豫时长", decimalLength = 6)
    private Double hesitateTime;

    @Column(comment = "诈骗结果")
    private Boolean fraudResult;

    @Column(comment = "该类型被诈骗的题目标签字符串('，'分割)", length = 1024)
    private String fraudTags;

    @Column(comment = "防诈骗题目得分")
    private Integer antiFraudScore;

    @Column(comment = "是否删除(0删1无)", defaultValue = "0")
    private Boolean isDelete;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.INSERT, select = false)
    @Column(comment = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.UPDATE, select = false)
    @Column(comment = "更新时间")
    private LocalDateTime updateTime;


}
