package com.shenjies88.practice.kotlin_practice_backend.vo

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author shenjies88
 * @since 2020/8/23-3:23 PM
 */
@ApiModel("Http返回封装实体")
class HttpResultVo<T>(
        @ApiModelProperty("成功标志")
        val success: Boolean,
        @ApiModelProperty("错误码")
        val code: Int,
        @ApiModelProperty("返回数据")
        val data: T?,
        @ApiModelProperty("错误消息")
        val errorMsg: String?) {

    companion object {
        fun <T> successReturn(data: T): HttpResultVo<T> = HttpResultVo<T>(true, 0, data, null)
        fun successReturn() = HttpResultVo(true, 0, null, null)
        fun failure(errorMsg: String?) = HttpResultVo(false, 1, null, errorMsg)
        fun failure(code: Int, errorMsg: String) = HttpResultVo(false, code, null, errorMsg)
    }
}