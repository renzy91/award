package com.renzy.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;
import java.util.Objects;

/**
 * @author renzhiyong
 * @description:
 * @date 2018-03-29 16:30
 */
public class ErrorCode {

    private static final Logger log = LoggerFactory.getLogger(ErrorCode.class);

    private static final String ERROR_MSG_FORMAT = "error.%s";

    private static AbstractMessageSource messageSource = null;

    // system error code 0 ~ 10
    public static final String SYS_SUCCESS = "0";

    public static final String SYS_ERROR = String.format("%s%s", "034", "10001");
    public static final String SYS_REQ_ERROR = String.format("%s%s", "034", "10002");
    public static final String SYS_FREQUENCY_OPERATION = String.format("%s%s", "034", "10003");
    public static final String SYS_PRIVILEGE_ERROR = String.format("%s%s", "034", "10004");
    public static final String SYS_OPER_ERROR = String.format("%s%s", "034", "10005");
    public static final String SYS_NOT_SUPPORT_METHOD = String.format("%s%s", "034", "10006");
    public static final String SYS_REMOTE_CALL_FAILED = String.format("%s%s", "034", "10007");

    // EXACT ERROR NOT MESSAGE
    public static final String SYS_REQ_EXACT_ERROR = String.format("%s%s", "034", "20001");
    public static final String SYS_NORMAL_EXACT_ERROR = String.format("%s%s", "034", "20002");
    // 用于公摊时，公共区域电表和分电表离线固定提醒   其他异常慎用
    public static final String PUBLIC_OFF_LINE_EXACT_ERROR = String.format("%s%s", "034", "20003");

    /* --------------------------------  hfq  -------------------------------------------*/
    // other error code manual specified
    public static final String AMMETER_PRICE_RECORD_NOT_EXIST = String.format("%s%s", "034", "21035");
    public static final String AMMETER_USER_NOT_TIED = String.format("%s%s", "034", "21036");
    public static final String AMMETER_NOT_EXIST = String.format("%s%s", "034", "21037");
    public static final String AMMETER_ALREADY_EXIST = String.format("%s%s", "034", "21038");
    public static final String AMMETER_OFF_LINE = String.format("%s%s", "034", "21039");
    public static final String AMMETER_IN_USED = String.format("%s%s", "034", "21040");
    public static final String USER_REGISTER_ERROR = String.format("%s%s", "034", "21041");
    public static final String USER_GET_REMAINING_MONEY_ERROR = String.format("%s%s", "034", "21042");
    public static final String USER_UNBIND_BANK_CARD = String.format("%s%s", "034", "21043");
    public static final String USER_DRAW_CASH_ERROR = String.format("%s%s", "034", "21044");
    public static final String AMMETER_CLEAR_ERROR = String.format("%s%s", "034", "21045");
    public static final String AMMETER_ADD_ERROR = String.format("%s%s", "034", "21046");
    public static final String AMMETER_PRICE_ADD_ERROR = String.format("%s%s", "034", "21047");
    public static final String AMMETER_PRICE_UPDATE_ERROR = String.format("%s%s", "034", "21048");
    public static final String AMMETER_QUERY_ERROR = String.format("%s%s", "034", "21049");
    public static final String AMMETER_UPDATE_ERROR = String.format("%s%s", "034", "21050");
    public static final String USER_QUERY_BANK_CARD_ERROR = String.format("%s%s", "034", "21051");
    public static final String AMMETER_WALLET_ERROR = String.format("%s%s", "034", "21052");
    public static final String AMMETER_CHARGE_ERROR = String.format("%s%s", "034", "21053");
    public static final String AMMETER_CHARGE_RECORD_NOT_EXIST = String.format("%s%s", "034", "21054");
    public static final String AMMETER_CHARGE_ORDER_NOT_EXIST = String.format("%s%s", "034", "21055");
    public static final String AMMETER_CHARGE_ORDER_HAS_EXIST = String.format("%s%s", "034", "21056");
    public static final String AMMETER_OPER_DAY_ALREADY_DRAW = String.format("%s%s", "034", "21057");
    public static final String USER_DRAW_CASH_INCONFORMITY = String.format("%s%s", "034", "21058");
    public static final String USER_DRAW_CASH_ISHORT = String.format("%s%s", "034", "21059");
    public static final String AMMETER_CHARGE_KWH_TOO_LARGE = String.format("%s%s", "034", "21060");
    public static final String AMMETER_VERSION_GET_ERROR = String.format("%s%s", "034", "21061");
    public static final String AMMETER_RELATION_TOO_LARGE = String.format("%s%s", "034", "21062");

