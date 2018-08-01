package com.renzy.award.award.service.impl;

import com.renzy.award.award.model.database.Record;
import com.renzy.award.award.dao.RecordDao;
import com.renzy.award.award.service.RecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 中奖纪录 服务实现类
 * </p>
 *
 * @author renzhiyong
 * @since 2018-07-17
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordDao, Record> implements RecordService {

}
