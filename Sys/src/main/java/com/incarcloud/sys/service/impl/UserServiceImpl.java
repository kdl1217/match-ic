package com.incarcloud.sys.service.impl;

import com.incarcloud.sys.entity.User;
import com.incarcloud.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基础信息表 服务实现类
 * </p>
 *
 * @author bzheng
 * @since 2019-11-28
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String test(String username) {
        Query query=new Query(Criteria.where("useruame").is(username));
        User user =  mongoTemplate.findOne(query , User.class);
        return "hello";
    }

    /**
     * 创建对象
     * @param user
     */
    @Override
    public void saveUser(User user) {
        User save = mongoTemplate.save(user);
    }

    /**
     * 根据用户名查询对象
     * @param userName
     * @return
     */
    @Override
    public User findUserByUserName(String userName) {
        Query query=new Query(Criteria.where("username").is(userName));
        User user =  mongoTemplate.findOne(query , User.class);
        return user;
    }

    /**
     * 更新对象
     * @param user
     */
    @Override
    public void updateUser(User user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("username", user.getUsername()).set("password", user.getPassword());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,User.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,User.class);
    }

    /**
     * 删除对象
     * @param id
     */
    @Override
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,User.class);
    }
}
