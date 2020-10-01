package com.example.kotlin_practice_app.callback

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_practice_app.client.BackendClient
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.vo.AppMyGoodsPageReqVo
import com.example.kotlin_practice_app.vo.HttpResultVo
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * 修改我的商品回调
 */
class UpdateGoodsCallback(
    private val context: AppCompatActivity,
    private val toastHandler: ToastHandler,
) : BaseCallback(toastHandler), Callback {
    override fun onFailure(call: Call, e: IOException) {
        Log.e("修改我的商品回调-onFailure", e.stackTraceToString())
        sendToast("修改我的商品回调出现异常")
    }

    override fun onResponse(call: Call, response: Response) {
        try {
            val resultString = response.body!!.string()
            Log.i("修改我的商品回调-onResponse", resultString)
            val resultVo = GsonUtil.fromJson<HttpResultVo<Nothing>>(
                resultString,
                object : TypeToken<HttpResultVo<Nothing>>() {}.type
            )
            if (!authentication(resultVo, context)) {
                return
            }
            sendToast("修改商品成功")
            BackendClient.AppGoods.page(
                AppMyGoodsPageReqVo(),
                MyGoodsPageCallback(context, toastHandler)
            )
        } catch (e: Exception) {
            Log.e("修改我的商品回调-onResponse", e.stackTraceToString())
            sendToast("修改我的商品回调出现异常")
        }
    }
}