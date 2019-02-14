package com.example.administrator.customcalendar.calendar

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.administrator.customcalendar.R

class CalendarOuterAdapter(var context: Context, var list: ArrayList<ArrayList<MonthBean>>) :
        RecyclerView.Adapter<CalendarOuterAdapter.CalendarOutterAdapter_VH>() {

    var inflater : LayoutInflater? = null

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CalendarOutterAdapter_VH {
        var view = inflater?.inflate(R.layout.calendar_outer_item_view , p0 , false)
        return CalendarOutterAdapter_VH(view!!)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CalendarOutterAdapter_VH, psn: Int) {
        var gridLayoutManager = GridLayoutManager(context , 7)
        holder.outer_recycler.layoutManager = gridLayoutManager
        var calendarDayAdapter = CalendarDayAdapter(context , list[psn])
        holder.outer_recycler.adapter = calendarDayAdapter
//        var dividerGridItemDecoration = DividerGridItemDecoration(context , 1 , R.color.gray_97)
//        if (holder.outer_recycler.tag == null || !holder.outer_recycler.tag.equals("isFirst")){
//            holder.outer_recycler.addItemDecoration(dividerGridItemDecoration)
//        }
    }

    class CalendarOutterAdapter_VH(var view: View) : RecyclerView.ViewHolder(view){
        var outer_recycler : RecyclerView = view.findViewById(R.id.outer_recycler)
    }
}