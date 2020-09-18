interface Prntable{
    fun printName()
}

enum class DayTime: Prntable{
    DAY{
        override fun printName(){
            println("День")
        }
    },
    NIGHT{
        override fun printName(){
            println("Ночь")
        }
    }
}

interface Human {
    var type: Int
    fun live()
    fun die() { //дефолтная реализация
        println("die")
    }
}

interface Human2 {
    fun die2(someNum: Int = 0) { //дефолтная реализация
        println("die" + someNum)
    }
}

//Главное отличие интерфейсов от абстрактных классов заключается в
// невозможности хранения переменных экземпляров.

//Абстрактные классы - это классы, определенные с модификатором abstract.
//Отличительной особенностью абстрактных классов является то, что мы не
//можем создать объект подобного класса.
abstract class Mammalia(open var name: String){
    fun hello(){
        println("I am $name")
    }
    var a: Int = 5
    abstract fun hello2()
}


//open говорит, что можно наследоваться
open class Person(_name: String): Mammalia(_name), Human, Human2 {
    override var type: Int = 4

    override var name: String = "Tom"

    internal val isMale = false // переменная видна в пределах модуля

    public var age: Int = 18
        public set(value) {
            if((value>0) and (value <110))
                field = value
        }
        public get() {
            return field
        }

    init{
        name = _name
    }

    //Вторичный конструктор, мы обязаны вызвать первичный конструктор
    constructor(_name: String, _age: Int) : this(_name){
        age = _age
    }

    fun sayHello(){
        println("Hello")
    }

    fun getInfo() : String{
        return "Name: $name  Age: $age"
    }

    override fun live() {
        print("live")
    }

    override fun hello2() {
        print("hello2")
    }
}

class Employee: Person{
    var company: String="undefined"

    //super - вызов конструктора родителя
    constructor(name: String, comp:String) : super(name) {
        company = comp
    }
}

fun nullTest() {
    var n = null

    val d : Int? = null

    var name : String?  = "Tom"
    var firstName: String = name ?: ""
    //if(name!=null){
    //    firstName = name
    //}

    //Если операнд равен null, то генерируется исключение kotlin.KotlinNullPointerException
    val id: String = name!!
    println(id)

    val length :Int = name!!.length
}

fun testExp() {
    try{
        val x : Int = 0
        val z : Int = 0 / x
        println("z = $z")
    }
    catch(e: Exception){
        println("Exception")
        println(e.message)
    }
    finally{
        println("Program has been finished")
    }
    ///////////////////
    /*try {
        val nums = arrayOf(1, 2, 3, 4)
        println(nums[6])
    }
    catch(e:ArrayIndexOutOfBoundsException){
        println("Out of bound of array")
    }
    catch (e: Exception){
        println(e.message)
    }
    */
    ////////////////////
    /*
    fun factorial(n: Int): Int {
        if(n < 1) throw  Exception("Input number must be more than zero")
        var result = 1
        for(i in 1..n)
            result *= i
        return result
    }
    val a: Int? = try { factorial(5) } catch (e: Exception) { null }
    */

}

class Account(_sum: Int) {
    var sum = _sum

    infix fun put(amount: Int): Unit{
        this.sum = this.sum + amount
    }
}

fun infix() {
    var acc = Account(340)
    acc.put(300)
    println(acc.sum)
    acc put 300
    println(acc.sum)
}

fun main(a: Int) {
    var p = Person("Bob")
    print(p)
    p.die2(someNum = 2)
    return
    //nullTest()
    //testExp()
    //infix()
}