/*
package com.renzy.common.utils;

import com.huifenqi.saas.smart.cloud.dao.ConcentratorCloudDao;
import com.huifenqi.saas.smart.cloud.model.database.ConcentratorCloud;
import com.huifenqi.saas.smart.common.exception.SmartException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

*/
/**
 * @author huwei
 * @description:
 * @date 2018/3/15 19:20
 *//*

@Component
public class ConcentratorUtils {
    public static final Logger log = LoggerFactory.getLogger(AmmeterUtils.class);
    */
/**
     * 集中器2两分钟上报一次数据
     * 10分钟认为离线
     *//*

    private static int INTERVAL_TIME = 10;
    */
/**
     * 集中器在线
     *//*

    private static int ONLINE = 1;
    */
/**
     * 集中器离线
     *//*

    private static int OFFLINE = 0;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Autowired
    private ConcentratorCloudDao concentratorCloudDao;

    public int isOnline(String concentratorSn) {
        int online = OFFLINE;
        try {
            String key = redisCacheManager.getKey(concentratorSn);
            String value = redisCacheManager.getValue(key);
            if (StringUtils.isNotBlank(value)) {
                online = ONLINE;
            }
        } catch (Exception e) {
            log.error(" get value error,cause by = {}", e);
            online = getConcentratorState(concentratorSn);
        }
        return online;
    }

    public int isOnline(Date lastestReportTime) {
        if (lastestReportTime == null) {
            return OFFLINE;
        }
        if (DateUtil.getDiffMinutesFromCurrent(lastestReportTime) <= INTERVAL_TIME) {
            return ONLINE;
        }
        return OFFLINE;
    }

    private int getConcentratorState(String concentratorSn) {
        ConcentratorCloud concentratorCloud = concentratorCloudDao.getByConcentratorSn(concentratorSn);
        if (concentratorCloud == null) {
            throw new BaseException(ErrorCode.RELATION_CONCENTRATOR_NOT_EXIST);
        }
        
        return isOnline(concentratorCloud.getfEnableTime());
    }
}
*/
