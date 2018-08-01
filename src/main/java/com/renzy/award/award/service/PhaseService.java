package com.renzy.award.award.service;

import com.renzy.award.award.model.database.Phase;
import com.baomidou.mybatisplus.service.IService;
import com.renzy.award.common.model.BaseResponse;

/**
 * <p>
 * 抽奖期数表 服务类
 * </p>
 *
 * @author renzhiyong
 * @since 2018-07-17
 */
public interface PhaseService extends IService<Phase> {
    BaseResponse<Boolean> add(Phase phase);
    void publish(Integer phaseId);
}
