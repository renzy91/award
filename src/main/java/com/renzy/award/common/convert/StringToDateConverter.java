package com.renzy.award.common.convert;

import com.renzy.award.common.utils.DateUtil;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * @Description:
 * @Author huwei
 * @Date 2017/8/11 17:03
 */
public class StringToDateConverter implements Converter<String,Date> {
    @Override
    public Date convert(String source) {
        return DateUtil.string2Date(source);
    }
}
