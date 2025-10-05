package com.ruoyi.newfront.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ruoyi.newfront.dao.entity.Repo;
import com.ruoyi.newfront.dao.entity.User;
import com.ruoyi.newfront.dao.mapper.RepoMapper;
import com.ruoyi.newfront.dto.resp.RepoRespDTO;
import com.ruoyi.newfront.service.RepoService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RepoServiceImpl extends ServiceImpl<RepoMapper, Repo> implements RepoService {

    @Override
    public List<RepoRespDTO> selectAllRepo() {

        MPJLambdaWrapper<Repo> wrapper = new MPJLambdaWrapper<>(Repo.class)
                .selectAll(Repo.class)
                .leftJoin(User.class, User::getUserId, Repo::getCreateId)
                .select("t1.user_name AS createName");

        List<RepoRespDTO> repoRespDTOList = baseMapper.selectJoinList(RepoRespDTO.class, wrapper);


        return repoRespDTOList;
    }
}
