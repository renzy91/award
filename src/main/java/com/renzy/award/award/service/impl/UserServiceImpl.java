package com.renzy.award.award.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.renzy.award.award.dao.UserDao;
import com.renzy.award.award.model.database.Award;
import com.renzy.award.award.model.database.Phase;
import com.renzy.award.award.model.database.User;
import com.renzy.award.award.service.AwardService;
import com.renzy.award.award.service.PhaseService;
import com.renzy.award.award.service.UserService;
import com.renzy.award.common.context.Constants;
import com.renzy.award.common.exception.BaseException;
import com.renzy.award.common.utils.RedisCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author renzhiyong
 * @since 2018-07-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
