package com.renzy.award.service.impl;

import com.renzy.award.model.database.User;
import com.renzy.award.dao.UserDao;
import com.renzy.award.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author renzhiyong
 * @since 2018-07-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
