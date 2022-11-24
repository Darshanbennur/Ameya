package com.example.uia.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uia.R
import ir.mahozad.android.PieChart

class ResultActivity : AppCompatActivity() {

    lateinit var results : ArrayList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val pieChart = findViewById<PieChart>(R.id.pieChart)
        results = intent.getIntegerArrayListExtra("arrayList") as ArrayList<Int>
        var sum : Double = intent.getDoubleExtra("sum", 1.0)
        Toast.makeText(applicationContext,sum.toString(), Toast.LENGTH_SHORT).show()


        pieChart.slices = listOf(
            PieChart.Slice((results.get(0)/sum).toFloat(), Color.GREEN, label = "Realistic", labelColor = Color.BLACK),
            PieChart.Slice((results.get(1)/sum).toFloat(), Color.BLUE,label = "Investigative", labelColor = Color.BLACK),
            PieChart.Slice((results.get(2)/sum).toFloat(), Color.MAGENTA,label ="Artistic", labelColor = Color.BLACK),
            PieChart.Slice((results.get(3)/sum).toFloat(), Color.RED,label ="Social", labelColor = Color.BLACK),
            PieChart.Slice((results.get(4)/sum).toFloat(), Color.GRAY,label ="Enterprising", labelColor = Color.BLACK),
            PieChart.Slice((results.get(5)/sum).toFloat(), Color.CYAN,label ="Conventional", labelColor = Color.BLACK),

        )
    }
}