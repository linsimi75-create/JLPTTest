package com.ruoyi.newfront.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.newfront.dao.entity.Question;
import com.ruoyi.newfront.dto.resp.QuestionRespDTO;

import java.util.List;

public interface QuestionService extends IService<Question> {

    /**
     * 根据题库ID查询题目
     * @param repoId 题库ID
     * @return 题目列表
     */
    List<QuestionRespDTO> listByRepoId(Long repoId);

}
