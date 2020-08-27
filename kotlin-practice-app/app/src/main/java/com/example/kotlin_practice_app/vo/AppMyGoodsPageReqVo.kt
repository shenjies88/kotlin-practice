package com.example.kotlin_practice_app.vo

class AppMyGoodsPageReqVo(size: Int, num: Int) : PageReqVo(size, num) {
    constructor() : this(20, 1)
}