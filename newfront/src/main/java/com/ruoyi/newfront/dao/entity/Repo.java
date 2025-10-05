package com.ruoyi.newfront.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.yulichang.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_repo")
public class Repo {

    @TableId(type = IdType.AUTO)
    /**
     * 题库ID     primary key
     */
    private Long id;

    /**
     * 题库名词
     */
    private String repoName;

    /**
     * 创建人ID
     */
    private Integer createId;

    /**
     * 题库介绍
     */
    private String repoDesc;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 题库类型
     */
    private Integer repoType;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 封面
     */
    private String cover;

}
