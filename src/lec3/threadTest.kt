import kotlinx.coroutines.*
import java.lang.Thread.sleep
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread

//Разница в том, что launch{} ничего не возвращает, а async{} возвращает экземпляр Deferred, в котором имеется функция await(), которая возвращает результат корутины


suspend fun main(args: Array<String>) {
    println("Kotlin Start")
    GlobalScope.launch {
        delay(2000)
        println("Kotlin Inside")
    }
    delay(3000)
    println("Kotlin End")
}

//fun main(args: Array<String>) {
//    delay(2000)
//}

//fun main(args: Array<String>) {
//    runBlocking {
//        delay(2000)
//    }
//}

suspend fun doWorkFor1Seconds(): String {
    delay(1000)
    return "doWorkFor1Seconds"
}

suspend fun doWorkFor2Seconds(): String {
    delay(2000)
    return "doWorkFor2Seconds"
}

// Serial execution
private fun doWorksInSeries() {
    GlobalScope.launch {
        val one = doWorkFor1Seconds()
        val two = doWorkFor2Seconds()
        println("Kotlin One : " + one)
        println("Kotlin Two : " + two)
    }
}

private fun doWorksInParallel() {
    val one = GlobalScope.async {
        doWorkFor1Seconds()
    }
    val two = GlobalScope.async {
        doWorkFor2Seconds()
    }
    GlobalScope.launch {
        val combined = one.await() + "_" + two.await()
        println("Kotlin Combined : " + combined)
    }
}

fun simpleThreadnew() {
    val c = AtomicLong()

    for (i in 1..100L)
        thread(start = true) {
            c.addAndGet(i)
            println(c.get())
        }

    println("Res " + c.get())
}

fun testSeq() {
    val sequence = sequence {
        val start = 0
        // yielding a single value
        yield(start)
        // yielding an iterable
        yieldAll(1..5 step 2)
        // yielding an infinite sequence
        yieldAll(generateSequence(8) { it * 3 })
    }

    println(sequence.take(7).toList())
}
