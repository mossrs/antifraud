package com.mossflower.antifraud.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.mossflower.antifraud.common.Constant;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/8/1 17:40
 */
@Data
@Table(value = "t_portrait", comment = "用户画像表")
public class Portrait implements Serializable {

    public static final long serialVersionUID = 1L;
    @Column(comment = "主键", isKey = true, isAutoIncrement = true)
    private Long id;

    @Column(comment = "学号", isNull = false)
    private String studentCard;

    @Column(comment = "性格集合")
    private String personality;

    @Column(comment = "诈骗标签集合", length = 2048)
    private String fraudTags;

    @Column(comment = "容易被诈骗的集合")
    private String fraudTypes;

    @Column(comment = "是否容易被诈骗")
    private Boolean isFraud;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.INSERT, select = false)
    @Column(comment = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.UPDATE, select = false)
    @Column(comment = "更新时间")
    private LocalDateTime updateTime;

}
