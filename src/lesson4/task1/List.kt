@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import ru.spbstu.wheels.forEachIndexedB
import java.lang.StringBuilder
import javax.naming.NameNotFoundException
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var vAbs = 0.0
    for (i in 0 until v.size) {
        vAbs += v[i] * v[i]
    }
    return sqrt(vAbs)
}


/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    val listAvg = list.sum() / list.size
    if (list.isEmpty())
        return 0.0
    else
        return listAvg
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) {
        val mean = mean(list)
        for (el in 0 until list.size) {
            list[el] -= mean
        }
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (el in a.indices)
        c += a[el] * b[el]
    return c
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var c = 0
    var y = 1
    for (el in 0 until p.size) {
        c += p[el] * y
        y *= x
    }
    return c
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (el in 1 until list.size) {
        list[el] += list[el - 1]
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var x = n
    val list = mutableListOf<Int>()
    var i = 2
    while (x > 1) {
        if (x % i == 0) {
            x /= i
            list.add(i)
        } else
            i++
    }
    return list
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String =
    factorize(n).joinToString(separator = "*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var x = n
    val result = mutableListOf<Int>()
    while (x > 0) {
        result.add(x % base)
        x /= base
    }
    return result.reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val alp = ('a'..'z').toMutableList()
    val x = convert(n, base)
    var result = ""
    for (i in x.indices) {
        val check = x[i]
        if (check in 0..9)
            result += check
        else
            result += alp[check - 10]
    }
    return result
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    val ch = digits.reversed().toMutableList()
    var result = 0
    for (i in ch.indices) {
        result += ch[i] * base.toDouble().pow(i).toInt()
    }
    return result
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var k = n
    val romanNum = listOf("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
    val arabianNum = listOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
    return buildString {
        for (i in arabianNum.size - 1 downTo 0) {
            while (k >= arabianNum[i]) {
                k -= arabianNum[i]
                append(romanNum[i])
            }
        }
    }
}


/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val result = StringBuilder()
    result.append(
        when (n / 100000) {
            9 -> " девятьсот"
            8 -> " восемьсот"
            7 -> " семьсот"
            6 -> " шестьсот"
            5 -> " пятьсот"
            4 -> " четыреста"
            3 -> " триста"
            2 -> " двести"
            1 -> " сто"
            else -> ""
        }
    )
    when ((n / 1000) % 100) {
        19 -> result.append(" девятнадцать")
        18 -> result.append(" восемнадцать")
        17 -> result.append(" семнадцать")
        16 -> result.append(" шестнадцать")
        15 -> result.append(" пятнадцать")
        14 -> result.append(" четырнадцать")
        13 -> result.append(" тринадцать")
        12 -> result.append(" двенадцать")
        11 -> result.append(" одиннадцать")
        10 -> result.append(" десять")
        else -> {
            result.append(
                when ((n / 10000) % 10) {
                    9 -> " девяносто"
                    8 -> " восемьдесят"
                    7 -> " семьдесят"
                    6 -> " шестьдесят"
                    5 -> " пятьдесят"
                    4 -> " сорок"
                    3 -> " тридцать"
                    2 -> " двадцать"
                    else -> ""
                }
            )
            result.append(
                when ((n / 1000) % 10) {
                    9 -> " девять"
                    8 -> " восемь"
                    7 -> " семь"
                    6 -> " шесть"
                    5 -> " пять"
                    4 -> " четыре"
                    3 -> " три"
                    2 -> " две"
                    1 -> " одна"
                    else -> ""
                }
            )
        }
    }
    if (((n / 10000) % 10 != 1) && (n / 1000) % 10 == 1) {
        result.append(" тысяча")
    } else if ((((n / 1000) % 10 == 2) || ((n / 1000) % 10 == 3) || ((n / 1000) % 10 == 4)) && (n / 10000) % 10 != 1) {
        result.append(" тысячи")
    } else if (n / 1000 != 0) {
        result.append(" тысяч")
    }
    result.append(
        when ((n / 100) % 10) {
            9 -> " девятьсот"
            8 -> " восемьсот"
            7 -> " семьсот"
            6 -> " шестьсот"
            5 -> " пятьсот"
            4 -> " четыреста"
            3 -> " триста"
            2 -> " двести"
            1 -> " сто"
            else -> ""
        }
    )
    when (n % 100) {
        19 -> result.append(" девятнадцать")
        18 -> result.append(" восемнадцать")
        17 -> result.append(" семнадцать")
        16 -> result.append(" шестнадцать")
        15 -> result.append(" пятнадцать")
        14 -> result.append(" четырнадцать")
        13 -> result.append(" тринадцать")
        12 -> result.append(" двенадцать")
        11 -> result.append(" одиннадцать")
        10 -> result.append(" десять")
        else -> {
            result.append(
                when ((n / 10) % 10) {
                    9 -> " девяносто"
                    8 -> " восемьдесят"
                    7 -> " семьдесят"
                    6 -> " шестьдесят"
                    5 -> " пятьдесят"
                    4 -> " сорок"
                    3 -> " тридцать"
                    2 -> " двадцать"
                    else -> ""
                }
            )
            result.append(
                when (n % 10) {
                    9 -> " девять"
                    8 -> " восемь"
                    7 -> " семь"
                    6 -> " шесть"
                    5 -> " пять"
                    4 -> " четыре"
                    3 -> " три"
                    2 -> " два"
                    1 -> " один"
                    else -> ""
                }
            )
        }
    }
    return result.toString().trim()
}









