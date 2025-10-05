package com.ruoyi.exam.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.TQuestionMapper;
import com.ruoyi.exam.domain.TQuestion;
import com.ruoyi.exam.service.ITQuestionService;

/**
 * 题目Service业务层处理
 * 
 * @author Jacks
 * @date 2025-10-05
 */
@Service
public class TQuestionServiceImpl implements ITQuestionService 
{
    @Autowired
    private TQuestionMapper tQuestionMapper;

    /**
     * 查询题目
     * 
     * @param id 题目主键
     * @return 题目
     */
    @Override
    public TQuestion selectTQuestionById(Long id)
    {
        return tQuestionMapper.selectTQuestionById(id);
    }

    /**
     * 查询题目列表
     * 
     * @param tQuestion 题目
     * @return 题目
     */
    @Override
    public List<TQuestion> selectTQuestionList(TQuestion tQuestion)
    {
        return tQuestionMapper.selectTQuestionList(tQuestion);
    }

    /**
     * 新增题目
     * 
     * @param tQuestion 题目
     * @return 结果
     */
    @Override
    public int insertTQuestion(TQuestion tQuestion)
    {
        tQuestion.setCreateTime(DateUtils.getNowDate());
        return tQuestionMapper.insertTQuestion(tQuestion);
    }

    /**
     * 修改题目
     * 
     * @param tQuestion 题目
     * @return 结果
     */
    @Override
    public int updateTQuestion(TQuestion tQuestion)
    {
        tQuestion.setUpdateTime(DateUtils.getNowDate());
        return tQuestionMapper.updateTQuestion(tQuestion);
    }

    /**
     * 批量删除题目
     * 
     * @param ids 需要删除的题目主键
     * @return 结果
     */
    @Override
    public int deleteTQuestionByIds(Long[] ids)
    {
        return tQuestionMapper.deleteTQuestionByIds(ids);
    }

    /**
     * 删除题目信息
     * 
     * @param id 题目主键
     * @return 结果
     */
    @Override
    public int deleteTQuestionById(Long id)
    {
        return tQuestionMapper.deleteTQuestionById(id);
    }
}
