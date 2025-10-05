package com.ruoyi.newfront.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepoRespDTO {
    private int id;

    private String repoName;


    private String createId;

    private String createName;

    /** 题库介绍 */
    private String repoDesc;

    /** 更新时间 */
    private Date updateTime;

    private String repoType;



}
