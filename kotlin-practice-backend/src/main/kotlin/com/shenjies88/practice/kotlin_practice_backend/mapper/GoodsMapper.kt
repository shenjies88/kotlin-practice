package com.shenjies88.practice.kotlin_practice_backend.mapper

import com.shenjies88.practice.kotlin_practice_backend.entity.GoodsDO
import com.shenjies88.practice.kotlin_practice_backend.vo.goods.GoodsCount
import com.shenjies88.practice.kotlin_practice_backend.vo.goods.req.AdminGoodsPageReqVo
import com.shenjies88.practice.kotlin_practice_backend.vo.goods.req.AppMyGoodsPageReqVo
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
    fun insert(@Param("name") name: String?, @Param("userId") userId: Int?)

    /**
     * 计数
     */
    fun count(param: GoodsCount): Int

    /**
     * 实体列表分页
     */
    fun appPage(param: AppMyGoodsPageReqVo): Array<GoodsDO>

    /**
     * 删除实体
     */
    fun delete(@Param("list") idList: Array<Int>)

    /**
     * 获取实体
     */
    fun get(id: Int?): GoodsDO?

    /**
     * 更新非空字段
     */
    fun updateSelect(goods: GoodsDO)

    /**----------Admin----------**/

    fun adminPage(param: AdminGoodsPageReqVo): Array<GoodsDO>
}