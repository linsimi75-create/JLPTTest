package com.ruoyi.newfront.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.newfront.dao.entity.Repo;
import com.ruoyi.newfront.dto.resp.RepoRespDTO;

import java.util.List;

public interface RepoService extends IService<Repo> {


/*获取所有题库*/

    List<RepoRespDTO> selectAllRepo();
}
