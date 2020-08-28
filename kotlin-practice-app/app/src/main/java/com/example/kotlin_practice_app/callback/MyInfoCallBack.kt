package com.example.kotlin_practice_app.callback

import android.os.Message
import android.util.Log
import com.example.kotlin_practice_app.activity.MainActivity
import com.example.kotlin_practice_app.client.BackendClient
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.manager.UserInfoManager
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.vo.AppLoginRespVo
import com.example.kotlin_practice_app.vo.AppMyGoodsPageReqVo
import com.example.kotlin_practice_app.vo.HttpResultVo
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * 我的用户信息回调
 */
class MyInfoCallBack(
    private val context: MainActivity,
    private val toastHandler: ToastHandler,
    private val myInfoHandler: MainActivity.MyInfoHandler
) : BaseCallback(toastHandler), Callback {

    override fun onFailure(call: Call, e: IOException) {
        Log.e("MyInfoCallBack-onFailure", e.stackTraceToString())
        sendToast("我的信息请求出现异常")
    }

    override fun onResponse(call: Call, response: Response) {
        try {
            val resultString = response.body!!.string()
            Log.i("MyInfoCallBack-onResponse", resultString)
            val resultVo = GsonUtil.fromJson<HttpResultVo<AppLoginRespVo>>(
                resultString,
                object : TypeToken<HttpResultVo<AppLoginRespVo>>() {}.type
            )
            if (!authentication(resultVo, context)) {
                return
            }
            val data = resultVo.data
            UserInfoManager.saveUser(data)
            val msg = Message.obtain()
            msg.obj = data
            myInfoHandler.sendMessage(msg)
            //请求商品信息
            BackendClient.AppGoods.page(
                AppMyGoodsPageReqVo(),
                MyGoodsPageCallback(context, toastHandler)
            )
        } catch (e: Exception) {
            Log.e("MyInfoCallBack-onResponse", e.stackTraceToString())
            sendToast("我的信息请求出现异常")
        }
    }
}