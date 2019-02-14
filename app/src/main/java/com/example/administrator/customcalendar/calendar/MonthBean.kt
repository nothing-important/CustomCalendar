package com.example.administrator.customcalendar.calendar

class MonthBean (){
    //flag 0 偏移量 1 正常值
    var flag : Int = 0
    var currentYear : Int = 0
    var currentMonth : Int = 0
    var currentDay : Int = 0
    var bean : Bean? = null

    data class Bean(var year : Int,
                    var month : Int,
                    var day : Int){
    }
}