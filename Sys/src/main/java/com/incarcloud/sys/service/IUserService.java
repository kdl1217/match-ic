package com.incarcloud.sys.service;

import com.incarcloud.sys.entity.User;

/**
 * <p>
 * 用户基础信息表 服务类
 * </p>
 *
 * @author bzheng
 * @since 2019-11-28
 */
public interface IUserService {

    String test(String username);

    void saveUser(User user);

    User findUserByUserName(String userName);

    void updateUser(User user);

    void deleteUserById(Long id);
}
