package com.shenjies88.practice.kotlin_practice_backend.vo

import io.swagger.annotations.ApiModelProperty

/**
 * @author shenjies88
 * @since 2020/8/23-7:48 PM
 */
open class PageReqVo(
        @ApiModelProperty("分页数") open val size: Int = 10,
        @ApiModelProperty("分页") open val num: Int = 1) {

    @ApiModelProperty(hidden = true)
    private val offset: Int = (num - 1) * size
}