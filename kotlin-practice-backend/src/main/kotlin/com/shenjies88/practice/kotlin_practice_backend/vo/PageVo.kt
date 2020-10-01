package com.shenjies88.practice.kotlin_practice_backend.vo

import io.swagger.annotations.ApiModel

/**
 * @author shenjies88
 * @since 2020/8/23-7:49 PM
 */
@ApiModel("实体分页返回")
class PageVo<T> {
    var list: Array<T>? = null
    var total: Int? = null
}