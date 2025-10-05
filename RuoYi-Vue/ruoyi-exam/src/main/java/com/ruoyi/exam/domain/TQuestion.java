package com.ruoyi.exam.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 题目对象 t_question
 * 
 * @author Jacks
 * @date 2025-10-05
 */
public class TQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 题目ID */
    @Excel(name = "题目ID")
    private Long id;

    /** 题库ID */
    @Excel(name = "题库ID")
    private Long repoId;

    /** 1单选 2多选 3判断 */
    @Excel(name = "1单选 2多选 3判断")
    private Integer questionType;

    /** 题干 */
    private String stem;

    /** 标准答案 */
    private String answer;

    /** 解析 */
    private String analysis;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer deleted;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long createId;

    /** 正确率 */
    @Excel(name = "正确率")
    private BigDecimal accuracyRate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setRepoId(Long repoId) 
    {
        this.repoId = repoId;
    }

    public Long getRepoId() 
    {
        return repoId;
    }

    public void setQuestionType(Integer questionType) 
    {
        this.questionType = questionType;
    }

    public Integer getQuestionType() 
    {
        return questionType;
    }

    public void setStem(String stem) 
    {
        this.stem = stem;
    }

    public String getStem() 
    {
        return stem;
    }

    public void setAnswer(String answer) 
    {
        this.answer = answer;
    }

    public String getAnswer() 
    {
        return answer;
    }

    public void setAnalysis(String analysis) 
    {
        this.analysis = analysis;
    }

    public String getAnalysis() 
    {
        return analysis;
    }

    public void setDeleted(Integer deleted) 
    {
        this.deleted = deleted;
    }

    public Integer getDeleted() 
    {
        return deleted;
    }

    public void setCreateId(Long createId) 
    {
        this.createId = createId;
    }

    public Long getCreateId() 
    {
        return createId;
    }

    public void setAccuracyRate(BigDecimal accuracyRate) 
    {
        this.accuracyRate = accuracyRate;
    }

    public BigDecimal getAccuracyRate() 
    {
        return accuracyRate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("repoId", getRepoId())
            .append("questionType", getQuestionType())
            .append("stem", getStem())
            .append("answer", getAnswer())
            .append("analysis", getAnalysis())
            .append("deleted", getDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createId", getCreateId())
            .append("accuracyRate", getAccuracyRate())
            .toString();
    }
}
