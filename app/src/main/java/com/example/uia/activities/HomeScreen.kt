package com.example.uia.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.example.uia.constant.Constants
import com.example.uia.databinding.ActivityHomeScreenBinding
import com.example.uia.databinding.ActivityMainBinding
import com.example.uia.models.Ques

class HomeScreen : AppCompatActivity() {
    lateinit var binding : ActivityHomeScreenBinding
    lateinit var results : ArrayList<Int>
    var count = 0
    var R = 0
    var I = 0
    var A = 0
    var S = 0
    var E = 0
    var C = 0
    lateinit var listView : ListView

    private lateinit var arrayList : ArrayList<Ques>
    var question = arrayListOf<String>("I like to","I'm Good at","I am","I wouldn’t mind","I Enjoy")

    val options = arrayOf(
        arrayOf<String>("Work on cars", "Work in team", "Organize things", "Read about art and music", "Clear instruction to follow", "Influence people", "Do experiment ",
            "Teach or train people ", "Help people solve prob ", "Take responsibility ", "Analyze prob ", "Start my business ",
            "Get into discussion ", "Lead"),
        arrayOf<String>("Working independent", "Healing people", "Assembling things", "Working with numbers", "Keeping record",
                "Working outdoor", "Typing", "Cook", "Puzzles"),
        arrayOf<String>("Ambitious", "Creative people", "Practical", "Math", "Building things", "Play instruments", "helper"),
        arrayOf<String>("working 8 hours per day", "Helping people", "Giving speeches", "acting", "Care animal", "Selling things"),
        arrayOf<String>("Creative writing", "Science", "Figure how things work", "Learning about cultures", "Pay attention", "Work in office",
            "To draw", "Give speeches"),
    )

    val answerSet = arrayOf(
        arrayOf<String>("R", "S", "C","A","C","E","I","S","S","E","I","E","S","E"),
        arrayOf<String>("A", "S", "R","I","C","R","C","R","I"),
        arrayOf<String>("E", "A", "R","I","R","A","S"),
        arrayOf<String>("C", "S", "E","A","R","E"),
        arrayOf<String>("A", "I", "I","S","C","C","A","E"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        arrayList = ArrayList()
        binding.mainQues.text = "Q." + question[count]
        for (i in options[count]){
            var ques = Ques(i.toString(),false)
            arrayList.add(ques)
        }
        binding.listView.adapter = Adapter(this,arrayList)

    }

    fun nextQuestion(view : View){
        for(i in 0..arrayList.size-1){
            if (arrayList[i].status){
                var char = answerSet[count][i]
                when (char) {
                    "R" -> R++
                    "I" -> I++
                    "A" -> A++
                    "S" -> S++
                    "E" -> E++
                    "C" -> C++
                    else -> { // Note the block
                        Toast.makeText(applicationContext,"Invalid", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        count++;
        if(count < 5){
            arrayList.clear()
            binding.mainQues.text = "Q." + question[count]
            for (i in options[count]){
                var ques = Ques(i.toString(),false)
                arrayList.add(ques)
            }
            binding.listView.adapter = Adapter(this,arrayList)
            binding.count.text = (count+1).toString() + "/5"
        }else{
            Log.i("R",R.toString())
            Log.i("I",I.toString())
            Log.i("A",A.toString())
            Log.i("S",S.toString())
            Log.i("E",E.toString())
            Log.i("C",C.toString())
            // binding.btnext.isEnabled = false
            binding.btnext.text = "Completed"
            Toast.makeText(applicationContext,"Done for Now", Toast.LENGTH_SHORT).show()

            results = ArrayList()
            results.add(Integer.valueOf(R.toString()))
            results.add(Integer.valueOf(I.toString()))
            results.add(Integer.valueOf(A.toString()))
            results.add(Integer.valueOf(S.toString()))
            results.add(Integer.valueOf(E.toString()))
            results.add(Integer.valueOf(C.toString()))

            var sum : Double = (R+I+A+S+E+C).toDouble();
            var intent = Intent(this,ResultActivity::class.java)
            intent.putIntegerArrayListExtra("arrayList",results)
            intent.putExtra("sum", sum)
            startActivity(intent)
            /*var intent = Intent(this, ResultActivity::class.java)
            // intent.putExtra("currentUser", currentUser)
            startActivity(intent)*/
        }

    }


}