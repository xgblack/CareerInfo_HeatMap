package cn.xgblack.heatmap.service;


import cn.xgblack.heatmap.entity.User;

/**
 * @author 小光
 * @date 2019/5/25 21:21
 * Copyright(C),2018-2019,https://blog.xgblack.cn
 * interfaceName: UserService
 * description:
 */
public interface UserService {

    /**
     * 查看用户名是否已经被注册
     * @param username  用户名
     * @return boolean  是否已经存在
     */
    boolean usernameIsExist(String username);

    /**
     * 验证用户登录方法
     * @param loginUser 根据用户输入的用户名密码封装的user对象（不一定是否正确）
     * @return User user 登陆成功，返回封装好的user
     */
    User login(User loginUser);


    /**
     * 验证用户注册方法
     * @param registerUser 根据用户输入，封装的user对象
     * @return boolean 注册是否成功
     */
    boolean regist(User registerUser);


}
