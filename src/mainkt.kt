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

fun main(args: Array<String>){
    println("Hello world!")
    Logger.out("test")
}

