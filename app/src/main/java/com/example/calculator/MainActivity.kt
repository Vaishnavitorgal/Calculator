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
        val last = findViewById<TextView>(R.id.tvWorkings).text.last()
        if(length>0) {
            if (last == '.') {
                canAddDecimal = true
            }
            findViewById<TextView>(R.id.tvWorkings).text =
                findViewById<TextView>(R.id.tvWorkings).text.subSequence(0, length - 1)
        }
    }
}