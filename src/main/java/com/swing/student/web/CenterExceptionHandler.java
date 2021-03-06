package com.swing.student.web;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理中心
 *
 * @author swing
 */
@RestControllerAdvice
public class CenterExceptionHandler {
    /**
     * 所有的自行抛出的异常
     */
    @ExceptionHandler(Exception.class)
    SkyResponse exception(Exception e) {
        return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    /**
     * 令牌过期异常
     */
    @ExceptionHandler(ExpiredJwtException.class)
    SkyResponse expiredJwtException(ExpiredJwtException e) {
        return SkyResponse.fail(HttpStatus.BAD_REQUEST, "令牌过期");
    }

    /**
     * 令牌无效
     */
    @ExceptionHandler(SignatureException.class)
    SkyResponse signatureException(SignatureException e) {
        return SkyResponse.fail(HttpStatus.BAD_REQUEST, "令牌无效");
    }
}
