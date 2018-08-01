package com.renzy.award.award.controller;


import com.renzy.award.award.model.DrawReq;
import com.renzy.award.award.model.database.Award;
import com.renzy.award.award.service.AwardService;
import com.renzy.award.common.model.BaseResponse;
import com.renzy.award.common.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * <p>
 * 奖品表 前端控制器
 * </p>
 *
 * @author renzhiyong
 * @since 2018-07-17
 */
@RestController
@RequestMapping("/award/award")
public class AwardController {
    @Autowired
    private AwardService awardService;

    @RequestMapping("/add")
    public BaseResponse<Boolean> add(@Valid Award award, BindingResult result) {
        Date currentTime = new Date();
        award.setCreateTime(currentTime);
        award.setUpdateTime(null);
        award.setId(null);
        award.setRemainCount(award.getCount());
        award.setState(0);

        return Utility.getRightResponse(awardService.insert(award));
    }

    @RequestMapping("/draw")
    public BaseResponse<Boolean> draw(@Valid DrawReq req, BindingResult result) {
        return awardService.draw(req.getUserId());
    }
}
