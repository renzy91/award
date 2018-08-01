package com.renzy.award.award.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author renzhiyong
 * @description:
 * @date 2018-07-19 10:42
 */
@Data
public class PhasePublishReq {
    @NotNull
    private Integer phaseId;
}
