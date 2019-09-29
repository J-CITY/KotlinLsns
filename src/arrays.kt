

fun main() {
    var countries: Map<String, Int> = mapOf(
        "USA" to 300,
        "France" to 60,
        "Germany" to 81)
    println(countries["USA"])

    var map1: LinkedHashMap<Int, String> = linkedMapOf(1 to "1", 2 to "2") // не гарантирует порядок
    var map2: HashMap<Int, String> = hashMapOf(1 to "1", 2 to "2") //представляет комбинацию HashMap и связанного списка, создается функцией
    var map3: java.util.SortedMap<Int, String> = sortedMapOf(1 to "1", 2 to "2") //упорядоченная коллекция, где все объекты автоматически сортируются


    var numbers : List<Int> = listOf(1, 2, 3, 4, 5)
    println(numbers.get(1))      // 2
    println(numbers.indexOf(2))     // 1
    println(numbers.lastIndexOf(3)) // 2
    println(numbers.first())        // 1
    println(numbers.last())         // 5
    println(numbers.size)           //5
    println(numbers.contains(4))    // true
    println(numbers.elementAt(1))   // 2
    println(numbers.elementAtOrNull(9))  // null

    //изменяемый мписок
    var numbers2 : ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5)
    numbers2.add(12)
    numbers2.add(0, 23)
    numbers2.addAll(0, listOf(-3, -2, -1))
    numbers2.removeAt(0)
    numbers2.remove(5)
}