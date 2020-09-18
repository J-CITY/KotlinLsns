//https://metanit.com/kotlin/tutorial/


inline fun <T> For(it : Iterator<T>, cb : (T) -> Unit) {
    while (it.hasNext()) cb(it.next())
}

fun printStrings(vararg strings: String){
    for(str in strings) {
        println(str)
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

    //anonim fun
    var anonim = fun(x: Int, y: Int): Int{
        return x + y
    }

}

fun main(args: Array<String>){
    println("Hello world!")
}


























