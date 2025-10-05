package com.ruoyi.newfront.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dept")
public class Dept {
    /**
     * 部门ID
     */
    @TableId(type = IdType.AUTO)
    private Integer deptId;

    /**
     * 部门名称
     */
    private String deptName;

}
