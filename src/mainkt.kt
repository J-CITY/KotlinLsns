
//https://metanit.com/kotlin/tutorial/

// Hello world
/*
fun main(args: Array<String>){
    println("Hello world!")
}
*/

// vars
/*
fun main(args: Array<String>){
    var age: Int = 22
    age = 24
    age = 42

    val name: String
    name = "Roberto"

    println(age)
    println(name)
}
*/

/*
Types
INTEGERS
Byte: [-128, 127]
Short: [-32768, 32767]
Int: [-2147483648, 2147483647]
Long: [–9 223 372 036 854 775 808, 9 223 372 036 854 775 807]

val age: Int = 0x0A1    // 161
val weight: Float = 68.71F
val weight: Double = 68.71
val a: Int = 0b0101    // 5

FLOAT
Float: [-3.4*1038, 3.4*1038]
Double: [±5.0*10-324, ±1.7*10308]

CHARS
Char
val a = 'A'
String
val name = "OLOLOLO"

val text: String = """Water, Fire, Earth, Air.
Once upon a time, four peoples lived in peace.
But everything changed when the people of Fire unleashed a war.
Only Avatar, the lord of all four elements, could stop the invaders"""

val firstName = "Lucky"
val lastName = 13
val welcome = "Hello, $firstName $lastName"

OTHER
Any - base type.
var name: Any = "Tom Smith"
name = 6758

 */

