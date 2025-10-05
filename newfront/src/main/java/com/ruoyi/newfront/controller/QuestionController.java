package com.ruoyi.newfront.controller;

import com.ruoyi.newfront.dao.entity.Question;
import com.ruoyi.newfront.dto.resp.QuestionRespDTO;
import com.ruoyi.newfront.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.common.controller.BaseController;

import java.util.List;


@RestController
@RequestMapping("/question")
public class QuestionController extends BaseController {


    @Autowired
    private QuestionService questionService;

   /**
    * 根据题库ID查询题目列表
    */
    @GetMapping("/list")
    public List<QuestionRespDTO> list(@RequestParam("repoId") Long repoId) {
        return questionService.listByRepoId(repoId);
    }
}