    /* --------------------------------  cloud  -------------------------------------------*/
    public static final String CLOUD_PLATFORM_NO_NOT_NULL = String.format("%s%s", "034", "22035");
    public static final String CLOUD_AMMETER_EXIST = String.format("%s%s", "034", "22036");
    public static final String CLOUD_AMMETER_NOT_EXIST = String.format("%s%s", "034", "22037");
    public static final String CLOUD_AMMETER_NOT_BELONG_PLATFORM = String.format("%s%s", "034", "22038");
    public static final String CLOUD_AMMETER_ENABLE_KWH_LESS = String.format("%s%s", "034", "22039");
    public static final String CLOUD_CHARGE_NOT_EXIST = String.format("%s%s", "034", "22040");
    public static final String CLOUD_AMMETER_OFFLINE = String.format("%s%s", "034", "22041");
    public static final String CLOUD_CONCENTRATOR_EXIST = String.format("%s%s", "034", "22042");

    /* --------------------------------  house -------------------------------------------*/
    public static final String HOUSE_EXIST = String.format("%s%s", "034", "23041");
    public static final String HOUSE_PRICE_NOT_EXIST = String.format("%s%s", "034", "23042");
    public static final String HOUSE_NOT_EXIST = String.format("%s%s", "034", "23043");
    public static final String HOUSE_NOT_BELONG_AGENCY = String.format("%s%s", "034", "23044");
    public static final String HOUSE_HAS_USER = String.format("%s%s", "034", "23045");
    public static final String HOUSE_PRICE_NOT_BELONG_AGENCY = String.format("%s%s", "034", "23046");
    public static final String HOUSE_PRICE_DEFAULT_NOT_DELETE = String.format("%s%s", "034", "23047");
    public static final String HOUSE_PRICE_IS_USE = String.format("%s%s", "034", "23048");
    public static final String HOUSE_PRICE_NOT_EXIST_IN_AGENCY = String.format("%s%s", "034", "23049");
    public static final String HOUSE_AMMETER_QUERY_ERROR = String.format("%s%s", "034", "23050");
    public static final String HOUSE_NOT_EXIST_ROOM = String.format("%s%s", "034", "23051");
    public static final String HOUSE_AMMETER_USING = String.format("%s%s", "034", "23052");
    public static final String HOUSE_AMMETER_NOT_EXIST_IN_AGENCY = String.format("%s%s", "034", "23053");
    public static final String HOUSE_AMMETER_NOT_EXIST = String.format("%s%s", "034", "23054");
    public static final String HOUSE_ROOM_IS_REPEAT = String.format("%s%s", "034", "23055");
    public static final String HOUSE_ROOM_MUST_HAS_ONE = String.format("%s%s", "034", "23056");
    public static final String HOUSE_ROOM_HAS_USER = String.format("%s%s", "034", "23057");
    public static final String HOUSE_ROOM_NOT_EXIST = String.format("%s%s", "034", "23058");
    public static final String HOUSE_ROOM_HAS_AMMETER = String.format("%s%s", "034", "23059");
    public static final String HOUSE_ROOM_NUM_CONFORM = String.format("%s%s", "034", "23060");
    public static final String HOUSE_DISPERSE_JOINT_ONLY = String.format("%s%s", "034", "23061");
    public static final String HOUSE_AMMETER_OFF_LINE = String.format("%s%s", "034", "23062");
    public static final String HOUSE_ROOM_NOT_HAS_USER = String.format("%s%s", "034", "23063");
    public static final String HOUSE_ROOM_USER_EXIST_UN_PUBLIC = String.format("%s%s", "034", "23064");
    public static final String HOUSE_ROOM_USER_NOT_EXIST = String.format("%s%s", "034", "23065");
    public static final String HOUSE_AMMETER_NOT_USING = String.format("%s%s", "034", "23066");
    public static final String HOUSE_AMMETER_NOT_PUBLIC = String.format("%s%s", "034", "23067");
    public static final String HOUSE_ROOM_HAS_USER_001 = String.format("%s%s", "034", "23068");
    public static final String HOUSE_ROOM_HAS_USER_002 = String.format("%s%s", "034", "23069");
    public static final String HOUSE_ROOM_HAS_USER_UNBIND_002 = String.format("%s%s", "034", "23070");
    public static final String HOUSE_OLD_VERSION_NOT_SUPPORTED = String.format("%s%s", "034", "23071");
    public static final String HOUSE_ROOM_USERS_CHANGE = String.format("%s%s", "034", "23072");
    public static final String HOUSE_AMMETER_485_THAN_MAX = String.format("%s%s", "034", "23073");
    public static final String HOUSE_AMMETER_485_IS_RELATION = String.format("%s%s", "034", "23074");
    public static final String HOUSE_AMMETER_485_NOT_RELATION_THIS = String.format("%s%s", "034", "23075");
    public static final String HOUSE_AMMETER_485_NOT_RELATION = String.format("%s%s", "034", "23076");
    public static final String HOUSE_ROOM_NOT_BELONG_AGENCY = String.format("%s%s", "034", "23077");
    public static final String HOUSE_NOT_EXIST_ROOM_WEIGHT = String.format("%s%s", "034", "23078");
    public static final String HOUSE_AMMETER_NOT_EXIST_IN_HOUSE = String.format("%s%s", "034", "23079");
    public static final String HOUSE_AMMETER_OFFLINE_UNDER_PUBLIC_HEAD = String.format("%s%s", "034", "23080");
    public static final String HOUSE_PUBLIC_HEAD_OLD_VERSION_NOT_SUPPORTED = String.format("%s%s", "034", "23081");
    public static final String ROOM_AMMETER_NOT_CLEAR_HAS_RENT = String.format("%s%s", "034", "23082");
    public static final String ROOM_AMMETER_NOT_UN_BIND_HAS_RENT = String.format("%s%s", "034", "23083");
    public static final String HOUSE_NOT_HAS_AMMETER_NOT_SWITCH_HEAD = String.format("%s%s", "034", "23084");
    public static final String HOUSE_NOT_SWITCH_MODEL_TYPE_IN_OLD_VERSION = String.format("%s%s", "034", "23085");
    public static final String HOUSE_NOT_SWITCH_DEFINE_IN_OLD_VERSION = String.format("%s%s", "034", "23086");
    public static final String HOUSE_AMMETER_NOT_EXIST_IN_ROOM = String.format("%s%s", "034", "23087");
    public static final String HOUSE_CONCENTRATOR_EXIST_DISPERSE = String.format("%s%s", "034", "23088");
    public static final String HOUSE_AMMETER_CONCENTRATOR_NOT_RELATION_THIS = String.format("%s%s", "034", "23089");
    public static final String ROOM_AMMETER_NOT_CANCEL_RELATE_HAS_RENT = String.format("%s%s", "034", "23090");
    public static final String LORA_CANNOT_USE_WHITH_OTHER_TYPE = String.format("%s%s", "034", "23098");
    public static final String ROOM_AMMETER_NOT_UN_BIND_HAS_RELATION = String.format("%s%s", "034", "23099");
    public static final String HOUSE_NO_PUBLIC_AMMETER = String.format("%s%s", "034", "23100");
    public static final String HOUSE_HAS_LORA_NOT_DELETE = String.format("%s%s", "034", "23101");
    public static final String ROOM_AMMETER_LORA_NOT_RELATION = String.format("%s%s", "034", "23102");

