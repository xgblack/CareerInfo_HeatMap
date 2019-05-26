package cn.xgblack.heatmap.dao;

import cn.xgblack.heatmap.domain.User;
import cn.xgblack.heatmap.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author 小光
 * @date 2019/5/25 21:22
 * className: UserDaoImpl
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 通过用户名密码查询用户
     * @param loginUser
     * @return User
     */
    @Override
    public User findUserByUsernameAndPassword(User loginUser) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ? ;";
        try {
            User user = template.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(),
                    loginUser.getPassword()
            );
            return user;
        } catch (DataAccessException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * 插入用户方法
     * @param registerUser
     * @return rows操作数据库的条数（期望其为1）
     */
    @Override
    public int insertNewUser(User registerUser) {

        System.out.println(registerUser);

        String sql = "INSERT INTO user VALUES(null,?,?,?,?,?,?);";

        //SQL参数
        Object[] params = {
                registerUser.getUsername(),
                registerUser.getPassword(),
                registerUser.getEmail(),
                registerUser.getPhone(),
                registerUser.getGender(),
                registerUser.getBirthday()
        };

        int rows = template.update(sql, params);
        return rows;
    }

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return list.size() 查询到的条数
     */
    @Override
    public int findUserByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ? ;";
        List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class),username);
        return list.size();
    }
}
