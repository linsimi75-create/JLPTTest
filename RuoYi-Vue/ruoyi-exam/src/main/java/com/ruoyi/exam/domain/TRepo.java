package com.ruoyi.exam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 题库对象 t_repo
 * 
 * @author Jacks
 * @date 2025-10-05
 */
public class TRepo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 题库ID */
    private Long id;

    /** 题库名称 */
    @Excel(name = "题库名称")
    private String repoName;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long createId;

    /** 题库介绍 */
    @Excel(name = "题库介绍")
    private String repoDesc;

    /** 题库类型 */
    @Excel(name = "题库类型")
    private Long repoType;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Long deleted;

    /** 封面 */
    private String cover;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setRepoName(String repoName) 
    {
        this.repoName = repoName;
    }

    public String getRepoName() 
    {
        return repoName;
    }

    public void setCreateId(Long createId) 
    {
        this.createId = createId;
    }

    public Long getCreateId() 
    {
        return createId;
    }

    public void setRepoDesc(String repoDesc) 
    {
        this.repoDesc = repoDesc;
    }

    public String getRepoDesc() 
    {
        return repoDesc;
    }

    public void setRepoType(Long repoType) 
    {
        this.repoType = repoType;
    }

    public Long getRepoType() 
    {
        return repoType;
    }

    public void setDeleted(Long deleted) 
    {
        this.deleted = deleted;
    }

    public Long getDeleted() 
    {
        return deleted;
    }

    public void setCover(String cover) 
    {
        this.cover = cover;
    }

    public String getCover() 
    {
        return cover;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("repoName", getRepoName())
            .append("createId", getCreateId())
            .append("repoDesc", getRepoDesc())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("repoType", getRepoType())
            .append("deleted", getDeleted())
            .append("cover", getCover())
            .toString();
    }
}
