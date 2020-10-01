package com.shenjies88.practice.kotlin_practice_backend.controller.admin

import com.shenjies88.practice.kotlin_practice_backend.entity.GoodsDO
import com.shenjies88.practice.kotlin_practice_backend.service.GoodsService
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo.Companion.successReturn
import com.shenjies88.practice.kotlin_practice_backend.vo.PageVo
import com.shenjies88.practice.kotlin_practice_backend.vo.goods.req.AdminGoodsPageReqVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author shenjies88
 * @since 2020/8/23-4:00 PM
 */
@Api(tags = ["商品接口"])
@RequestMapping("/admin/goods")
@RestController
class AdminGoodsController @Autowired constructor(private val goodsService: GoodsService) {

    @ApiOperation("商品列表分页")
    @PostMapping("/page")
    fun page(@RequestBody param: AdminGoodsPageReqVo): HttpResultVo<PageVo<GoodsDO>> {
        return successReturn(goodsService.adminPage(param))
    }
}