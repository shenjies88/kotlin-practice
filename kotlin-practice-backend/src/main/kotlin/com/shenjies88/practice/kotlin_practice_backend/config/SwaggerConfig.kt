package com.shenjies88.practice.kotlin_practice_backend.config

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*


/**
 * @author shenjies88
 * @since 2020/8/23-2:50 PM
 */
@EnableSwaggerBootstrapUI
@EnableSwagger2
@Configuration
class SwaggerConfig {

    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shenjies88.practice.kotlin_practice_backend.controller"))
                .paths { path: String? -> !Objects.equals(path, "/error") }
                .build()
    }
}