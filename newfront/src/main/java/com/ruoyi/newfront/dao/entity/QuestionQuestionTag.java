package com.ruoyi.newfront.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_question_question_tag")
public class QuestionQuestionTag {
    /**
     * id
     */
    private Integer id;

    /**
     * question_id
     */
    private Integer questionId;

    /**
     * tag_id
     */
    private Integer tagId;
}
