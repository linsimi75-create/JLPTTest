package com.ruoyi.newfront.dto.resp;

import com.ruoyi.newfront.dao.entity.Option;
import com.ruoyi.newfront.dao.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRespDTO extends Question {

    private String createName;

    private List<String> tags;

    private String tagStr;

    private List<Option> options;

    public QuestionRespDTO(Question question) {
        super();
    }

}
