package com.shenjies88.practice.kotlin_practice_backend.exception

import org.springframework.http.HttpStatus

/**
 * @author shenjies88
 * @since 2020/8/26-9:03 AM
 */
class MyAuthorizationException(message: String?) : RuntimeException(message) {

    val code: Int = HttpStatus.UNAUTHORIZED.value()
}