package com.shenjies88.practice.kotlin_practice_backend.config

import com.shenjies88.practice.kotlin_practice_backend.constant.ApplicationCanstant
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Configuration

/**
 * @author shenjies88
 * @since 2020/8/23-3:08 PM
 */
@MapperScan(ApplicationCanstant.BASE_MAPPER)
@Configuration
class MybatisConfig