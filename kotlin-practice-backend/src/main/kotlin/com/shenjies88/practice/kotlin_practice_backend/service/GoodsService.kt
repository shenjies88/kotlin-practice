package com.shenjies88.practice.kotlin_practice_backend.service

import com.shenjies88.practice.kotlin_practice_backend.mapper.GoodsMapper
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
        goodsMapper.insert(name)
    }

}