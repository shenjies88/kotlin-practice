package com.shenjies88.practice.kotlin_practice_backend.vo.goods.req

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author shenjies88
 * @since 2020/8/23-7:53 PM
 */
@ApiModel("修改我的商品请求")
data class AppMyGoodsUpdateReqVo(
        @ApiModelProperty("主键id")
        var id: Int?,
        @ApiModelProperty("商品名称")
        var name: String?)