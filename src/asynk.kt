
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread
import kotlinx.coroutines.launch

//org.jetbrains.kotlinx:kotlinx-coroutines-core:0.27.0
//suspend говорит, что функция может прерываться

suspend fun workload(n: Int): Int {
    delay(1000)
    println("worker")
    return n
}

suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        42
    }
    val two = async<Int> {
        32
    }
    one.await() + two.await()
}

suspend fun failedConcurrentSum2(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE) // Emulates very long computation
            42
        } finally {
            println("First child was cancelled")
        }
    }
    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }
    one.await() + two.await()
}

fun simpleThread() {
    val c = AtomicLong()

    for (i in 1..100L)
        thread(start = true) {
            c.addAndGet(i)
            println(c.get())
        }

    println("Res " + c.get())
}


fun main() {
    //simpleThread()
    //workload() //error




    /*var job = GlobalScope.launch{
        delay(1000)
        println("Hello from coroutine")
    }
    job.start()*/


    println("main")
   /*runBlocking<Unit> {
        delay(2000)
        try {
            failedConcurrentSum()
        } catch(e: ArithmeticException) {
            println("Computation failed with ArithmeticException")

        }
    }
    */
    //yield
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


//launch Запускает новую сопрограмму без блокировки текущего потока и возвращает ссылку на сопрограмму как задание.
//async Создает сопрограмму и возвращает ее будущий результат как реализацию Deferred.
//runBlocking Запускает новую сопрограмму и прерывает текущий поток до его завершения.
