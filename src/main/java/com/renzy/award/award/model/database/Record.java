package com.renzy.award.award.model.database;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 中奖纪录
 * </p>
 *
 * @author renzhiyong
 * @since 2018-07-17
 */
@TableName("t_record")
public class Record extends Model<Record> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 期数id
     */
    @TableField("phase_id")
    private Integer phaseId;

    /**
     * 奖品id
     */
    @TableField("award_id")
    private Integer awardId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    @TableField("award_name")
    private String awardName;

    @TableField("award_desc")
    private String awardDesc;

    private Integer state;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Integer phaseId) {
        this.phaseId = phaseId;
    }
    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }
    public String getAwardDesc() {
        return awardDesc;
    }

    public void setAwardDesc(String awardDesc) {
        this.awardDesc = awardDesc;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Record{" +
        "id=" + id +
        ", phaseId=" + phaseId +
        ", awardId=" + awardId +
        ", userId=" + userId +
        ", awardName=" + awardName +
        ", awardDesc=" + awardDesc +
        ", state=" + state +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
