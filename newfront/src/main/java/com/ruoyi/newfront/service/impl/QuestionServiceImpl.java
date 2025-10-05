package com.ruoyi.newfront.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ruoyi.newfront.dao.entity.Question;
import com.ruoyi.newfront.dao.entity.User;
import com.ruoyi.newfront.dao.mapper.QuestionMapper;
import com.ruoyi.newfront.dto.resp.QuestionRespDTO;
import com.ruoyi.newfront.service.QuestionService;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {
    private final QuestionMapper questionMapper;

    public QuestionServiceImpl(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Override
    public List<QuestionRespDTO> listByRepoId(Long repoId) {

        return questionMapper.selectListWithTagsAndOptions(repoId);
    }
}
