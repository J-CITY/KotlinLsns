package Patterns.AbstractFabric

//интерфейсы генерируемых объектов
interface Button {
    fun paint()
}
interface Checkbox {
    fun paint()
}

//реализация интерфейсов
class WindowsButton : Button {
    override fun paint() {
        println("You have created WindowsButton.")
    }
}
class WindowsCheckbox : Checkbox {
    override fun paint() {
        println("You have created WindowsCheckbox.")
    }
}

//интерфейс фабрики
interface GUIFactory {
    fun createButton(): Button
    fun createCheckbox(): Checkbox
}
//сама фарбика
class WindowsFactory : GUIFactory {

    override fun createButton(): Button {
        return WindowsButton()
    }

    override fun createCheckbox(): Checkbox {
        return WindowsCheckbox()
    }
}

class Singltone {
    companion object {
        var instance = Singltone()
    }
}

fun main() {
    val wf = WindowsFactory()
    wf.createButton().paint()


    Singltone.instance
}