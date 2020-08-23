package com.shenjies88.practice.kotlin_practice_backend.vo.goods.resp

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author shenjies88
 * @since 2020/8/23-8:45 PM
 */
@ApiModel("我的商品列表请求")
data class AppMyGoodsPageRespVo(
        @ApiModelProperty("主键")
        var id: Int?,
        @ApiModelProperty("产品名称")
        var name: String?)