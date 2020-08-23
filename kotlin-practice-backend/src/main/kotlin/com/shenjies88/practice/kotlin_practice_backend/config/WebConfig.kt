package com.shenjies88.practice.kotlin_practice_backend.config

import com.shenjies88.practice.kotlin_practice_backend.interceptor.AppInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author shenjies88
 * @since 2020/8/23-5:36 PM
 */
@Configuration
class WebConfig @Autowired constructor(private val appInterceptor: AppInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(appInterceptor)
                .addPathPatterns("/app/**")
                .excludePathPatterns("/app/user/registered", "/app/user/login")
    }
}