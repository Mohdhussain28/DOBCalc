package com.example.dobcalc

import android.app.DatePickerDialog
import android.content.DialogInterface.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private var tvMinutes: TextView?=null
    private var tvSelectDate: TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var calndrBtn: Button=findViewById(R.id.calenderbtn)
        tvSelectDate=findViewById(R.id.tvSelectDate)
        tvMinutes=findViewById(R.id.MinutesDates)

        calndrBtn.setOnClickListener {
            calenderPicker()
        }

    }

   private fun calenderPicker(){
        val mycalender= Calendar.getInstance()
        var year=mycalender.get(Calendar.YEAR)
        var month=mycalender.get(Calendar.MONTH)
        var day=mycalender.get(Calendar.DAY_OF_MONTH)
        var showDate=DatePickerDialog(this,{_,year,month,dayOfMonth ->
            Toast.makeText(this, "work", Toast.LENGTH_SHORT).show()

            var selectedDate="${dayOfMonth}/${month+1}/${year}"
            tvSelectDate?.text=selectedDate
            var sdf=SimpleDateFormat("dd/mm/yyy", Locale.ENGLISH)

            val theDate=sdf.parse(selectedDate)
            theDate?.let { var dateInMinutes=theDate.time/60000

                var currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {  var currentDateInMinutes=currentDate.time/60000

                    var differenceinMinutes=currentDateInMinutes-dateInMinutes

                    tvMinutes?.text= differenceinMinutes.toString() } }
        },
            year, month, day
        )
        showDate.datePicker.maxDate =System.currentTimeMillis() -86400000
        showDate.show()
    }
}
