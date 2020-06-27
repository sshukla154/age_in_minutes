package com.learn.kotlin.age_calculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {
            view -> clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View){
        var myCalender = Calendar.getInstance()
        var currentYear = myCalender.get(Calendar.YEAR);
        var currentMonth = myCalender.get(Calendar.MONTH);
        var currentDayOfMonth = myCalender.get(Calendar.DAY_OF_MONTH);
        /*
        NOTE: currentYear, currentMonth and currentDayOfMonth is today's but
        the one used in DatePickerDialog.OnDateSetListener is the select Year, Month and Day
        */

        var datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "The Chosen year is $selectedYear, month is ${selectedMonth + 1} and dayOfMonth is $selectedDayOfMonth", Toast.LENGTH_LONG).show()

            // Selected Date in String
            var selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/${selectedYear}"
            tvSelectedDate.setText(selectedDate)

            /*
             SimpleDateFormat is a concrete class for formatting and parsing dates in a locale-sensitive manner.
             It allows for formatting (date → text), parsing (text → date), and normalization.
             */

            var simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            //parsing the string date into our required format
            var theDate = simpleDateFormat.parse(selectedDate)

            // Changed the parsed selected date into Min
            var dateInMin = theDate!!.time / 60000

            //Current date into our required format
            var currentDateInString = simpleDateFormat.format(System.currentTimeMillis())

            // Parse Formatted Current date into date
            var theCurrentDate = simpleDateFormat.parse(currentDateInString)

            // Now change the parsed Current (is in millisecond) Date into Min
            var currentDateInMin = theCurrentDate!!.time / 60000

            var diffInMin = currentDateInMin - dateInMin

            //now adding this diffInMin to tvSelectedDateInMinutes
            tvSelectedDateInMinutes.setText(diffInMin.toString())

        }, currentYear, currentMonth, currentDayOfMonth)
        /*
        NOTE: 86400000 millisecond of 1 day
        NOTE: setting Max Date to disable date higher than current date and time
        */
        datePickerDialog.datePicker.setMaxDate(Date().time - 86400000)
        datePickerDialog.show()
    }
}