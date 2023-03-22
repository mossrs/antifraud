package com.mossflower.antifraud.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;
import com.mossflower.antifraud.common.Constant;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author z's'b
 */
@Data
@Table(name = "t_student", comment = "用户表")
@Validated
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(comment = "主键", isKey = true, isAutoIncrement = true)
    private Long id;

    @Unique
    @Column(comment = "学号", isNull = false, length = 16)
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "用户名必须为4-16位字母、数字、下划线")
    private String studentCard;

    @TableField(select = false)
    @Column(comment = "密码", length = 16)
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "密码必须为4-16位字母、数字、下划线")
    private String password;

    @Column(comment = "学生姓名", length = 16)
    private String studentName;

    @Column(comment = "性别", length = 8)
    private String sex;

    @Column(comment = "年龄")
    private Integer age;

    @Column(comment = "电话", length = 11)
    private String phone;

    @Column(comment = "邮箱", length = 32)
    private String email;

    @Column(comment = "专业", length = 32)
    private String major;

    @Column(comment = "班级", length = 16)
    private String className;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.INSERT, select = false)
    @Column(comment = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.UPDATE, select = false)
    @Column(comment = "更新时间")
    private LocalDateTime updateTime;
}
