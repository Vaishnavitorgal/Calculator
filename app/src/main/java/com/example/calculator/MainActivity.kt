package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var canAddOperation = false
    private var canAddDecimal = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun equalsAction(view: View)
    {
        findViewById<TextView>(R.id.tvResults).text = calculateResult()
    }

    private fun calculateResult() : String
    {
        val digitsOperators = digitsOperators()
        if (digitsOperators.isEmpty() ) return ""

        val result = addsubCalc(digitsOperators)
        return result.toString()
    }

    private fun digitsOperators():MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentDigit = ""

        for (character in findViewById<TextView>(R.id.tvWorkings).text)
        {
            if(character.isDigit() || character == '.') {
                currentDigit += character
            }
            else {
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(character)
            }
        }

        if(currentDigit != "")
            list.add(currentDigit.toFloat())

        return list
    }

    private fun addsubCalc(passedList: MutableList<Any>): Float {

        var result = passedList[0] as Float

        for (i in passedList.indices) {

            if (passedList[i] is Char && i != passedList.lastIndex) {

                val operator = passedList[i]
                val nextDigit = passedList[i + 1] as Float

                if (operator == '+') {
                    result += nextDigit
                }

                else if (operator == "-") {
                    result -= nextDigit
                }
            }
        }
        return result
    }

    fun allClearAction(view: View)
    {
        findViewById<TextView>(R.id.tvWorkings).text=""
        findViewById<TextView>(R.id.tvResults).text=""

          canAddOperation = false
          canAddDecimal = true
    }

    fun numberAction(view: View)
    {
        if(view is Button)
        {
            if(view.text == ".") {
                if (canAddDecimal)
                    findViewById<TextView>(R.id.tvWorkings).append(view.text)

                canAddDecimal = false
            }
            else
                findViewById<TextView>(R.id.tvWorkings).append(view.text)

            canAddOperation = true
        }
    }

    fun operationAction(view : View)
    {
        if(view is Button && canAddOperation)
        {
            findViewById<TextView>(R.id.tvWorkings).append(view.text)
            canAddOperation = false
            canAddDecimal = true
        }
    }

    @SuppressLint("CutPasteId")
    fun backspaceAction(view : View)
    {
        val length = findViewById<TextView>(R.id.tvWorkings).length()
        if(length>0)
            findViewById<TextView>(R.id.tvWorkings).text=findViewById<TextView>(R.id.tvWorkings).text.subSequence(0,length-1)

    }




}

