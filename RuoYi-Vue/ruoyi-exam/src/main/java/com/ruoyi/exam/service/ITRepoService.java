package com.ruoyi.exam.service;

import java.util.List;
import com.ruoyi.exam.domain.TRepo;

/**
 * 题库Service接口
 * 
 * @author Jacks
 * @date 2025-10-05
 */
public interface ITRepoService 
{
    /**
     * 查询题库
     * 
     * @param id 题库主键
     * @return 题库
     */
    public TRepo selectTRepoById(Long id);

    /**
     * 查询题库列表
     * 
     * @param tRepo 题库
     * @return 题库集合
     */
    public List<TRepo> selectTRepoList(TRepo tRepo);

    /**
     * 新增题库
     * 
     * @param tRepo 题库
     * @return 结果
     */
    public int insertTRepo(TRepo tRepo);

    /**
     * 修改题库
     * 
     * @param tRepo 题库
     * @return 结果
     */
    public int updateTRepo(TRepo tRepo);

    /**
     * 批量删除题库
     * 
     * @param ids 需要删除的题库主键集合
     * @return 结果
     */
    public int deleteTRepoByIds(Long[] ids);

    /**
     * 删除题库信息
     * 
     * @param id 题库主键
     * @return 结果
     */
    public int deleteTRepoById(Long id);
}
