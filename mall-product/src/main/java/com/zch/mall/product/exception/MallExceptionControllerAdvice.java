package com.zch.mall.product.exception;

import com.zch.common.exception.MallCodeException;
import com.zch.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice(basePackages = "com.zch.mall.product.controller")
public class MallExceptionControllerAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleControllerException(MethodArgumentNotValidException e){
        log.error("数据效验出现问题：{}，异常类型：{}",e.getMessage(),e.getClass());
        BindingResult bindingResult = e.getBindingResult();

        Map<String,String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError) ->{
            errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        });

        return R.error(MallCodeException.VALID_EXCEPTION.getCode(),"数据效验失败").put("data",errorMap);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){

        return R.error(MallCodeException.UNKNOW_EXCEPTION.getCode(),MallCodeException.UNKNOW_EXCEPTION.getMsg());
    }
}
