package com.shenjies88.practice.kotlin_practice_backend.config

import com.shenjies88.practice.kotlin_practice_backend.interceptor.AdminInterceptor
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
class   WebConfig @Autowired constructor(
        private val appInterceptor: AppInterceptor,
        private val adminInterceptor: AdminInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(appInterceptor)
                .addPathPatterns("/app/**")
                .excludePathPatterns("/app/authentication/*")
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/authentication/*")
    }
}