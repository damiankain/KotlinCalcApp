package com.damiankain.kotlincalcapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.damiankain.kotlincalcapp.databinding.ActivityMainBinding

// Пока не очень понимаю правильно ли я движусь, потому что пока нет понимания как соединить
// activity_main , MainActuvity и мой калькулятор из прошлого проекта???

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding  // правильно ли использовать байндинг?
    
    fun evaluateExpression(string: String, clear: Boolean) {
        if(clear) {
            binding.bRes.text = ""
            binding.bExp.append(string)
        } else {
            binding.bExp.append(binding.bRes.text)
            binding.bExp.append(string)
            binding.bRes.text = ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // правильно ли я получаю имена переменных из activity_main?
        binding.b0.setOnClickListener { evaluateExpression ("0", true) }
        binding.b1.setOnClickListener { evaluateExpression ("1", true) }
        binding.b2.setOnClickListener { evaluateExpression ("2", true) }
        binding.b3.setOnClickListener { evaluateExpression ("4", true) }
        binding.b4.setOnClickListener { evaluateExpression ("4", true) }
        binding.b5.setOnClickListener { evaluateExpression ("5", true) }
        binding.b6.setOnClickListener { evaluateExpression ("6", true) }
        binding.b7.setOnClickListener { evaluateExpression ("7", true) }
        binding.b8.setOnClickListener { evaluateExpression ("8", true) }
        binding.b9.setOnClickListener { evaluateExpression ("9", true) }
        binding.bPlus.setOnClickListener { evaluateExpression("+", clear = true) }
        binding.bMinus.setOnClickListener { evaluateExpression("-", clear = true) }
        binding.bMultiple.setOnClickListener { evaluateExpression("*", clear = true) }
        binding.bDivide.setOnClickListener { evaluateExpression("/", clear = true) }
        binding.bDot.setOnClickListener { evaluateExpression(".", clear = true) }
        binding.bAC.setOnClickListener {
            binding.bExp.text = ""
            binding.bRes.text = ""
        }


        /*binding.bEqual.setOnClickListener {
            val text = binding.bExp.text.toString()
            val expression : StringBuilder = java.lang.StringBuilder(text)

            val result = expression.evaluate()
            val longResult = result.toLong()
            if (result == longResult.toDouble()) {
                binding.bExp.text = longResult.toString()
            } else {
                binding.bExp.text = result.toString()
            }
        }*/

    }


}