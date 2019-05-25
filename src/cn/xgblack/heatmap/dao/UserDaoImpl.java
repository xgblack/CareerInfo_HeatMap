package cn.xgblack.heatmap.dao;

import cn.xgblack.heatmap.domain.User;
import cn.xgblack.heatmap.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
}
