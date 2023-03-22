package com.mossflower.antifraud.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.mossflower.antifraud.common.Constant;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/15 11:05
 */
@Data
@Table(name = "t_fraud_subject", comment = "诈骗题目表")
@Validated
public class FraudSubject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(comment = "主键自增", isAutoIncrement = true, isKey = true)
    private Long id;

    @Column(comment = "诈骗题目名称")
    private String fraudSubjectName;

    @Column(comment = "诈骗题目类型")
    private String fraudType;

    @Column(comment = "诈骗题目选择项类型(多选、单选)")
    private String fraudOptionType;

    @Column(comment = "诈骗题目标签关键字")
    private String fraudTag;

    @Column(comment = "选项A")
    private String aOption;

    @Column(comment = "选项B")
    private String bOption;

    @Column(comment = "选项C")
    private String cOption;

    @Column(comment = "选项D")
    private String dOption;

    @Column(comment = "正确答案")
    private String answer;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.INSERT, select = false)
    @Column(comment = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.UPDATE, select = false)
    @Column(comment = "更新时间")
    private LocalDateTime updateTime;
}
