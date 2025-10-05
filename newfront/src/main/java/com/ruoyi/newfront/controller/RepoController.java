package com.ruoyi.newfront.controller;

import com.ruoyi.newfront.dto.resp.RepoRespDTO;
import com.ruoyi.newfront.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/repo")
public class RepoController {

    @Autowired
    private RepoService repoService;

    @RequestMapping("/list")
    public List<RepoRespDTO> list() {
        return repoService.selectAllRepo();
    }

}
