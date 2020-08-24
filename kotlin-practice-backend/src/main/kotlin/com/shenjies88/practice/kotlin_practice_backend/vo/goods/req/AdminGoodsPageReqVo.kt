package com.shenjies88.practice.kotlin_practice_backend.vo.goods.req

import com.shenjies88.practice.kotlin_practice_backend.vo.PageReqVo
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author shenjies88
 * @since 2020/8/23-7:53 PM
 */
@ApiModel("商品列表分页请求")
class AdminGoodsPageReqVo(
        @ApiModelProperty("用户id")
        var userId: Int?) : PageReqVo()