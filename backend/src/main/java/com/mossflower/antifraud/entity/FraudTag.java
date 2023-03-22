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
 * @date 2022/7/29 11:01
 */
@Data
@Table(name = "t_fraud_tag", comment = "诈骗标签表")
public class FraudTag implements Serializable {
    public static final long serialVersionUID = 1L;

    @Column(comment = "主键", isKey = true, isAutoIncrement = true)
    private Long id;

    @Column(comment = "标签名称", isNull = false, length = 32)
    private String tagName;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.INSERT, select = false)
    @Column(comment = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.UPDATE, select = false)
    @Column(comment = "更新时间")
    private LocalDateTime updateTime;
}
