package com.shenjies88.practice.kotlin_practice_backend.config

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


/**
 * @author shenjies88
 * @since 2020/8/23-2:50 PM
 */
@EnableSwaggerBootstrapUI
@EnableSwagger2
@Configuration
class SwaggerConfig {

    @Bean
    fun appApiDoc(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("1.APP端接口")
                .apiInfo(appApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shenjies88.practice.kotlin_practice_backend.controller.app"))
                .paths(PathSelectors.any())
                .build()
    }

    @Bean
    fun adminApiDoc(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("2.管理后台接口")
                .apiInfo(adminApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shenjies88.practice.kotlin_practice_backend.controller.admin"))
                .paths(PathSelectors.any())
                .build()
    }

    private fun appApiInfo(): ApiInfo? {
        return ApiInfoBuilder()
                .title("APP端接口文档")
                .build()
    }

    private fun adminApiInfo(): ApiInfo? {
        return ApiInfoBuilder()
                .title("后台管理端接口文档")
                .build()
    }
}