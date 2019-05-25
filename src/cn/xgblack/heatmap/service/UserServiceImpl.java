package cn.xgblack.heatmap.service;

import cn.xgblack.heatmap.dao.UserDao;
import cn.xgblack.heatmap.dao.UserDaoImpl;
import cn.xgblack.heatmap.domain.User;

/**
 * @author 小光
 * @date 2019/5/25 21:21
 * className: UserServiceImpl
 * description: User相关的service层接口实现类
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class UserServiceImpl implements UserService{
    private UserDao dao = new UserDaoImpl();
    @Override
    public User login(User loginUser) {
        //调用dao层的方案
        return dao.findUserByUsernameAndPassword(loginUser);
    }
}
