package com.example.administrator.customcalendar.calendar

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.administrator.customcalendar.R

class CalendarDayAdapter(var context: Context , var list : ArrayList<MonthBean>) : RecyclerView.Adapter<CalendarDayAdapter.CalendarDayAdapter_VH>(){

    var inflater : LayoutInflater? = null
    var containerWidth : Int = 0

    init {
        inflater = LayoutInflater.from(context)
        containerWidth = getWindowWidth(context) / 7 - 1
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CalendarDayAdapter_VH {
        var view = inflater?.inflate(R.layout.calendar_day_view , p0 , false)
        return CalendarDayAdapter_VH(view!!)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CalendarDayAdapter_VH, psn: Int) {
        if (holder.item_day_container.tag == null || !holder.item_day_container.tag.equals("isFirst")){
            val layoutParams = holder.item_day_container.layoutParams
            layoutParams.width = containerWidth
            layoutParams.height = containerWidth
            holder.item_day_container.layoutParams = layoutParams
            holder.item_day_container.tag = "isFirst"
        }
        if (list[psn].flag == 0){//偏移量
            holder.item_day_tv.text = ""
        }else{//正常值
            holder.item_day_tv.text = list[psn].bean?.day.toString()
            //当天日期
            if (list[psn].year != list[psn].bean?.year)return
            if (list[psn].month != list[psn].bean?.month)return
            if (list[psn].day != list[psn].bean?.day)return
            holder.item_day_tv.setTextColor(context.resources.getColor(R.color.colorAccent
            ))
        }
    }


    class CalendarDayAdapter_VH(view: View) : RecyclerView.ViewHolder(view){
        var item_day_container : RelativeLayout = view.findViewById(R.id.item_day_container)
        var item_day_tv : TextView = view.findViewById(R.id.item_day_tv)
    }

    private fun getWindowWidth(context: Context) : Int{
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.widthPixels
    }
}