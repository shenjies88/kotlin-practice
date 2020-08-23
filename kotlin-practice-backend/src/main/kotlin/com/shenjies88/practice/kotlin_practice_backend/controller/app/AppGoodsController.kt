package com.shenjies88.practice.kotlin_practice_backend.controller.app

import com.shenjies88.practice.kotlin_practice_backend.service.GoodsService
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo.Companion.successReturn
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.Assert
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author shenjies88
 * @since 2020/8/23-4:00 PM
 */
@Api(tags = ["产品接口"])
@RequestMapping("/app/goods")
@RestController
class AppGoodsController @Autowired constructor(private val goodsService: GoodsService) {

    @ApiOperation("增加商品")
    @PutMapping
    fun insert(name: String?): HttpResultVo<Nothing> {
        Assert.hasText(name, "商品名称不能为空")
        goodsService.insert(name)
        return successReturn()
    }
    //删除
    //修改
    //查看
}