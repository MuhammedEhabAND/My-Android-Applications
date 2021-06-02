package com.example.calculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

var lastNumeric: Boolean = false
var lastDot: Boolean = false
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun onDigit(view :View){
        lastNumeric =true
        tv_input.append((view as Button).text)
    }
    fun onClear(view: View){
        tv_input.text =""
        lastNumeric = false
        lastDot = false

    }
    fun onDecimal(view:View){
        if(lastNumeric && !lastDot){
            tv_input.append(".")
            lastNumeric =false
            lastDot = true
        }

    }
    private fun removeZero(result : String): String {
        var value = result
        if(result.contains(".0"))
            value= result.substring(0 ,result.length -2)
        return value

    }
    fun onOperator(view: View){
        if(lastNumeric && !isOperatorAdded(tv_input.text.toString())){
            tv_input.append((view as Button).text)
            lastNumeric =false
            lastDot = false

        }

    }
    private fun  isOperatorAdded(value: String) : Boolean{
        return  if (value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    ||value.contains("X")
                    ||value.contains("+")
                    ||value.contains("-")
        }
    }
    fun onEqual(view : View){

        if(lastNumeric){
            var tvValue = tv_input.text.toString()
            var prefix =""
            try{
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one  = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix+ one
                    }
                    tv_input.text = removeZero((one.toDouble() - two.toDouble()).toString())

                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one  = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix+ one
                    }
                    tv_input.text = removeZero((one.toDouble() + two.toDouble()).toString())

                }else if(tvValue.contains("X")){
                    val splitValue = tvValue.split("X")
                    var one  = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix+ one
                    }
                    tv_input.text = removeZero((one.toDouble() * two.toDouble()).toString())

                }else if(tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tv_input.text = removeZero((one.toDouble() / two.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
}

