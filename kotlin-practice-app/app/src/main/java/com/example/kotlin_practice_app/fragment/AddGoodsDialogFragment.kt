package com.example.kotlin_practice_app.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.kotlin_practice_app.R
import com.example.kotlin_practice_app.callback.BaseCallback
import com.example.kotlin_practice_app.callback.MyGoodsPageCallback
import com.example.kotlin_practice_app.client.BackendClient
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.vo.AppMyGoodsPageReqVo
import com.example.kotlin_practice_app.vo.HttpResultVo
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class AddGoodsDialogFragment(
    private val context: AppCompatActivity,
    private val toastHandler: ToastHandler
) : DialogFragment() {

    //控件
    private lateinit var editGoodsName: TextInputLayout
    private lateinit var btCancel: Button
    private lateinit var btDetermine: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { fragmentActivity ->
            val builder = AlertDialog.Builder(fragmentActivity)
            val inflater = requireActivity().layoutInflater
            val layout = inflater.inflate(R.layout.add_goods_dialog, null)
            builder.setView(layout)
            editGoodsName = layout.findViewById(R.id.edit_goods_name)
            btCancel = layout.findViewById(R.id.bt_cancel)
            btDetermine = layout.findViewById(R.id.bt_determine)

            btDetermine.setOnClickListener { btDetermine ->
                if (editGoodsName.editText!!.text.toString().isBlank()) {
                    val msg = Message()
                    msg.obj = "请输入商品名称"
                    toastHandler.sendMessage(msg)
                    return@setOnClickListener
                }
                BackendClient.AppGoods.insert(
                    editGoodsName.editText!!.text.toString(),
                    InsertGoodsCallback(this, context, toastHandler)
                )
                dialog!!.cancel()
            }

            btCancel.setOnClickListener {
                dialog!!.cancel()
            }
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        fun newInstant(
            activity: AppCompatActivity,
            toastHandler: ToastHandler
        ): AddGoodsDialogFragment = AddGoodsDialogFragment(activity, toastHandler)
    }

    /**
     * 增加商品回调
     */
    class InsertGoodsCallback(
        private val fragment: AddGoodsDialogFragment,
        private val context: AppCompatActivity,
        private val toastHandler: ToastHandler,
    ) : BaseCallback(toastHandler), Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("InsertGoodsCallback-onFailure", e.stackTraceToString())
            sendToast("我的商品列表分页求出现异常")
        }

        override fun onResponse(call: Call, response: Response) {
            try {
                val resultString = response.body!!.string()
                Log.i("InsertGoodsCallback-onResponse", resultString)
                val resultVo = GsonUtil.fromJson<HttpResultVo<Nothing>>(
                    resultString,
                    object : TypeToken<HttpResultVo<Nothing>>() {}.type
                )
                if (!authentication(resultVo, context)) {
                    return
                }
                sendToast("增加商品成功")
                BackendClient.AppGoods.page(
                    AppMyGoodsPageReqVo(),
                    MyGoodsPageCallback(context, toastHandler)
                )
            } catch (e: Exception) {
                Log.e("InsertGoodsCallback-onResponse", e.stackTraceToString())
                sendToast("我的商品列表分页求出现异常")
            }
        }
    }

}