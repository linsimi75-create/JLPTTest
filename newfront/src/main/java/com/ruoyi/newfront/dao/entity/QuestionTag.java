package com.ruoyi.newfront.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_question_tag")
public class QuestionTag {

    @TableId(type = IdType.AUTO)
    /**
     * tag_id
     */
    private Long tagId;

    /**
     * tag
     */
    private String tag;

}
