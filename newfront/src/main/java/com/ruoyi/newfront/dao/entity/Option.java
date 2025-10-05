package com.ruoyi.newfront.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Option {

    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    private Long id;

    /**
     * question_id
     */
    private Long questionId;

    /**
     * option_label
     */
    private String optionLabel;

    /**
     * content
     */
    private String content;

    /**
     * sort_order
     */
    private Integer sortOrder;

}