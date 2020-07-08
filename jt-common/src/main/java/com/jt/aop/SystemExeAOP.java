package com.jt.aop;

import com.jt.vo.SysResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    public Object systemResultExe(Exception exception) {
        //有问题则在控制台打印
        exception.printStackTrace();
        log.error("{---------------业务调用异常" + exception.getMessage() + "}", exception);
        //返回统一的失败数据
        return SysResult.fail();
    }

}
