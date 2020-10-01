package com.shenjies88.practice.kotlin_practice_backend.vo.goods.req

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * @author shenjies88
 * @since 2020/8/23-7:53 PM
 */
@ApiModel("修改我的商品请求")
data class AppMyGoodsUpdateReqVo(
        @get: NotNull(message = "未选中商品")
        @ApiModelProperty("主键id")
        var id: Int?,
        @get: NotBlank(message = "商品名称不能为空")
        @ApiModelProperty("商品名称")
        var name: String?)