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

    /**
     * 验证用户注册方法
     * @param registerUser 根据用户输入，封装的user对象
     * @return boolean 注册是否成功
     */
    @Override
    public boolean regist(User registerUser) {
        //操作的数据库行数
        int rows = dao.insertNewUser(registerUser);

        if (rows == 1) {
            //如果行数为1，则注册成功
            return true;
        }else{
            //如果行数为1，则注册成功
            return false;
        }
    }

    @Override
    public boolean usernameIsExist(User registerUser) {
        int rows = dao.findUserByUsername(registerUser.getUsername());
        if (rows == 0) {
            return false;
        } else {
            return true;
        }
    }
}
