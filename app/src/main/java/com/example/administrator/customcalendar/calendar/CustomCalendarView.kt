package com.example.administrator.customcalendar.calendar

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.administrator.customcalendar.R
import java.util.*
import kotlin.collections.ArrayList

class CustomCalendarView : LinearLayout {

    var calendar_recycler : RecyclerView? = null
    var weekList : ArrayList<String> = ArrayList()
    var dataList : ArrayList<ArrayList<MonthBean>> = ArrayList()

    constructor(context: Context) : super(context){initView(context)}
    constructor(context: Context , attributeSet: AttributeSet) : super(context , attributeSet){initView(context)}
    constructor(context: Context , attributeSet: AttributeSet , defStyleAttr : Int) : super(context , attributeSet , defStyleAttr){initView(context)}

    private fun initView(context: Context){
        initData()
        val inflateView = LayoutInflater.from(context).inflate(R.layout.custom_calendar_view, this , false)
        var calendar_desc : TextView = inflateView.findViewById(R.id.calendar_desc)
        createWeekDesc(inflateView , context)
        calendar_recycler = inflateView.findViewById(R.id.calendar_recycler)
        var linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = HORIZONTAL
        calendar_recycler?.layoutManager = linearLayoutManager
        var calendarOuterAdapter = CalendarOuterAdapter(context , dataList)
        calendar_recycler?.adapter = calendarOuterAdapter
        var snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(calendar_recycler)
        calendar_recycler?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            @SuppressLint("SetTextI18n")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = calendar_recycler?.layoutManager
                if (layoutManager is LinearLayoutManager){
                    var manager : LinearLayoutManager= layoutManager
                    val itemPosition = manager.findFirstVisibleItemPosition()
                    val month = dataList[itemPosition][0].bean?.month
                    val year = dataList[itemPosition][0].bean?.year
                    calendar_desc.text = "$year - $month"
                }
            }
        })
        addView(inflateView)
    }

    private fun createWeekDesc(view: View , context: Context){
        var calendar_day : RecyclerView = view.findViewById(R.id.calendar_day)
//        var dividerGridItemDecoration = DividerGridItemDecoration(context , 1 , R.color.gray_97)
//        calendar_day.addItemDecoration(dividerGridItemDecoration)
        var linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = HORIZONTAL
        calendar_day.layoutManager = linearLayoutManager
        var weekAdapter = CalendarWeekAdapter(context , weekList)
        calendar_day.adapter = weekAdapter
    }

    private fun initData(){
        weekList.add("日")
        weekList.add("一")
        weekList.add("二")
        weekList.add("三")
        weekList.add("四")
        weekList.add("五")
        weekList.add("六")
        var calendar : Calendar = Calendar.getInstance()
        var currentYear = calendar.get(Calendar.YEAR)
        var currentMonth = calendar.get(Calendar.MONTH) + 1
        var currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        for (i in 1..5){
            var monthList : ArrayList<MonthBean> = ArrayList()
            val tempYear = calendar.get(Calendar.YEAR)
            val tempMonth = calendar.get(Calendar.MONTH) + 1
            calendar.set(Calendar.DATE , 1)
            val offSet = calendar.get(Calendar.DAY_OF_WEEK) - 1
            //添加偏移量
            for (i in 1..offSet){
                var monthBean = MonthBean()
                monthBean.flag = 0
                var bean = MonthBean.Bean(tempYear , tempMonth , i)
                monthBean.bean = bean
                monthList.add(monthBean)
            }
            //添加正常日期
            calendar.roll(Calendar.DATE , -1)
            val range = calendar.get(Calendar.DATE)
            for (i in 1..range){
                var monthBean = MonthBean()
                monthBean.flag = 1
                monthBean.currentYear = currentYear
                monthBean.currentMonth = currentMonth
                monthBean.currentDay = currentDay
                var bean = MonthBean.Bean(tempYear , tempMonth , i)
                monthBean.bean = bean
                monthList.add(monthBean)
            }
            //补全剩余
            val leave = 7 - (monthList.size % 7)
            for (i in 1..leave){
                var monthBean = MonthBean()
                monthBean.flag = 0
                monthList.add(monthBean)
            }
            dataList.add(monthList)
            calendar.add(Calendar.MONTH , 1)
        }
    }

}