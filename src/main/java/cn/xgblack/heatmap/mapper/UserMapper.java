package cn.xgblack.heatmap.mapper;

import cn.xgblack.heatmap.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 小光
 * @date 2019/5/25 21:22
 * Copyright(C),2018-2019,https://blog.xgblack.cn
 * interfaceName: UserDao
 * description:
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return List<User>
     */
    List<User> findUserByUsername(String username);

    /**
     * 通过用户名密码查询用户
     * @param loginUser
     * @return User
     */
    User findUserByUsernameAndPassword(User loginUser);

    /**
     * 插入用户方法
     * @param registerUser
     * @return rows操作数据库的条数（期望其为1）
     */
    boolean insertNewUser(User registerUser);


}
