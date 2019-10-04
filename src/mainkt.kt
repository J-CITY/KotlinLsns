import javafx.application.Application

//https://metanit.com/kotlin/tutorial/

interface Printable {
    fun printMe()
}

enum class LogType(val value: Int): Printable {
    INFO(0) {
        override fun printMe() {
            print("INFO: ")
        }
    }

}

class Logger {
    companion object {
        fun out(str: String, type: LogType = LogType.INFO) {
            type.printMe()
            println(str)
        }
    }
}


// Hello world

inline fun <T> For(it : Iterator<T>, cb : (T) -> Unit) {
    while (it.hasNext()) cb(it.next())
}

fun printStrings(vararg strings: String){
    for(str in strings) {
        println(str)
    }
}

fun sqrint(x: Int) = x * x

//функции высоких порядков
fun action (n1: Int, n2: Int, operation: (Int, Int)-> Int){
    val result = operation(n1, n2)
    println(result)
}

fun selectAction(key: Int): (Int, Int) -> Int{
    // определение возвращаемого результата
    when(key){
        1 -> return {x:Int, y: Int -> x + y}
        2 -> return {x:Int, y: Int -> x - y}
        3 -> return {x:Int, y: Int -> x * y}
        else -> return  {x:Int, y: Int -> 0}
    }
}

fun main2() {
    val list = listOf(1, 3, 4, 12)
    println("for");   for (it in list) println(it)
    println("FOR");   For(list.iterator()) { println(it) }

    val users = arrayOf("Tom", "Bob", "Alice")
    printStrings("Tom", "Bob", "Alice")
    printStrings(*users)

    //lambda
    val sum = {x:Int, y:Int ->
        val result = x + y
        println("$x + $y = $result")
        result //like return result
    }

    var anonim = fun(x: Int, y: Int): Int{
        return x + y
    }

}

fun main(args: Array<String>){
    println("Hello world!")
    Logger.out("test")
}


