    /* --------------------------------  room  ammeter-------------------------------------------*/
    public static final String ROOM_AMMETER_EXIST = String.format("%s%s", "034", "24035");
    public static final String ROOM_AMMETER_REJ_ERROR = String.format("%s%s", "034", "24036");
    public static final String ROOM_AMMETER_LIST_ERROR = String.format("%s%s", "034", "24037");
    public static final String ROOM_AMMETER_DETAIL_ERROR = String.format("%s%s", "034", "24038");
    public static final String ROOM_AMMETER_MODIFY_ERROR = String.format("%s%s", "034", "24039");
    public static final String ROOM_AMMETER_HAS_NOT_EXIST = String.format("%s%s", "034", "24040");
    public static final String ROOM_AMMETER_DELETE_ERROR = String.format("%s%s", "034", "24041");
    public static final String ROOM_AMMETER_NOT_IN_USE = String.format("%s%s", "034", "24042");
    public static final String ROOM_HAS_QUIT_RENTING_NOT_RENT = String.format("%s%s", "034", "24043");

    /* --------------------------------  charge -------------------------------------------*/
    public static final String CHARGE_ERROR = String.format("%s%s", "034", "25035");
    public static final String CHARGE_RECORD_HAS_EXIST = String.format("%s%s", "034", "25036");

