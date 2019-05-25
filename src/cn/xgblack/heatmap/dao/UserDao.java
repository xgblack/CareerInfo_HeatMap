package cn.xgblack.heatmap.dao;

import cn.xgblack.heatmap.domain.User;

/**
 * @author 小光
 * @date 2019/5/25 21:22
 * Copyright(C),2018-2019,https://blog.xgblack.cn
 * interfaceName: UserDao
 * description:
 */
public interface UserDao {
    /**
     * 通过用户名密码查询用户
     * @param loginUser
     * @return User
     */
    User findUserByUsernameAndPassword(User loginUser);
}
