package com.shenjies88.practice.kotlin_practice_backend.entity

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author shenjies88
 * @since 2020/8/23-5:21 PM
 */
@ApiModel("产品实体")
class GoodsDO(
        @ApiModelProperty("主键")
        var id: Int?,
        @ApiModelProperty("用户id")
        var userId: Int?,
        @ApiModelProperty("产品名称")
        var name: String?)