package com.ruoyi.newfront.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Lang;
import xin.altitude.cms.common.entity.BaseEntity;

import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@TableName("t_question")
public class Question  {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 题库ID
     */
    private Long repoId;

    /**
     * 1单选 2多选 3判断
     */
    private Integer questionType;

    /**
     * 题干
     */
    private String stem;

    /**
     * 标准答案
     */
    private String answer;

    /**
     * 解析
     */
    private String analysis;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private Long createId;

    /**
     * 正确率
     */
    private Double accuracyRate;

    public Question(Long repoId, Integer questionType, String stem, String answer, String analysis, Integer deleted, Date createTime, Date updateTime, Long createId, Double accuracyRate) {
        this.repoId = repoId;
        this.questionType = questionType;
        this.stem = stem;
        this.answer = answer;
        this.analysis = analysis;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createId = createId;
        this.accuracyRate = accuracyRate;
    }


}
