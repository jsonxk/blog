package com.ashok.user.input;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * Description:新增用户
 * Author: xk
 * Date: 2020/3/8 23:26
 */
@Setter
@Getter
public class UserAddInputParameter {

    @JSONField(name = "user_name")
    private String userName;

    @JSONField(name = "password")
    private String password;
}
