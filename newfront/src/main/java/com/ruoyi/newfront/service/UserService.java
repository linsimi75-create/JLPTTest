package com.ruoyi.newfront.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.ruoyi.newfront.dao.entity.User;
import com.ruoyi.newfront.dto.resp.UserRespDTO;


public interface UserService extends IService<User> {

        /**
         * 根据用户ID查询用户信息
         * @param userId 用户ID
         * @return 用户信息
         */
        UserRespDTO selectUserInfo(Integer userId);
}
