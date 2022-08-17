package com.damiankain.kotlincalcapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.damiankain.kotlincalcapp.databinding.FragmentScreenBinding


class ScreenFragment : Fragment() {

    private var _binding: FragmentScreenBinding? = null
    private val binding : FragmentScreenBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentScreenBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        //Digits
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
        binding.bDot.setOnClickListener { evaluateExpression(".", clear = true) }

        //Operators
        binding.bPlus.setOnClickListener { evaluateExpression("+", clear = true) }
        binding.bMinus.setOnClickListener { evaluateExpression("-", clear = true) }
        binding.bMultiple.setOnClickListener { evaluateExpression("*", clear = true) }
        binding.bDivide.setOnClickListener { evaluateExpression("/", clear = true) }
        binding.bLP.setOnClickListener { evaluateExpression("(", clear = true) }
        binding.bRP.setOnClickListener { evaluateExpression(")", clear = true) }

        binding.bAC.setOnClickListener {
            binding.bExp.text = ""
            binding.bRes.text = ""
        }

        binding.bEqual.setOnClickListener {
            val text = binding.bExp.text.toString()
            val bsn = CalculatorLogic()
            val answer = text.let { bsn.buildStringPolishNotation(it) }.let { bsn.getAnswer(it) }

            val longResult = answer.toLong()
            if (answer == longResult.toDouble()) {
                binding.bRes.text = longResult.toString()
            } else {
                binding.bRes.text = answer.toString()
            }
            binding.bExp.text = ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}