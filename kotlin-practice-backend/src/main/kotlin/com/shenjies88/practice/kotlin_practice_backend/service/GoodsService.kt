package com.shenjies88.practice.kotlin_practice_backend.service

import com.shenjies88.practice.kotlin_practice_backend.entity.GoodsDO
import com.shenjies88.practice.kotlin_practice_backend.mapper.GoodsMapper
import com.shenjies88.practice.kotlin_practice_backend.utils.AppUserMemoryUtils
import com.shenjies88.practice.kotlin_practice_backend.vo.PageVo
import com.shenjies88.practice.kotlin_practice_backend.vo.goods.GoodsCount
import com.shenjies88.practice.kotlin_practice_backend.vo.goods.req.AdminGoodsPageReqVo
import com.shenjies88.practice.kotlin_practice_backend.vo.goods.req.AppMyGoodsPageReqVo
import com.shenjies88.practice.kotlin_practice_backend.vo.goods.req.AppMyGoodsUpdateReqVo
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert

/**
 * @author shenjies88
 * @since 2020/8/23-3:07 PM
 */
@Service
class GoodsService @Autowired constructor(private val goodsMapper: GoodsMapper) {

    @Transactional(rollbackFor = [Exception::class])
    fun insert(name: String?) {
        goodsMapper.insert(name, AppUserMemoryUtils.get().id)
    }

    fun page(param: AppMyGoodsPageReqVo): PageVo<GoodsDO> {
        val goodsCount = GoodsCount()
        BeanUtils.copyProperties(param, goodsCount)
        val total = goodsMapper.count(goodsCount)
        val result = PageVo<GoodsDO>()
        result.list = if (total > 0) goodsMapper.appPage(param) else emptyArray()
        result.total = total
        return result
    }

    @Transactional(rollbackFor = [Exception::class])
    fun delete(idList: Array<Int>) {
        goodsMapper.delete(idList)
    }

    @Transactional(rollbackFor = [Exception::class])
    fun update(param: AppMyGoodsUpdateReqVo) {
        val goods = goodsMapper.get(param.id)
        Assert.notNull(goods, "该商品不存在")
        goods!!.name = param.name
        goodsMapper.updateSelect(goods)
    }

    /**----------Admin----------**/

    fun adminPage(param: AdminGoodsPageReqVo): PageVo<GoodsDO> {
        val goodsCount = GoodsCount()
        BeanUtils.copyProperties(param, goodsCount)
        val total = goodsMapper.count(goodsCount)
        val result = PageVo<GoodsDO>()
        result.list = if (total > 0) goodsMapper.adminPage(param) else emptyArray()
        result.total = total
        return result
    }
}