    /* --------------------------------  rent -------------------------------------------*/
    public static final String RENT_NOT_EXIST = String.format("%s%s", "034", "26035");

    /* --------------------------------  concentrator relation -------------------------------------------*/
    public static final String RELATION_CONCENTRATOR_EXIST = String.format("%s%s", "034", "27035");
    public static final String RELATION_CONCENTRATOR_NOT_EXIST = String.format("%s%s", "034", "27036");
    public static final String RELATION_CONCENTRATOR_USING = String.format("%s%s", "034", "27037");
    public static final String RELATION_CONCENTRATOR_NOT_EXIST_IN_AGENCY = String.format("%s%s", "034", "27038");
    public static final String RELATION_CONCENTRATOR_NOT_USE = String.format("%s%s", "034", "27039");
    public static final String RELATION_CONCENTRATOR_NOT_EXIST_IN_HOUSE = String.format("%s%s", "034", "27040");
    public static final String RELATION_CONCENTRATOR_HAS_LORA = String.format("%s%s", "034", "27041");
    public static final String RELATION_CONCENTRATOR_LORA_NOT_IN_SAME_HOUSE = String.format("%s%s", "034", "27042");
    public static final String RELATION_CONCENTRATOR_LORA_THAN_MAX = String.format("%s%s", "034", "27043");
    public static final String RELATION_CONCENTRATOR_LORA_STATE_RELATING = String.format("%s%s", "034", "27044");
    public static final String RELATION_CONCENTRATOR_LORA_STATE_CANCELLING = String.format("%s%s", "034", "27045");
    public static final String RELATION_CONCENTRATOR_OFF_LINE = String.format("%s%s", "034", "27046");
    public static final String RELATION_CONCENTRATOR_PUBLIC_LORA_STATE_ING = String.format("%s%s", "034", "27047");
    public static final String RELATION_CONCENTRATOR_LORA_STATE_UNRELATE = String.format("%s%s", "034", "27048");
    public static final String RELATION_CONCENTRATOR_LORA_STATE_RELATED = String.format("%s%s", "034", "27049");


