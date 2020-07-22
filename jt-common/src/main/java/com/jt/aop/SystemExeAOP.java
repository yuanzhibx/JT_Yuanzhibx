package com.jt.aop;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.vo.SysResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuanzhibx
 * @Date 2020-07-08
 */
@RestControllerAdvice
@Slf4j
public class SystemExeAOP {

    /**
     * 通用异常返回
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    public Object systemResultExe(HttpServletRequest request, Exception exception) {
        String callback = request.getParameter("callback");
        if (StringUtils.isEmpty(callback)) {
            //不是跨域访问
            log.error("{---------------业务调用异常" + exception.getMessage() + "}", exception);
            return SysResult.fail();
        }

        String method = request.getMethod();
        if (!method.equalsIgnoreCase("GET")) {
            log.error("{---------------业务调用异常" + exception.getMessage() + "}", exception);
            return SysResult.fail();
        }


        log.error("{---------------业务调用异常" + exception.getMessage() + "}", exception);
        //返回统一的失败数据
        return new JSONPObject(callback, SysResult.fail());
    }

}
