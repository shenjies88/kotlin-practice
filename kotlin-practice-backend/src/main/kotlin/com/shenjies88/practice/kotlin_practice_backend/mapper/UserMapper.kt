package com.shenjies88.practice.kotlin_practice_backend.mapper

import com.shenjies88.practice.kotlin_practice_backend.entity.UserDO
import com.shenjies88.practice.kotlin_practice_backend.vo.user.UserCount
import com.shenjies88.practice.kotlin_practice_backend.vo.user.req.AdminUserPageReqVo
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

/**
 * @author shenjies88
 * @since 2020/8/23-3:08 PM
 */
@Repository
interface UserMapper {

    /**
     * 根据账号获取用户
     */
    fun getByAccount(@Param("account") account: String?): UserDO?

    /**
     * 插入实体
     */
    fun insert(@Param("body") userDO: UserDO)

    /**
     * 计数
     */
    fun count(userCount: UserCount): Int

    /**----------Admin----------**/

    /**
     * 实体列表分页
     */
    fun adminPage(param: AdminUserPageReqVo): Array<UserDO>
}