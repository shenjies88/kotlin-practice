package com.shenjies88.practice.kotlin_practice_backend.mapper

import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

/**
 * @author shenjies88
 * @since 2020/8/23-3:08 PM
 */
@Repository
interface GoodsMapper {

    /**
     * 插入实体
     */
    fun insert(@Param("name") name: String?)
}