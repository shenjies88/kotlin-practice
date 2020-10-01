package com.example.kotlin_practice_app.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_practice_app.R
import com.example.kotlin_practice_app.adapter.GoodsRecyclerViewAdapter
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.vo.GoodsDO
import com.google.gson.reflect.TypeToken

/**
 * A fragment representing a list of Items.
 */
class GoodsFragment(private val toastHandler: ToastHandler) : Fragment() {

    private var goodsList: Array<GoodsDO> = emptyArray()
    private lateinit var activity: AppCompatActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            goodsList = GsonUtil.fromJson(
                it.getString(GOODS_LIST),
                object : TypeToken<Array<GoodsDO>>() {}.type
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.goods_fragment_item_list, container, false)
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                adapter = GoodsRecyclerViewAdapter(goodsList, activity, toastHandler)
            }
        }
        return view
    }

    companion object {

        const val GOODS_LIST = "GOODS_LIST"

        fun newInstance(goodsList: Array<GoodsDO>?, toastHandler: ToastHandler) =
            GoodsFragment(toastHandler).apply {
                arguments = Bundle().apply {
                    putString(GOODS_LIST, GsonUtil.toJson(goodsList))
                }
            }
    }
}