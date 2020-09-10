package com.shenjies88.practice.kotlin_practice_backend.exception

import com.shenjies88.practice.kotlin_practice_backend.common.MyLogger
import com.shenjies88.practice.kotlin_practice_backend.constant.ApplicationConstant
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo
import org.springframework.http.HttpStatus
import org.springframework.util.CollectionUtils
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @author shenjies88
 * @since 2020/8/23-3:21 PM
 */
@ResponseStatus(HttpStatus.OK)
@RestControllerAdvice(ApplicationConstant.BASE_PACKAGE)
class ExceptionController {

    companion object : MyLogger

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException): HttpResultVo<Nothing> {
        val errorMessage = "无效的请求参数 ${e.bindingResult.fieldError!!.field} ${e.bindingResult.fieldError!!.defaultMessage}"
        log().error("校验异常 {}", errorMessage)
        return HttpResultVo.failure(errorMessage)
    }

    @ExceptionHandler(MyAuthorizationException::class)
    fun authorizationExceptionHandler(e: MyAuthorizationException): HttpResultVo<Nothing> {
        log().error("授权异常 {}", e.message)
        return HttpResultVo.failure(e.code, e.message)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentExceptionHandler(e: IllegalArgumentException): HttpResultVo<Nothing> {
        var errorMessage: String? = "服务器繁忙"
        if (!CollectionUtils.isEmpty(e.stackTrace.filter { it.className.startsWith(ApplicationConstant.BASE_PACKAGE) })) {
            log().error("业务异常 {}", e.message)
            errorMessage = e.message
        } else {
            log().error("服务器异常", e)
        }
        return HttpResultVo.failure(errorMessage)
    }

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(e: Exception): HttpResultVo<Nothing> {
        log().error("通用异常", e)
        return HttpResultVo.failure("服务器繁忙")
    }
}