    /* --------------------------------  locker -------------------------------------------*/
    public static final String LOCKER_HAS_EXIST = String.format("%s%s", "034", "30035");
    public static final String LOCKER_NOT_EXIST = String.format("%s%s", "034", "30036");
    public static final String LOCKER_IS_BIND = String.format("%s%s", "034", "30037");
    public static final String LOCKER_KEY_NOT_EXIST = String.format("%s%s", "034", "30038");
    public static final String LOCKER_KEY_NOT_MATCH = String.format("%s%s", "034", "30039");
    public static final String LOCKER_KEY_TYPE_ERROR = String.format("%s%s", "034", "30040");
    public static final String LOCKER_EXIST_IN_HOUSE = String.format("%s%s", "034", "30041");
    public static final String LOCKER_EXIST_IN_ROOM = String.format("%s%s", "034", "30042");
    public static final String LOCKER_NOT_EXIST_IN_HOUSE = String.format("%s%s", "034", "30043");
    public static final String LOCKER_NOT_EXIST_IN_ROOM = String.format("%s%s", "034", "30044");
    public static final String LOCKER_NOT_EXIST_IN_AGENCY = String.format("%s%s", "034", "30045");
    public static final String LOCKER_IS_UNBIND = String.format("%s%s", "034", "30046");
    public static final String LOCKER_NOT_EXIST_IN_CUSTOMER = String.format("%s%s", "034", "30047");
    public static final String LOCKER_KEY_HAS_EXIST = String.format("%s%s", "034", "30048");
    public static final String LOCKER_KEY_LENGTH_ERROR = String.format("%s%s", "034", "30049");
    public static final String LOCKER_KEY_STATE_ERROR = String.format("%s%s", "034", "30050");
    public static final String LOCKER_KEY_HAS_EXPIRED = String.format("%s%s", "034", "30051");
    public static final String LOCKER_GATEWAY_RELATE_ERROR = String.format("%s%s", "034", "30052");
    public static final String LOCKER_GATEWAY_UNRELATE_ERROR = String.format("%s%s", "034", "30053");
    public static final String LOCKER_UNBIND_ERROR = String.format("%s%s", "034", "30054");
    public static final String LOCKER_KEY_STARTTIME_ERROR = String.format("%s%s", "034", "30055");
    public static final String LOCKER_KEY_ENDTTIME_ERROR = String.format("%s%s", "034", "30056");
    public static final String LOCKER_KEY_NEWPWD_EQUALS_OLDPWD = String.format("%s%s", "034", "30057");
    public static final String LOCKER_OPENAPI_TOKEN_EXPIRED = String.format("%s%s", "034", "30058");
    public static final String LOCKER_KEY_MUST_ALL_NUMBER = String.format("%s%s", "034", "30059");
    public static final String LOCKER_OLD_VERSION_NOT_SUPPORTED = String.format("%s%s", "034", "30060");

    /* --------------------------------  gateway -------------------------------------------*/
    public static final String CHANGE_GATEWAY_SAME_ERROR = String.format("%s%s", "034", "40035");

    public static String getErrorMsg(String errorCode, Objects[] objectses, Locale locale, ReloadableResourceBundleMessageSource messageSource) {

        if (messageSource == null) {
            log.error("==> getErrorMsg can not get message because message bundle source is null.");
            return String.format("errorCode = %d , get message source fail.", errorCode);
        }

        String message = messageSource.getMessage(String.format(ERROR_MSG_FORMAT, errorCode), objectses, locale == null ? Locale.CHINA : locale);

        if (message == null) {
            message = String.format("errorCode = %d , get message source fail . ", errorCode);
        }
        return message;
    }


    public static String getErrorMsg(String errorCode, ReloadableResourceBundleMessageSource messageSource) {
        return getErrorMsg(errorCode, null, null, messageSource);
    }

    public static void setMessageSource(AbstractMessageSource messageSource) {
        ErrorCode.messageSource = messageSource;
    }
}