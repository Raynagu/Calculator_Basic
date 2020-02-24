package com.admin.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    val operators = arrayOf('+','-','*','/')
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Numbers listeners
        one.setOnClickListener { appendOnExp("1",true) }
        two.setOnClickListener { appendOnExp("2",true) }
        three.setOnClickListener { appendOnExp("3",true) }
        four.setOnClickListener { appendOnExp("4",true) }
        five.setOnClickListener { appendOnExp("5",true) }
        six.setOnClickListener { appendOnExp("6",true) }
        seven.setOnClickListener { appendOnExp("7",true) }
        eight.setOnClickListener { appendOnExp("8",true) }
        nine.setOnClickListener { appendOnExp("9",true) }
        zero.setOnClickListener { appendOnExp("0",true) }
        dot.setOnClickListener { appendOnExp(".",true) }


//    operators listeners
        plus.setOnClickListener { appendOnExp("+", false) }
        minus.setOnClickListener { appendOnExp("-", false) }
        multiply.setOnClickListener { appendOnExp("*", false) }
        divide.setOnClickListener { appendOnExp("/", false) }
        open.setOnClickListener { appendOnExp("(", false) }
        close.setOnClickListener { appendOnExp(")", false) }

//        on Clear (CE)
        CE.setOnClickListener {
            exp.text = ""
            result.text = ""
        }
//        on Back
        back.setOnClickListener {
            val str = exp.text.toString()
            if(str.isNotEmpty()){
                exp.text = str.substring(0, str.length-1)
            }
            result.text = ""
        }

//        on equals
        equal.setOnClickListener {
           try {
               val expression = ExpressionBuilder(exp.text.toString()).build()
               val res = expression.evaluate()
               val longRes = res.toLong()
               if(res == longRes.toDouble())
                   result.text = longRes.toString()
               else
                   result.text = res.toString()
           }catch (e:Exception){
               Log.d("Exception", "message : "+e.message)
           }
        }

    }


    fun appendOnExp(string: String, canClear: Boolean){
        if(result.text.isNotEmpty()){
            exp.text = ""
        }
        if(canClear){
            result.text = ""
            exp.append(string)
        }else{
//            exp.append(result.text)
            val exp1: String = exp.text.toString()+result.text
            if(exp1.isNotEmpty()) {
                val new = if(exp1.last() in operators) exp1.replace(exp1.last(), string[0]) else exp1+string[0]
                exp.text = new
                Log.i("expMessage", new)
            }else{
                exp.append(string)
            }

            result.text = ""
        }
    }
}