/*
Operators

+-*%/
++
--
-=
*=
%=
/=
+=

//prefix
var x = 5
val y = --x
println(x)      // x = 4
println(y)      // y = 4

//postfix
var x = 5
val y = x--
println(x)      // x = 4
println(y)      // y = 5

Binary
shl
shr
ushl
ushr
and
or
xor
inv
val b = 11  // 1011
val c = b.inv()


Compare
>
<
>=
<=
==
!=

Logic
and
or
xor
!, .not()
in - a in 1..10

//IF ELSE

val a = 10
if(a == 10) {
    println("a равно 10")
}
else{
    println("a НЕ равно 10")
}

val c = if (a > b) a else b

val c = if (a > b){
    println("a = $a")
    a
} else {
    println("b = $b")
    b
}


//SWICH
val a = 10
when(a){
    in 1..3 -> print(1)
    in 3..5 -> print(0)
    10 -> println("a = 10")
    20 -> println("a = 20")
    else -> println("неопределенное значение")
}

val rate = when(sum){
    in 100..999 -> 10
    in 1000..9999 -> 15
    else -> 20
}

//LOOPS
for(n in 1..9){
    print("${n * n} \t")
}

for (i in 6 downTo 0 step 2) {
    println(i)
}

for (i in array.indices) {
    println(array[i])
}

var i = 10
while(i > 0){
    println(i*i)
    i--;
}

var i = -1
do{
    println(i*i)
    i--;
}
while(i > 0)

for(n in 1..8){
    if(n == 5) continue; //break;
    println(n * n)
}

//RANGE
var range1 =  1..5      // 1 2 3 4 5
var range2 =  5 downTo 1    // 5 4 3 2 1

var range =  "a".."d"
var range1 = 1 until 9          // 1 2 3 4 5 6 7 8
var range2 = 1 until 9 step 2   // 1 3 5 7

var isInRange = 5 in range
var isInRange = 5 !in range
for(c in range) print(c)

//Array
val numbers: Array<Int> = arrayOf(1, 2, 3, 4, 5)
val n = numbers[1]  // получаем второй элемент  n=2
numbers[2] = 7      // переустанавливаем третий элемент

val numbers = Array(3, {5}) // [5, 5, 5]

val numbers: IntArray = intArrayOf(1, 2, 3, 4, 5)
val doubles: DoubleArray = doubleArrayOf(2.4, 4.5, 1.2)

val table: Array<Array<Int>> = Array(3, { Array(5, {0}) })

val table = Array(3, { Array(3, {0}) })
table[0] = arrayOf(1, 2, 3)
table[1] = arrayOf(4, 5, 6)
table[2] = arrayOf(7, 8, 9)

fun displayUser(name: String, age: Int = 18, position: String="unemployed") : Bool {
    println("Name: $name   Age: $age  Position: $position")
    return true
}
displayUser(name="Tom", position="Manager", age=28)

fun printStrings(vararg strings: String){
    for(str in strings)
        println(str)
}


/передать параметр в качестве значения элементы из массива:
fun printUserGroup(group: String, vararg users: String, count:Int){
    println("Count: $count")
    for(user in users)
        println(user)
}
fun main(args: Array<String>) {

    val users = arrayOf("Tom", "Bob", "Alice")
    printUserGroup("MO-011", *users, count=3)
}

fun double(x: Int) : Int = x * x
fun double(x: Int) = x * x

fun isFirstGreater(base1: Double, height1: Double, base2: Double, height2: Double): Boolean{

    fun square(base: Double, height: Double) = base * height / 2

    return square(base1, height1) > square(base2, height2)
}

//override
fun add(a: Int, b: Int) : Int{
    return a + b
}
fun add(a: Double, b: Double) : Double{
    return a + b
}

//lambda
val hello = {println("hello")}
val printer = {message: String -> println(message)}
val sum = {x:Int, y:Int -> println(x + y)}

Выражение, стоящее после стрелки, определяет результат лямбда-выражения.
И этот результат мы можем присвоить, например, переменной.

Если лямбда-выражение формально не возвращает никакого результата, то фактически,
как и в функциях, возвращается значение типа Unit:

val sum = {x:Int, y:Int -> x + y}


//Функции высокого порядка
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

//Анонимные функции


fun(x: Int, y: Int): Int = x + y

fun(x: Int, y: Int): Int{
    return x + y
}

fun main(args: Array<String>) {

    operation(9,5, fun(x: Int, y: Int): Int { return x + y })   // 14
    operation(9,5, fun(x: Int, y: Int): Int = x - y)            // 4
}
fun operation(x: Int, y: Int, op: (Int, Int) ->Int){

    val result = op(x, y)
    println(result)
}

//Функции расширения
//fun тип.имя_функции(параметры) : возвращаемый_тип{
//    тело функции
//}

fun String.wordCount(c: Char) : Int{
    var count = 0
    for(n in 0..this.count()-1){
        if(this[n] == c) count++
    }
    return count
}
fun Int.square(): Int{
    return this * this
}

//Class

class Person { }

В Kotlin классы могут содержать ряд компонентов:

конструкторы и инициализаторы

функции

свойства

вложенные классы

объявления объектов

//class Person(_name: String){
class Person constructor(_name: String){
    val name: String
    init{
        name = _name
    }

    constructor(_name: String, _age: Int) : this(_name){
        age = _age
    }

    //Свойства
    var name: String = "Tom"
    var age: Int = 18
    get(){
        return  field
    }

    val info: String
    get() = "Name: $name  Age: $age"
    set(value){
        field = value
    }

    fun sayHello(){
        println("Hello")
    }
}


//Packege import
package name

import name.*
import naem.smth

//Модификаторы видимости


private
protected
public
internal  - в покете


class Account{
    class Transaction{

    }
}

//Чтобы вложенный класс мог иметь доступ к свойствам и функциям внешнего класса, необходимо определить вложенный класс с ключевым словом inner.
class Account{

    private var sum: Int = 3450
    fun display(){
        println("sum = $sum")
    }

    inner class Transaction{
        fun pay(s: Int){
            sum -= s
            display()
        }
    }
}


class A{
    private val n: Int = 1
    inner class B{
        private val n: Int = 1
        fun action(){
            println(n)          // n из класса B
            println(this.n)     // n из класса B
            println(this@B.n)   // n из класса B
            println(this@A.n)   // n из класса A
        }
    }
}

//Интерфейсы:

interface Movable{
    fun move()      // определение функции без реализации
    fun stop(){     // определение функции с реализацией по умолчанию
        println("Остановка")
    }
}

class Car : Movable{

    override fun move(){
        println("Машина едет")
    }
}
class Aircraft : Movable{
    override fun move(){
        println("Самолет летит")
    }
    override fun stop(){
        println("Приземление")
    }
}

//open даёт возможность наследоваться
open class Person(val name: String) {
    open fun display(){
        println("Name: $name")
    }
}
class Employee: Person{

    var company: String="undefined"

    constructor(name: String, comp:String) : super(name){
        company = comp
    }

    override fun display() {
        println("Name: $name    Company: $company")
    }
}


val liza: Person = Employee("Liza", "Apple")


open class Person(val name: String){

    open val fullInfo: String
        get() = "Name: $name"
}
open class Employee(val company: String, name: String): Person(name){

    override val fullInfo: String
        get() = "Name: $name  Company: $company"
}

//Обращение к реализации из базового класса
open class Person(val name: String){

    open val fullInfo: String
        get() = "Name: $name"

    open fun display(){
        println("Name: $name")
    }
}
open class Employee(val company: String, name: String): Person(name){

    override val fullInfo: String
        get() = "${super.fullInfo} Company: $company"

    final override fun display() {
        super.display()
        println("Company: $company")
    }
}
////////
open class Video {
    open fun play() { println("Play video") }
}

interface AudioPlayable {
    fun play() { println("Play audio") }
}

class MediaPlayer() : Video(), AudioPlayable {
    // Функцию play обязательно надо переопределить
    override fun play() {
        super<Video>.play()         // вызываем Video.play()
        super<AudioPlayable>.play() // вызываем AudioPlayable.play()
    }
}

//Абстрактные классы и методы
//мы не можем создать объект подобного класса.

abstract class Human(val name: String)

val kate: Human     // норм, просто определение переменной
val alice: Human = Human("Alice")   // ! ошибка, создать объект нельзя

abstract class Human(val name: String){

    fun hello(){
        println("My name is $name")
    }
}
class Person(name: String): Human(name)

//абстрактные методы можно определить только в абстрактных классах:
abstract class Human(val name: String){

    abstract fun hello()
}
class Person(name: String): Human(name){

    override fun hello(){
        println("My name is $name")
    }
}

//Data-классы
//При компиляции такого класса компилятор автоматически добавляет в класс функции:
//
//equals(): сравнивает два объекта на равенство
//
//hashCode(): возвращает хеш-код объекта
//
//toString(): возвращает строковое представление объекта
//
//copy(): копирует данные объекта в другой объект
fun main(args: Array<String>) {

    val alice: Person = Person("Alice", 24)
    println(alice.toString())
}

data class Person(val name: String, val age: Int){
    override fun toString(): String {
        return "Name: $name  Age: $age"
    }
}

//ENUMS
enum class Day{
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

enum class Day(val value: Int){
    MONDAY(1), TUESDAY(2), WEDNESDAY(3),
    THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(100500)
}

enum class Day(val value: Int){
    MONDAY(1), TUESDAY(2), WEDNESDAY(3),
    THURSDAY(4), FRIDAY(5), SATURDAY(6),
    SUNDAY(7);
    fun getDuration(day: Day): Int{
        return value - day.value;
    }
}
//Все перечисления обладают двумя встроенными свойствами:
//
//name: возвращает название константы в виде строки
//
//ordinal: возвращает название порядковый номер константы

//Анонимные классы и реализация интерфейсов
interface Printable{
    fun printName()
}
enum class DayTime: Printable{
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

fun main(args: Array<String>) {

    DayTime.DAY.printName()     // День
    DayTime.NIGHT.printName()   // Ночь
}

//Инфиксная нотация
//Инфиксная функция формально принимает два параметра.
//Первый параметр представляет объект, который вызывает функцию.
//А второй параметр - данные, которые непосредственно будут передаваться
//функции при ее вызове.
//
//Инфиксная функция определяется только внутри класса. Например, пусть
//у нас есть класс банковского счета, в котором определена функция добавления
//средств на счет:

class Account(_sum: Int) {
    var sum = _sum

    infix fun put(amount: Int): Unit{
        this.sum = this.sum + amount
    }
}

var acc = Account(340)
acc.put(300)
println(acc.sum)    // 640
acc put 300
println(acc.sum)    // 940


val n = null

val n : Int = null //ERR
val d : Int? = null

var age : Int? = null
age = 34              // Int? допускает null и числа
var name : String? = null
name = "Tom"        // String? допускает null и строки


var name : String?  = "Tom"
val firstName: String = name    // ! Ошибка

var name : String?  = "Tom"
val firstName: String = name ?: "Undefined" // если name = null, то присваивается "Undefined"

var age: Int? = 23
val myAge: Int = age ?:0    // если age равно null, то присваивается число 0

val name : String?  = "Tom"
val length: Int? = name?.length


val name : String?  = "Tom"
val length: Int = name?.length ?:0

val name : String?  = "Tom"
// Если операнд равен null, то генерируется исключение kotlin.KotlinNullPointerException.
val id: String = name!!
println(id)

val name : String?  = null
val length :Int = name!!.length


*/

//fun main(args: Array<String>) {
//}


