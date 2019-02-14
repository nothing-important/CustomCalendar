package com.example.administrator.customcalendar.calendar

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.administrator.customcalendar.R

class CalendarWeekAdapter(var context : Context , var list: ArrayList<String>) : RecyclerView.Adapter<CalendarWeekAdapter.CalendarWeekAdapter_VH>() {

    var inflater : LayoutInflater? = null
    var containerWidth : Int = 0

    init {
        inflater = LayoutInflater.from(context)
        containerWidth = getWindowWidth(context)/7 - 1
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CalendarWeekAdapter_VH {
        var view = inflater?.inflate(R.layout.calendar_week_day_view , p0 , false)
        return CalendarWeekAdapter_VH(view!!)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CalendarWeekAdapter_VH, psn: Int) {
        val layoutParams = holder.calendar_day_item.layoutParams
        layoutParams.width = containerWidth
        layoutParams.height = containerWidth
        holder.calendar_day_item.layoutParams = layoutParams
        holder.calendar_day_item.text = list[psn]
    }


    class CalendarWeekAdapter_VH(view : View) : RecyclerView.ViewHolder(view){
        var calendar_day_item : TextView = view.findViewById(R.id.calendar_day_item)
    }

    private fun getWindowWidth(context: Context) : Int{
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.widthPixels
    }
}