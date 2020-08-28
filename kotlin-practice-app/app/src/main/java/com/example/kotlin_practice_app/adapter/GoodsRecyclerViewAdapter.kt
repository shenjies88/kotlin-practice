package com.example.kotlin_practice_app.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_practice_app.R
import com.example.kotlin_practice_app.callback.BaseCallback
import com.example.kotlin_practice_app.callback.MyGoodsPageCallback
import com.example.kotlin_practice_app.client.BackendClient
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.vo.AppMyGoodsPageReqVo
import com.example.kotlin_practice_app.vo.GoodsDO
import com.example.kotlin_practice_app.vo.HttpResultVo
import com.example.kotlin_practice_app.vo.PageVo
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class GoodsRecyclerViewAdapter(
    private val values: Array<GoodsDO>,
    private val activity: AppCompatActivity,
    private val toastHandler: ToastHandler
) : RecyclerView.Adapter<GoodsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.goods_fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.id.text = item.id.toString()
        holder.name.text = item.name
        holder.delete.setOnClickListener {
            MaterialAlertDialogBuilder(activity)
                .setTitle("确定要删除吗")
                .setPositiveButton("确定") { dialog, which ->
                    //调用接口
                    BackendClient.AppGoods.delete(
                        arrayOf(item.id!!.toInt()),
                        DeleteGoodsCallback(activity, toastHandler)
                    )
                    dialog.cancel()
                }
                .setNegativeButton("取消") { dialog, which ->
                    dialog.cancel()
                }
                .show()
        }
    }

    override fun getItemCount(): Int = values.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id: TextView = view.findViewById(R.id.item_number)
        val name: TextView = view.findViewById(R.id.content)
        val delete: ImageView = view.findViewById(R.id.iv_delete)
    }

    /**
     * 删除商品回调
     */
    class DeleteGoodsCallback(
        private val activity: AppCompatActivity,
        private val toastHandler: ToastHandler,
    ) : BaseCallback(toastHandler), Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("DeleteGoodsCallback-onFailure", e.stackTraceToString())
            sendToast("删除商品求出现异常")
        }

        override fun onResponse(call: Call, response: Response) {
            try {
                val resultString = response.body!!.string()
                Log.i("DeleteGoodsCallback-onResponse", resultString)
                val resultVo = GsonUtil.fromJson<HttpResultVo<PageVo<GoodsDO>>>(
                    resultString,
                    object : TypeToken<HttpResultVo<PageVo<GoodsDO>>>() {}.type
                )
                if (!authentication(resultVo, activity)) {
                    return
                }
                sendToast("删除成功")
                BackendClient.AppGoods.page(
                    AppMyGoodsPageReqVo(),
                    MyGoodsPageCallback(activity, toastHandler)
                )
            } catch (e: Exception) {
                Log.e("DeleteGoodsCallback-onResponse", e.stackTraceToString())
                sendToast("删除商品求出现异常")
            }
        }
    }
}