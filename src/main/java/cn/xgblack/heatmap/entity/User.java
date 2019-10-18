package cn.xgblack.heatmap.entity;

import lombok.*;

import java.sql.Date;


/**
 * @author 小光
 * @date 2019/5/25 16:36
 * className: User
 * description: 用户表Bean对象
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
@Data
@AllArgsConstructor
public class User {
    /**
     * 主键
     */
    private Integer uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 性别
     */
    private String gender;
    /**
     * 生日
     */
    private Date birthday;

    /**
     * 头像地址
     */
    private String avatar;

}
