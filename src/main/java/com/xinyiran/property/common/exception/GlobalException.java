package com.xinyiran.property.common.exception;

import com.xinyiran.property.common.Result;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

//全局异常捕获
@Slf4j
@RestControllerAdvice
public class GlobalException {

//    运行异常
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = RuntimeException.class)
//    public Result handler(RuntimeException e){
//        log.error("运行异常:-----------{}",e.getMessage());
//        return Result.fail(e.getMessage());
//
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = SQLException.class)
    public Result handler(SQLException e){
        log.error("Server层数据库操作异常:-----------{}",e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = JwtException.class)
    public Result handler(JwtException e){
        log.error("JWT认证异常:-----------{}",e.getMessage());
        return Result.fail(e.getMessage());
    }



    //Assert异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalAccessException.class)
    public Result handler(IllegalAccessException e){
        log.error("Assert异常:-----------{}",e.getMessage());
        return Result.fail(e.getMessage());
    }

    //实体校验异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        log.error("实体校验异常:-----------{}",objectError.getDefaultMessage());
        return Result.fail(objectError.getDefaultMessage());
    }
}
