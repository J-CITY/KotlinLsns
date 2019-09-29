
//Шаблоны
class Complex<T: Number>(_real: T, _im: T) {
    val real: T = _real
    var im: T = _im

    fun print() {
        print(real.toString() + " + i" + "("+ im + ")")
    }

    operator fun plus(other: Complex<T>): Complex<T> {
        return Complex<T>(real+other.real, im+other.im)
    }
}

private operator fun <T> Number.plus(real: T): T {
    return real
}


fun main() {
    var num1 = Complex<Int>(1, 0)
    var num2 = Complex<Int>(5, 1)

    var num3 = num1 + num2
    num3.print()
}