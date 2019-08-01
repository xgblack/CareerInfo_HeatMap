package cn.xgblack.heatmap.service.impl;

import cn.xgblack.heatmap.dao.UserDao;
import cn.xgblack.heatmap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



/**
 * 事务配置
 */
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

}
