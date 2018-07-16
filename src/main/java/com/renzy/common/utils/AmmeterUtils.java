/*
package com.renzy.common.utils;

import com.google.gson.Gson;
import com.huifenqi.saas.smart.cloud.model.database.AmmeterCloud;
import com.huifenqi.saas.smart.cloud.service.AmmeterCloudService;
import com.huifenqi.saas.smart.common.context.Enums;
import com.huifenqi.saas.smart.common.exception.NormalException;
import com.huifenqi.saas.smart.common.exception.SmartException;
import com.huifenqi.saas.smart.protocol.model.Instant;
import com.huifenqi.saas.smart.v2.util.CheckUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

*/
/**
 * @Description:
 * @Author wanglin
 * @Date 2017/04/27 下午6:10
 *//*

@Component
public class AmmeterUtils {

    public static final Logger log = LoggerFactory.getLogger(AmmeterUtils.class);
    private static final String KEY_VERSION_BEFORE = "smart-version-";
    private static final List<String> OLD_VERSIONS = Arrays.asList("1.2.01", "1.2.02", "2.2.01", "2.2.02", "2.2.03");
    private static final List<String> PROTOCOL3 = Arrays.asList("3.2.00A", "3.2.00", "3.2.01");

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Autowired
    private AmmeterCloudService ammeterCloudService;

    public int isOnline(String ammeterSn) {
        int online = 0;
        try {
            String key = redisCacheManager.getKey(ammeterSn);
            String value = redisCacheManager.getValue(key);
            if (!StringUtils.isEmpty(value)) {
                online = 1;
            }
        } catch (Exception e) {
            log.error(" get value error,cause by = {}", e);
            online = this.getIsOnlineInDb(ammeterSn);
        }
        return online;
    }

    private int getIsOnlineInDb(String ammeterSn) {
        int online = 0;

        AmmeterCloud ammeterCloud = ammeterCloudService.findByAmmeterSn(ammeterSn);

        if (ammeterCloud != null) {
            online = isOnline(DateUtil.formatDateTime(ammeterCloud.getfEnableTime()),
                    Enums.AmmeterType.getAmmeterType(ammeterSn));
        }

        return online;
    }

    public static int isOnline(String enableTime, int ammeterType) {
        final int nonLoraAmmeterOfflineTime = 11 * 60 * 1000;
        final int loraAmmeterOfflineTime = 31 * 60 * 1000;
        int onlineState = 1;
        if (StringUtil.isEmpty(enableTime)) {
            onlineState = 0;
        } else {
            boolean loraAmmeter = Enums.AmmeterType.LORA.getType() == ammeterType;
            if (!loraAmmeter && (System.currentTimeMillis() - DateUtil.parseDateTime(enableTime).getTime()) > nonLoraAmmeterOfflineTime) {
                onlineState = 0;
            } else if (loraAmmeter && (System.currentTimeMillis() - DateUtil.parseDateTime(enableTime).getTime()) > loraAmmeterOfflineTime) {
                onlineState = 0;
            }
        }
        return onlineState;
    }

    public Instant getInstant(String ammeterSn) {
        Instant instant = new Instant();
        try {
            String key = redisCacheManager.getKey(ammeterSn);
            String value = redisCacheManager.getValue(key);
            instant = new Gson().fromJson(value, Instant.class);
        } catch (Exception e) {
            log.error(" get value error,cause by = {}", e);
        }
        return instant;
    }

    public String getVersion(String ammeterSn) {

        if (!CheckUtil.ammeterSnCheck(ammeterSn)) {
            throw new NormalException(ErrorCode.SYS_NORMAL_EXACT_ERROR, "电表序列号不能为空");
        }

        if (StringUtils.isBlank(ammeterSn)) {
            throw new NormalException(ErrorCode.SYS_NORMAL_EXACT_ERROR, "电表序列号不能为空");
        }

        String version;
        try {
            version = redisCacheManager.getValue(String.format("%s%s", KEY_VERSION_BEFORE, ammeterSn));
        } catch (Exception e) {
            log.error(" redis get version error,ammeterSn = {}", ammeterSn);
            throw new BaseException(ErrorCode.AMMETER_VERSION_GET_ERROR);
        }

        if (StringUtils.isBlank(version)) {
            throw new BaseException(ErrorCode.AMMETER_VERSION_GET_ERROR);
        }

        return version;
    }

    public boolean isNewVersion(String ammeterSn) {
        return !OLD_VERSIONS.contains(getVersion(ammeterSn));
    }

    public boolean isProtocol3(String ammeterSn) {
        return PROTOCOL3.contains(getVersion(ammeterSn));
    }
}
*/
