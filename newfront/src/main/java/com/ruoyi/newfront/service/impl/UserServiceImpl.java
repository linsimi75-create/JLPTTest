package com.ruoyi.newfront.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.newfront.dao.entity.Dept;
import com.ruoyi.newfront.dao.entity.User;
import com.ruoyi.newfront.dao.mapper.UserMapper;
import com.ruoyi.newfront.dto.resp.UserRespDTO;
import com.ruoyi.newfront.service.DeptService;
import com.ruoyi.newfront.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;

import java.util.Optional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public UserRespDTO selectUserInfo(Integer userId) {


        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>(User.class)
                        .eq(User::getUserId, userId);

        UserRespDTO userRespDTO = EntityUtils.toObj(getOne(wrapper), UserRespDTO::new);

        Optional.ofNullable(userRespDTO).ifPresent(this::addDeptNameInfo);

        return userRespDTO;
    }

    @Autowired
    private DeptService deptService;

    private void addDeptNameInfo(UserRespDTO userRespDTO) {
        LambdaQueryWrapper<Dept> wrapper = new LambdaQueryWrapper<>(Dept.class)
                        .eq(Dept::getDeptId,userRespDTO.getUserId());

        Dept dept=deptService.getOne(wrapper);

        Optional.ofNullable(dept).ifPresent(e->userRespDTO.setDeptName(e.getDeptName()));

    }


}
