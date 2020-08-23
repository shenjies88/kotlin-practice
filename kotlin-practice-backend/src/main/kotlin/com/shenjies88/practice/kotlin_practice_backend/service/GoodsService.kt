package com.shenjies88.practice.kotlin_practice_backend.service

import com.shenjies88.practice.kotlin_practice_backend.entity.GoodsDO
import com.shenjies88.practice.kotlin_practice_backend.mapper.GoodsMapper
import com.shenjies88.practice.kotlin_practice_backend.utils.AppUserMemoryUtils
import com.shenjies88.practice.kotlin_practice_backend.vo.PageVo
import com.shenjies88.practice.kotlin_practice_backend.vo.goods.AppMyGoodsPageRespVo
import com.shenjies88.practice.kotlin_practice_backend.vo.goods.GoodsCount
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author shenjies88
 * @since 2020/8/23-3:07 PM
 */
@Service
class GoodsService @Autowired constructor(private val goodsMapper: GoodsMapper) {

    @Transactional(rollbackFor = [Exception::class])
    fun insert(name: String?) {
        goodsMapper.insert(name, AppUserMemoryUtils.getAppUser().id)
    }

    fun page(param: AppMyGoodsPageRespVo): PageVo<GoodsDO> {
        val goodsCount = GoodsCount()
        BeanUtils.copyProperties(param, goodsCount)
        val total = goodsMapper.count(goodsCount)
        val result = PageVo<GoodsDO>()
        if (total > 0) {
            result.list = goodsMapper.appPage(param)
        } else {
            result.list = emptyArray()
        }
        result.total = total
        return result
    }

}