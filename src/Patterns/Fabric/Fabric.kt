package Patterns.Fabric

interface Button {
    fun render()
    fun onClick()
}

class HtmlButton : Button {

    override fun render() {
        println("<button>Test Button</button>")
        onClick()
    }

    override fun onClick() {
        println("Click! Button says - 'Hello World!'")
    }
}
class ConsoleButton : Button {

    override fun render() {
        println("Test Button")
        onClick()
    }

    override fun onClick() {
        println("Click! Button console says - 'Hello World!'")
    }
}

abstract class Factory {
    fun renderWindow() {
        // ... остальной код диалога ...
        val okButton = createButton()
        okButton.render()
    }
    /**
     * Подклассы будут переопределять этот метод, чтобы создавать конкретные
     * объекты продуктов, разные для каждой фабрики.
     */
    abstract fun createButton(): Button
}

class HtmlFactory : Factory() {
    override fun createButton(): Button {
        return HtmlButton()
    }
}

fun main() {
    val htmlFactory = HtmlFactory()
    htmlFactory.createButton().render()
}
