package com.shenjies88.practice.kotlin_practice_backend.common

import org.slf4j.LoggerFactory

/**
 * @author shenjies88
 * @since 2020/8/23-4:32 PM
 */
interface MyLogger {
    fun log() = LoggerFactory.getLogger(this.javaClass)
}