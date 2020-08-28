package com.example.kotlin_practice_app.callback

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_practice_app.R
import com.example.kotlin_practice_app.fragment.GoodsFragment
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.vo.GoodsDO
import com.example.kotlin_practice_app.vo.HttpResultVo
import com.example.kotlin_practice_app.vo.PageVo
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * 我的商品列表分页回调
 */
class MyGoodsPageCallback(
    private val context: AppCompatActivity,
    toastHandler: ToastHandler,
) : BaseCallback(toastHandler), Callback {
    override fun onFailure(call: Call, e: IOException) {
        Log.e("MyGoodsPage-onFailure", e.stackTraceToString())
        sendToast("我的商品列表分页求出现异常")
    }

    override fun onResponse(call: Call, response: Response) {
        try {
            val resultString = response.body!!.string()
            Log.i("MyGoodsPage-onResponse", resultString)
            val resultVo = GsonUtil.fromJson<HttpResultVo<PageVo<GoodsDO>>>(
                resultString,
                object : TypeToken<HttpResultVo<PageVo<GoodsDO>>>() {}.type
            )
            if (!authentication(resultVo, context)) {
                return
            }
            val data = resultVo.data
            val goodsFragment = GoodsFragment.newInstance(data?.list ?: arrayOf())
            val transaction = context.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.root_fragment_layout, goodsFragment)
            transaction.commit()
        } catch (e: Exception) {
            Log.e("MyGoodsPage-onResponse", e.stackTraceToString())
            sendToast("我的商品列表分页求出现异常")
        }
    }
}