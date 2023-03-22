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
 * @date 2022/8/13 17:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_stu_clazz", comment = "学生班级表")
public class StuClazz  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(comment = "主键", isKey = true, isAutoIncrement = true)
    private Long id;

    @Column(comment = "班级名称")
    private String name;

    @Column(comment = "班级人数")
    private Integer num;

    @Column(comment = "班级累计答题数")
    private Long fraudSubjectNum;

    @Column(comment = "班级被诈骗人数")
    private Integer fraudNum;

    @Column(comment = "班级诈骗预警等级")
    private String antiFraudLevel;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.INSERT, select = false)
    @Column(comment = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.UPDATE, select = false)
    @Column(comment = "更新时间")
    private LocalDateTime updateTime;
}
