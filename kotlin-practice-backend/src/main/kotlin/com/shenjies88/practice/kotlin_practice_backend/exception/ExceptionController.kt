package com.shenjies88.practice.kotlin_practice_backend.exception

import com.shenjies88.practice.kotlin_practice_backend.common.MyLog
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo
import org.springframework.http.HttpStatus
import org.springframework.util.CollectionUtils
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @author shenjies88
 * @since 2020/8/23-3:21 PM
 */
@ResponseStatus(HttpStatus.OK)
@RestControllerAdvice("com.shenjies88.practice.kotlin_practice_backend")
class ExceptionController {

    companion object : MyLog

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentExceptionHandler(e: IllegalArgumentException): HttpResultVo<Nothing> {
        var errorMessage: String? = "服务器繁忙"
        if (!CollectionUtils.isEmpty(e.stackTrace.filter { it.className.startsWith("com.shenjies88.practice.kotlin_practice_backend") })) {
            errorMessage = e.message
        }
        log().error("业务异常", e)
        return HttpResultVo.failure(errorMessage)
    }

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(e: Exception): HttpResultVo<Nothing> {
        log().error("通用错误", e)
        return HttpResultVo.failure("服务器繁忙")
    }
}