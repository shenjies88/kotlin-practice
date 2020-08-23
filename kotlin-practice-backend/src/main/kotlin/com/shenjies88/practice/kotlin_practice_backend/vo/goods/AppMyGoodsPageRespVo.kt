package com.shenjies88.practice.kotlin_practice_backend.vo.goods

import com.shenjies88.practice.kotlin_practice_backend.vo.PageReqVo
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author shenjies88
 * @since 2020/8/23-7:53 PM
 */
@ApiModel("我的商品列表请求")
class AppMyGoodsPageRespVo(
        @ApiModelProperty(hidden = true)
        var userId: Int?) : PageReqVo()