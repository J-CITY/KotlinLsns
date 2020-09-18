# KotlinLsns
Курс по дисциплине "Разработка оконных приложений", с использованием ЯП Kotlin и библиотеки JavaFX

## Краткис справочник по Kotlin

1. Основы
    + [var и val](#varval)

## Hello world

```kotlin
fun main(args: Array<String>){
    println("Hello Kotlin")
}
```

## Базовые типы данных

Byte, Short, Int, Long, Float, Double - всё довольно стандартно

Стоит отметить тип `any`

```kitlin
var name: Any = "42"
name = 42
```

`Any` - базовый тип данных

##### <a name="varval"></a> val и var 


```kotlin
val a: Int = 42;

var b = 0.0;

a = -42 //error
b = 42.0
```

val - неизменяемая переменная

var - изменяемая

## Условный оператор

```kotlin
if(a == b)
    println("a == b")
else
    println("a != b")
```

может работать как тернарный оператор

```
val c = if (a > b){
    a
} else {
    b
}
```

### Аналог case

```kotlin
val a = 10
when(a){
    10 -> println("a = 10")
    20 -> println("a = 20")
    else -> println("неопределенное значение")
}
```

так же может работать как аналог тернарного оператора + может быть не только с константами и ренджами

```kotlin
val rate = when(sum){
    in 100..999 -> 10
    in 1000..9999 -> 15
    else -> 20
}
```


## Циклы

```
for(n in 1..9){
    print("${n} \t")
}
```

for - может быть только через итераторы

В принципе можно спокойно написать свой For

```
inline fun <T> For(it : Iterator<T>, cb : (T) -> Unit) {
    while (it.hasNext()) cb(it.next())
}

fun main() {
    For(list.iterator()) { 
        println(it)
    }
}
```

`Синтаксис позволяет вынести определение лямбды за скобку.`

while - аналогичен c++

```
while(i > 0){
    println(i*i)
    i--;
}
```

## Последовательности

```kotlin
var range = 1..10
range =  "a".."z"
range =  10 downTo 0
range = 10 downTo 0 step 2

if (1 in range) {
    print("Yey")
}
```

## Массивы

```kotlin
val numbers: Array<Int> = arrayOf(1, 2, 3, 4, 5)

val numbers2 = Array(3, {5}) // [5, 5, 5]

//2D array
val table: Array<Array<Int>> = Array(3, { Array(5, {0}) })


val arr: Array<String> = arrayOf("1", "2", "3")
for(elem in arr){
    println(elem)
}
```

#Функции

```kotlin
fun funName(param1: type1, param2: type2, ...) : returnType {
    code
}
```

Аргументы по умолчанию

```kotlin
fun displayUser(name: String, age: Int = 18, position: String="unemployed"){
    println("Name: $name   Age: $age  Position: $position")
}

displayUser(age=21, name="Alice")
```

Переменное количество аргументов

`vararg` - ключевое слово, для передачи переменного количества параметров одного типа

```kotlin
fun sum(vararg numbers: Int){
    var result=0
    for(n in numbers)
        result += n
    println("Сумма чисел равна $result")
}
```

Если параметров много, то `vararg` - должен быть последним

(Это можно нарушить, если после vararg-параметра идут еще какие-нибудь параметры, то при вызове функции значения этим параметрам передаются через именованные аргументы)

Оператор * (оператор распаковки)

Помагает распаковать массив для передачи его как параметр vararg

```kotlin
fun printUserGroup(group: String, vararg users: String, count:Int){
    println("Count: $count")
    for(user in users)
        println(user)
}
fun main(args: Array<String>) {
 
    val users = arrayOf("Tom", "Bob", "Alice")
    printUserGroup("MO-011", *users, count=3)
}
```

Возвращение результата из функции

Возвращение значение из функции осуществляется с помощью оператора `return`

```kotlin
fun fun42() : Int{
    return 42
}
```

Забавный факт, если функция ничего не возвращает, то ее тип возвращаемого значения - `Uint`

Однострочные функции

Однострочные функции (single expression function) используют сокращенный синтаксис определения функции в виде одного выражения. Эта форма позволяет опустить возвращаемый тип и оператор return.

```kotlin
fun pow2(x: Int) : Int = x * x

```

Локальные функции

```kotlin
fun check(num: Int): Boolean{
    fun pow2(num: Int) = num*num
    return pow2(num) > 100
}
```

Перегрузка функций

```kotlin
fun add(a: Int, b: Int) : Int{
    return a + b
}
fun add(a: Double, b: Double) : Double{
    return a + b
}
```

Важно в `kotlin` при перегрузке не учитывает возвращаемый результат функции

Лямбды

Лямбда-выражения представляют небольшие кусочки кода, которые выполняют некоторые действия. Фактически лямбды преставляют сокращенную запись функций. При этом лямбды могут передаваться в качестве параметра в функции.

```kotlin
val printer = {message: String -> println(message)}
val summer = {a: Int, b: Int -> a+b}

val summerAndPrinter = {x:Int, y:Int ->
    val result = x + y
    println("$x + $y = $result")
    result
}
```

Анонимные функции

Анонимные функции выглядят как обычные за тем исключением, что они не имеют имени.

```kotlin
fun(x: Int): x+x

fun(x: Int, y: Int): Int{ 
    return x + y
}
```

Зачем нам и лямбды и анонимные функции??

```kotlin
fun loop(list: List<Element>): Boolean {
     list.forEach { element ->
          if (!element.process()) return false   // returns from loop()
     }
     return true
}


fun loop(list: List<Element>): Boolean {
     list.forEach { element ->
          if (!element.process()) return@forEach   // returns from forEach
     }
     return true
}


fun loop(list: List<Element>): Boolean {
     list.forEach(fun(element) {
          if (!element.process()) return false   // returns from forEach
     })
     return true
}
```

Для чего они нужны? Да чтобы их в функции передавать))

Функции высокого порядка

Функции высокого порядка (high order function) - это функции, которые либо принимают функцию в качестве параметра, либо возвращают функцию, либо и то, и другое.

```kotlin
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
```

Inline функции

Ключевое слово inline - сообщит компилятору, что мы хотим, чтобы код этой функции подставлялся в месте её вызова.

```kotlin
inline fun pow(i: Int)-> Int {
    return i*i
}
```



