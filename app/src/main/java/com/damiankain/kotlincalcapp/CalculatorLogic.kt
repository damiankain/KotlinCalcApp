package com.damiankain.kotlincalcapp

class CalculatorLogic {

    fun buildStringPolishNotation(exp: String): String { // строю строку польской нотации

        val stackOperators = ArrayDeque<Char>() // стек для знаков операций
        var temp = ""  // временная переменная строки для записи чисел через пробел
        var priority: Int  // получаем приоритет символа

        for (i in exp.indices) {
            priority = getPriority(exp[i])
            when (priority) {
                0 -> temp += exp[i]

                1 -> stackOperators.addLast(exp[i])

                2, 3 -> {
                    temp += " "
                    while (!stackOperators.isEmpty()) {
                        if (getPriority(stackOperators.last()) >= priority) {
                            temp += stackOperators.removeLast()
                        } else break
                    }
                    stackOperators.addLast(exp[i])
                }
                -1 -> {
                    temp += " "
                    while (getPriority(stackOperators.last()) != 1) {
                        temp += stackOperators.removeLast()
                    }
                    stackOperators.removeLast()
                }
            }
        }
        while (!stackOperators.isEmpty()) {
            temp += stackOperators.removeLast()
        }
        return temp
    }

    fun getAnswer(rpn: String): Double { // получаем ответ из строки польской нотации
        var operand = ""
        val stack = ArrayDeque<Double>()
        var priority: Int

        try {
            for (i in 0..rpn.length) {
                priority = getPriority(rpn[i])

                when (priority) {
                    0 -> {
                        operand += rpn[i]
                    }

                    -2 -> {
                        if (operand.equals("")) {
                            continue
                        }
                        stack.addLast(operand.toDouble())
                        operand = ""
                    }

                    2, 3 -> {
                        if (operand != "") {
                            stack.addLast(operand.toDouble())
                        }
                        val b = stack.removeLast()
                        val a = stack.removeLast()
                        when (rpn[i]) {
                            '+' -> stack.addLast(b + a)
                            '-' -> stack.addLast(b - a)
                            '*' -> stack.addLast(b * a)
                            '/' -> stack.addLast(b / a)
                        }
                    }
                    else -> {
                        stack.addLast(operand.toDouble())
                        operand += rpn[i]
                    }
                }
            }
        } catch (e: StringIndexOutOfBoundsException) { // РАЗОБРАТЬСЯ ПОЧЕМУ ТАКАЯ ОШИБКА

        }
        return stack.last()
    }

    private fun getPriority(a: Char): Int { // функция определения приоритета символа
        return when (a) {
            '*', '/' -> 3
            '+', '-' -> 2
            '(' -> 1
            ')' -> -1
            ' ' -> -2
            else -> 0
        }
    }
}