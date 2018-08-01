package com.renzy.award.award.service;

import com.baomidou.mybatisplus.service.IService;
import com.renzy.award.award.model.database.Award;
import com.renzy.award.common.model.BaseResponse;

/**
 * <p>
 * 奖品表 服务类
 * </p>
 *
 * @author renzhiyong
 * @since 2018-07-17
 */
public interface AwardService extends IService<Award> {
    BaseResponse<Boolean> draw(Integer userId);
    void initAwardPool(Integer phaseId);
}
