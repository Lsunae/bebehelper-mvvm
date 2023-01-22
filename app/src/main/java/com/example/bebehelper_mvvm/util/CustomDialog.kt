package com.example.bebehelper_mvvm.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.View
import com.example.bebehelper_mvvm.R
import kotlinx.android.synthetic.main.dialog.*

class CustomDialog(context: Context) : AlertDialog(context) {
    private val dialog = Dialog(context)
    private lateinit var okClickListener: OkClickListener
    private lateinit var onReadMoreClickListener: OnReadMoreClickListener
    private var directPrice = 0L

    interface OkClickListener {
        fun okClick()
    }

    fun setOkClickListener(okClick: OkClickListener) {
        this.okClickListener = okClick
    }

    interface OnReadMoreClickListener {
        fun readMore()
    }

    fun setOnReadMoreClickListener(onReadMoreClickListener: OnReadMoreClickListener) {
        this.onReadMoreClickListener = onReadMoreClickListener
    }

    /**
     * 기본 다이얼로그
     */
    fun showDialog(title: String, detail: String, isSetOk: Boolean) {
        dialog.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함
        dialog.setContentView(R.layout.dialog)

        // title/detail message 없을 경우 뷰 예외처리
        when {
            title == "" -> {
                dialog.tv_title.visibility = View.GONE
                dialog.tv_detail.visibility = View.VISIBLE
            }
            detail == "" -> {
                dialog.tv_title.visibility = View.VISIBLE
                dialog.tv_detail.visibility = View.GONE
            }
            else -> {
                dialog.tv_title.visibility = View.VISIBLE
                dialog.tv_detail.visibility = View.VISIBLE
            }
        }

        dialog.tv_ok.visibility = View.VISIBLE
        dialog.ll_fix.visibility = View.GONE

        setBaseDialog(title, detail, isSetOk)
    }

    /**
     * 다이얼로그 (확인, 취소)
     */
    fun showProposalDialog(title: String, detail: String, okText: String, cancelText: String, isSetOk: Boolean) {
        dialog.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함
        dialog.setContentView(R.layout.dialog)

        // title/detail message 없을 경우 뷰 예외처리
        when {
            title == "" -> {
                dialog.tv_title.visibility = View.GONE
                dialog.tv_detail.visibility = View.VISIBLE
            }
            detail == "" -> {
                dialog.tv_title.visibility = View.VISIBLE
                dialog.tv_detail.visibility = View.GONE
            }
            else -> {
                dialog.tv_title.visibility = View.VISIBLE
                dialog.tv_detail.visibility = View.VISIBLE
            }
        }

        dialog.tv_title.text = title
        dialog.tv_detail.text = detail
        dialog.tv_ok.visibility = View.GONE
        dialog.ll_fix.visibility = View.VISIBLE
        dialog.tv_ok_text.text = okText
        dialog.tv_cancel.text = cancelText
        dialog.tv_ok_text.setTextColor(context.resources.getColor(R.color.gray_900))
        dialog.tv_cancel.setTextColor(context.resources.getColor(R.color.gray_600))
        dialog.window?.setBackgroundDrawable(Utils.drawable(context, R.drawable.bg_round_dialog))

        // 취소
        dialog.tv_cancel.setOnClickListener { dialog.dismiss() }
        // 확인
        dialog.tv_ok_text.setOnClickListener {
            if (isSetOk) {
                okClickListener.okClick()
            }
            dialog.dismiss()
        }
        dialog.show()
    }

    /** 기본 다이얼로그 셋팅 */
    private fun setBaseDialog(title: String, detail: String, isSetOk: Boolean) {
        dialog.tv_title.text = title
        dialog.tv_detail.text = detail
        dialog.tv_ok.text = Utils.string(context, R.string.ok)
        dialog.tv_ok.setTextColor(Utils.color(context, "gray_900"))
        dialog.window?.setBackgroundDrawable(Utils.drawable(context, R.drawable.bg_round_dialog))

        // 확인 버튼
        dialog.tv_ok.setOnClickListener {
            if (isSetOk) {
                okClickListener.okClick()
            }
            dialog.dismiss()
        }
        dialog.show()
    }

    fun setDirectPrice(directPrice: Long) {
        this.directPrice = directPrice
    }
}