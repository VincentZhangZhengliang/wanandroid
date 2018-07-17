package com.python.wanandroid.view.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.DisplayMetrics
import android.view.*
import android.widget.Button
import com.python.wanandroid.R
import com.python.wanandroid.utils.Constant.hours
import com.python.wanandroid.utils.Constant.minutes
import com.python.wanandroid.view.wheelview.ArrayWheelAdapter
import com.python.wanandroid.view.wheelview.WheelView
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.Function4

class TimePickerDialog : DialogFragment() {

    private lateinit var btnConfirm: Button
    private lateinit var wvStartHour: WheelView
    private lateinit var wvStartMinute: WheelView
    private lateinit var wvEndHour: WheelView
    private lateinit var wvEndMinute: WheelView

    companion object {
        fun getInstance(): TimePickerDialog {
            val dialog = TimePickerDialog()
            val bundle = Bundle()
            dialog.arguments = bundle
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomDialog)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = layoutInflater.inflate(R.layout.dialog_time_pick, container, false)
        initView(view)
        initListener()
        return view
    }

    private fun initListener() {

        val startHourObservable = Observable.create(ObservableOnSubscribe<Int> { emitter ->
            wvStartHour.addChangingListener { _, _, newValue ->
                emitter.onNext(newValue)
            }
        })

        val startMinObservable = Observable.create(ObservableOnSubscribe<Int> { emitter ->
            wvStartMinute.addChangingListener { _, _, newValue ->
                emitter.onNext(newValue)
            }
        })


        val endHourObservable = Observable.create(ObservableOnSubscribe<Int> { emitter ->
            wvEndHour.addChangingListener { _, _, newValue ->
                emitter.onNext(newValue)
            }
        })

        val endMinObservable = Observable.create(ObservableOnSubscribe<Int> { emitter ->
            wvEndMinute.addChangingListener { _, _, newValue ->
                emitter.onNext(newValue)
            }
        })

        Observable.combineLatest(startHourObservable, startMinObservable,
                endHourObservable, endMinObservable,
                Function4<Int, Int, Int, Int, Unit> { t1, t2, t3, t4 ->
//                    Timber.e("t1 = $t1,t2 = $t2,t3 = $t3,t4 = $t4")
                    if (t2 == 59) {
                        wvEndHour.currentItem = t1 + 1
                        wvEndMinute.currentItem = 0
                    }
                }).subscribe {

        }

        btnConfirm.setOnClickListener {
            val confirmListener = activity as OnConfirmListener
            confirmListener.onConfirm(wvStartHour.currentItem, wvStartMinute.currentItem, wvEndHour.currentItem, wvEndMinute.currentItem)
        }

    }

    private fun initView(view: View) {
        btnConfirm = view.findViewById(R.id.btn_confirm)
        wvStartHour = view.findViewById(R.id.wv_start_hour)
        wvStartHour.adapter = ArrayWheelAdapter(hours)
        wvStartHour.isCyclic = true
        wvStartMinute = view.findViewById(R.id.wv_start_minute)
        wvStartMinute.adapter = ArrayWheelAdapter(minutes)
        wvStartMinute.isCyclic = true
        wvEndHour = view.findViewById(R.id.wv_end_hour)
        wvEndHour.adapter = ArrayWheelAdapter(hours)
        wvEndHour.isCyclic = true
        wvEndMinute = view.findViewById(R.id.wv_end_minute)
        wvEndMinute.adapter = ArrayWheelAdapter(minutes)
        wvEndMinute.isCyclic = true
    }

    override fun onStart() {
        super.onStart()
        val dm = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(dm)
        val window = dialog.window
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isCancelable = true
        dialog.setCanceledOnTouchOutside(true)
        val windowParams = window.attributes
        windowParams.dimAmount = 0.5f
        windowParams.gravity = Gravity.BOTTOM
        windowParams.width = WindowManager.LayoutParams.MATCH_PARENT
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = windowParams
    }

    override fun onResume() {
        super.onResume()
    }

    interface OnConfirmListener {
        fun onConfirm(startHour: Int = 0,startMinute: Int = 0, endHour: Int = 0, endMinute: Int = 0)
    }

}