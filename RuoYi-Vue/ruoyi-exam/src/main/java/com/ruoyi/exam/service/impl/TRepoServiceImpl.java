package com.ruoyi.exam.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.TRepoMapper;
import com.ruoyi.exam.domain.TRepo;
import com.ruoyi.exam.service.ITRepoService;

/**
 * 题库Service业务层处理
 * 
 * @author Jacks
 * @date 2025-10-05
 */
@Service
public class TRepoServiceImpl implements ITRepoService 
{
    @Autowired
    private TRepoMapper tRepoMapper;

    /**
     * 查询题库
     * 
     * @param id 题库主键
     * @return 题库
     */
    @Override
    public TRepo selectTRepoById(Long id)
    {
        return tRepoMapper.selectTRepoById(id);
    }

    /**
     * 查询题库列表
     * 
     * @param tRepo 题库
     * @return 题库
     */
    @Override
    public List<TRepo> selectTRepoList(TRepo tRepo)
    {
        return tRepoMapper.selectTRepoList(tRepo);
    }

    /**
     * 新增题库
     * 
     * @param tRepo 题库
     * @return 结果
     */
    @Override
    public int insertTRepo(TRepo tRepo)
    {
        tRepo.setCreateTime(DateUtils.getNowDate());
        return tRepoMapper.insertTRepo(tRepo);
    }

    /**
     * 修改题库
     * 
     * @param tRepo 题库
     * @return 结果
     */
    @Override
    public int updateTRepo(TRepo tRepo)
    {
        tRepo.setUpdateTime(DateUtils.getNowDate());
        return tRepoMapper.updateTRepo(tRepo);
    }

    /**
     * 批量删除题库
     * 
     * @param ids 需要删除的题库主键
     * @return 结果
     */
    @Override
    public int deleteTRepoByIds(Long[] ids)
    {
        return tRepoMapper.deleteTRepoByIds(ids);
    }

    /**
     * 删除题库信息
     * 
     * @param id 题库主键
     * @return 结果
     */
    @Override
    public int deleteTRepoById(Long id)
    {
        return tRepoMapper.deleteTRepoById(id);
    }
}
