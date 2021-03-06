package com.shenjies88.practice.kotlin_practice_backend.controller.app

import com.shenjies88.practice.kotlin_practice_backend.entity.GoodsDO
import com.shenjies88.practice.kotlin_practice_backend.service.GoodsService
import com.shenjies88.practice.kotlin_practice_backend.utils.AppUserMemoryUtils
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo.Companion.successReturn
import com.shenjies88.practice.kotlin_practice_backend.vo.PageVo
import com.shenjies88.practice.kotlin_practice_backend.vo.goods.req.AppMyGoodsPageReqVo
import com.shenjies88.practice.kotlin_practice_backend.vo.goods.req.AppMyGoodsUpdateReqVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.Assert
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * @author shenjies88
 * @since 2020/8/23-4:00 PM
 */
@Api(tags = ["商品接口"])
@RequestMapping("/app/goods")
@RestController
class AppGoodsController @Autowired constructor(private val goodsService: GoodsService) {

    @ApiOperation("增加商品")
    @PutMapping
    fun insert(@RequestBody name: String?): HttpResultVo<Nothing> {
        Assert.hasText(name, "商品名称不能为空")
        goodsService.insert(name)
        return successReturn()
    }

    @ApiOperation("我的商品列表分页")
    @PostMapping("/page")
    fun page(@RequestBody param: AppMyGoodsPageReqVo): HttpResultVo<PageVo<GoodsDO>> {
        param.userId = AppUserMemoryUtils.get().id
        return successReturn(goodsService.page(param))
    }

    @ApiOperation("删除商品")
    @DeleteMapping
    fun delete(@RequestBody idList: Array<Int>): HttpResultVo<Nothing> {
        Assert.notEmpty(idList, "未选中商品")
        goodsService.delete(idList)
        return successReturn()
    }

    @ApiOperation("修改我的商品")
    @PostMapping("/update")
    fun update(@RequestBody @Valid param: AppMyGoodsUpdateReqVo): HttpResultVo<Nothing> {
        goodsService.update(param)
        return successReturn()
    }
}