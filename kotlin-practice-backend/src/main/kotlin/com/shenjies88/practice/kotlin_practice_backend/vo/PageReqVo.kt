package com.shenjies88.practice.kotlin_practice_backend.vo

/**
 * @author shenjies88
 * @since 2020/8/23-7:48 PM
 */
open class PageReqVo(private val size: Int = 20,private val num: Int = 1) {

    val offset: Int = (num - 1) * size
}