package com.ruoyi.newfront.dto.resp;

import com.ruoyi.newfront.dao.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRespDTO extends User {

        /** 部门名称 */
        private String deptName;



        public UserRespDTO(User user) {
            super(user);
        }
}
