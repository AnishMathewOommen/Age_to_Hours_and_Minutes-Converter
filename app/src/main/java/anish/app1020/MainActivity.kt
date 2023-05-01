package anish.app1020

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate:TextView?=null
    private var textMinutes:TextView?=null
    private var texthours:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDateVal: Button=findViewById(R.id.btnDate )
        textMinutes=findViewById(R.id.tvInminutes)
        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        texthours=findViewById(R.id.tvInhours)
        btnDateVal.setOnClickListener {
            clickDateBtn()
        }
    }
    private fun clickDateBtn(){
        var myCalendar=Calendar.getInstance()
        var Year=myCalendar.get(Calendar.YEAR)
        var Month=myCalendar.get(Calendar.MONTH)
        var day=myCalendar.get(Calendar.DAY_OF_MONTH)
        var dpd=DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedday ->
                Toast.makeText(this, "oh Wow!!!", Toast.LENGTH_LONG).show()
                val Date_Val = "$selectedday/${selectedMonth+1}/$selectedYear"
                tvSelectedDate?.text = Date_Val
                val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val thedate=sdf.parse(Date_Val)
                thedate?.let {
                    val selectedDateInHours=thedate.time/(60 * 60 * 1000)
                    val selectedDateInMinutes=thedate.time /60000
                    val currentTime=sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentTime?.let {
                        val currectDateInHours=currentTime.time/(60 * 60 * 1000)
                        val currentDateInMinutes = currentTime.time / 60000
                        val difference_time_hours=(currectDateInHours-selectedDateInHours)
                        val difference_time = (currentDateInMinutes - selectedDateInMinutes)
                        textMinutes?.text = difference_time.toString()
                        texthours?.text=difference_time_hours.toString()
                    }
                }

            },
            Year,
            Month,
            day
        )
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()


    }
}