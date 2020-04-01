package com.ashok.blog_interface.user;

import com.ashok.user.input.UserAddInputParameter;
import com.ashok.user.output.UserAddOutputParameter;

/**
 * Description: 用户接口
 * Author: xk
 * Date: 2020/3/8 23:29
 */
public interface UserInterface {

    public UserAddOutputParameter UserAdd(UserAddInputParameter input);
}
