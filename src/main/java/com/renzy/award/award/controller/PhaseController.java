package com.renzy.award.award.controller;


import com.renzy.award.award.model.PhasePublishReq;
import com.renzy.award.award.model.database.Phase;
import com.renzy.award.award.service.PhaseService;
import com.renzy.award.common.model.BaseResponse;
import com.renzy.award.common.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

/**
 * <p>
 * 抽奖期数表 前端控制器
 * </p>
 *
 * @author renzhiyong
 * @since 2018-07-17
 */
@RestController
@RequestMapping("/award/phase")
public class PhaseController {
    @Autowired
    private PhaseService phaseService;

    @RequestMapping("/add")
    public BaseResponse<Boolean> add(@Valid Phase phase, BindingResult result) {
        Date createTime = new Date();
        phase.setCreateTime(createTime);
        phase.setUpdateTime(null);
        phase.setId(null);
        phase.setState(0);

        return Utility.getRightResponse(phaseService.insert(phase));
    }

    @RequestMapping("/publish")
    public BaseResponse<Boolean> publish(@Valid PhasePublishReq req, BindingResult result) {
        phaseService.publish(req.getPhaseId());
        return Utility.getRightResponse(null);
    }
}
