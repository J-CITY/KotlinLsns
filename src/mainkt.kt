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


fun selectAction(key: Int): (Int, Int) -> Int{
    // определение возвращаемого результата


    when(key){
        1 -> return {x:Int, y: Int -> x + y}
        2 -> return {x:Int, y: Int -> x - y}
        3 -> return {x:Int, y: Int -> x * y}
        else -> return  {x:Int, y: Int -> 0}
    }
}

// Hello world

fun main(args: Array<String>){
    println("Hello world!")
    Logger.out("test")
}

