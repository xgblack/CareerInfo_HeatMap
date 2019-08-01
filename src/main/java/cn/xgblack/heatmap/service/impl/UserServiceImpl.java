package cn.xgblack.heatmap.service.impl;

import cn.xgblack.heatmap.dao.UserDao;
import cn.xgblack.heatmap.entity.User;
import cn.xgblack.heatmap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小光
 * @date 2019/5/25 21:21
 * className: UserServiceImpl
 * description: User相关的service层接口实现类
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao ;

    @Override
    public User login(User loginUser) {
        //调用dao层的方案
        return dao.findUserByUsernameAndPassword(loginUser);
    }

    /**
     * 验证用户注册方法
     * @param registerUser 根据用户输入，封装的user对象
     * @return boolean 注册是否成功
     */
    @Override
    public boolean regist(User registerUser) {
        //操作的数据库行数
        return dao.insertNewUser(registerUser);


    }

    @Override
    public boolean usernameIsExist(User registerUser) {
        List<User> users = dao.findUserByUsername(registerUser.getUsername());
        if (users.size() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
