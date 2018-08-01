package com.renzy.award.common.exception.handler;

import com.renzy.award.common.exception.BaseException;
import com.renzy.award.common.exception.NormalException;
import com.renzy.award.common.exception.ReqException;
import com.renzy.award.common.model.BaseResult;
import com.renzy.award.common.utils.ErrorCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author renzhiyong
 * @description:
 * @date 2018-03-29 16:30
 */
@Aspect
@Component
public class ExceptionHandler {
    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;

    @Around("execution(public com.renzy.award.common.model.BaseResponse com.renzy.award.award.controller.*Controller.*(..)) && args(.., result)")
    public Object arround(ProceedingJoinPoint pjp, BindingResult result) {
        //获取方法调用信息
        Signature signature = pjp.getSignature();
        Object[] requestParam = pjp.getArgs();
        String methodName = signature.getName();
        Class methodType = signature.getDeclaringType();

        Logger log = LoggerFactory.getLogger(methodType);

        log.info(" ==> {}: request param = {}", methodName, requestParam);
        //校验参数
        if (result.hasErrors()) {
            String message = result.getAllErrors().get(0).getDefaultMessage();
            log.error(" ==> {}: parameter error = {}", methodName, message);
            return BaseResult.badRequest(message);
        }

        Object response;
        try {
            response = pjp.proceed();
            log.info(" ==> {}: response = {}", methodName, response);
            //参数异常
        } catch (ReqException e) {
            log.error(" ==> {}: parameter error = {}", methodName, e.getMessage());
            return BaseResult.badRequest(e.getMessage());
            //第三方错误
        } catch (NormalException e) {
            log.error(" ==> {}: error caused by = {}", methodName, ErrorCode.getErrorMsg(e.getErrorCode(), messageSource));
            return BaseResult.operatError(e.getMessage());
            //逻辑异常
        } catch (BaseException e) {
            log.error(" ==> {}: error caused by = {}", methodName, ErrorCode.getErrorMsg(e.getErrorCode(), messageSource));
            return BaseResult.getErrorResponse(e.getErrorCode());
        } catch (Throwable t) {
            log.error(" ==> {}: error caused by = {}", methodName, t);
            return BaseResult.operatError();
        }
        return response;
    }
}
