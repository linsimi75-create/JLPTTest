package com.ruoyi.exam.mapper;

import java.util.List;
import com.ruoyi.exam.domain.TQuestion;

/**
 * 题目Mapper接口
 * 
 * @author Jacks
 * @date 2025-10-05
 */
public interface TQuestionMapper 
{
    /**
     * 查询题目
     * 
     * @param id 题目主键
     * @return 题目
     */
    public TQuestion selectTQuestionById(Long id);

    /**
     * 查询题目列表
     * 
     * @param tQuestion 题目
     * @return 题目集合
     */
    public List<TQuestion> selectTQuestionList(TQuestion tQuestion);

    /**
     * 新增题目
     * 
     * @param tQuestion 题目
     * @return 结果
     */
    public int insertTQuestion(TQuestion tQuestion);

    /**
     * 修改题目
     * 
     * @param tQuestion 题目
     * @return 结果
     */
    public int updateTQuestion(TQuestion tQuestion);

    /**
     * 删除题目
     * 
     * @param id 题目主键
     * @return 结果
     */
    public int deleteTQuestionById(Long id);

    /**
     * 批量删除题目
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTQuestionByIds(Long[] ids);
}
