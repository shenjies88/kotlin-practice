package com.shenjies88.practice.kotlin_practice_backend.mapper

import com.shenjies88.practice.kotlin_practice_backend.entity.AdminUserDO
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

/**
 * @author shenjies88
 * @since 2020/8/23-10:59 PM
 */
@Repository
interface AdminUserMapper {

    /**
     * 根据账号获取实体
     */
    fun getByAccount(@Param("account") account: String?): AdminUserDO?
}