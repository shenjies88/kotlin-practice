package com.shenjies88.practice.kotlin_practice_backend.mapper

import com.shenjies88.practice.kotlin_practice_backend.entity.GoodsDO
import com.shenjies88.practice.kotlin_practice_backend.vo.goods.AppMyGoodsPageRespVo
import com.shenjies88.practice.kotlin_practice_backend.vo.goods.GoodsCount
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
     * App
     * 实体列表
     */
    fun appPage(param: AppMyGoodsPageRespVo): Array<GoodsDO>
}