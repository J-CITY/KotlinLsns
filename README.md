# KotlinLsns
 KtlinLessons

## val и var

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








