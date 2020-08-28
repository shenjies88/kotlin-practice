package com.example.kotlin_practice_app.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.os.Message
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.kotlin_practice_app.R
import com.example.kotlin_practice_app.callback.InsertGoodsCallback
import com.example.kotlin_practice_app.callback.UpdateGoodsCallback
import com.example.kotlin_practice_app.client.BackendClient
import com.example.kotlin_practice_app.contant.AppConstant.INSERT
import com.example.kotlin_practice_app.contant.AppConstant.UPDATE
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.vo.AppMyGoodsUpdateReqVo
import com.google.android.material.textfield.TextInputLayout

class GoodsDialogFragment(
    private val context: AppCompatActivity,
    private val toastHandler: ToastHandler,
    private val type: String,
    private val goodsId: Array<Int?>
) : DialogFragment() {

    constructor(activity: AppCompatActivity, toastHandler: ToastHandler, type: String) : this(
        activity,
        toastHandler,
        type,
        arrayOf()
    )

    //控件
    private lateinit var editGoodsName: TextInputLayout
    private lateinit var btCancel: Button
    private lateinit var btDetermine: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { fragmentActivity ->
            val builder = AlertDialog.Builder(fragmentActivity)
            val inflater = requireActivity().layoutInflater
            val layout = inflater.inflate(R.layout.goods_dialog, null)
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
                when (type) {
                    INSERT -> {
                        BackendClient.AppGoods.insert(
                            editGoodsName.editText!!.text.toString(),
                            InsertGoodsCallback(context, toastHandler)
                        )
                    }
                    UPDATE -> {
                        BackendClient.AppGoods.update(
                            AppMyGoodsUpdateReqVo(
                                goodsId[0],
                                editGoodsName.editText!!.text.toString()
                            ),
                            UpdateGoodsCallback(context, toastHandler)
                        )
                    }
                }
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
            toastHandler: ToastHandler,
            type: String,
            goodsId: Array<Int?>
        ): GoodsDialogFragment = GoodsDialogFragment(activity, toastHandler, type, goodsId)

        fun newInstant(
            activity: AppCompatActivity,
            toastHandler: ToastHandler,
            type: String
        ): GoodsDialogFragment = GoodsDialogFragment(activity, toastHandler, type)
    }

}