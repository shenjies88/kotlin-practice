package com.shenjies88.practice.kotlin_practice_backend.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

/**
 * @author shenjies88
 * @since 2020/8/24-9:17 AM
 */
@Configuration
class JacksonConfig {

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper::class)
    fun jacksonObjectMapper(builder: Jackson2ObjectMapperBuilder): ObjectMapper {
        val objectMapper = builder.createXmlMapper(false).build<ObjectMapper>()
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
        return objectMapper
    }
    //Include.Include.ALWAYS 默认
    //Include.NON_DEFAULT    属性为默认值不序列化
    //Include.NON_EMPTY       属性为 空（""） 或者为 NULL 都不序列化
    //Include.NON_NULL       属性为NULL 不序列化
}