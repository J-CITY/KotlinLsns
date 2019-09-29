package helloworld

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage

class HelloWorld : Application() {
    override fun start(primaryStage: Stage) {
        //создаем кнопку
        val btn = Button()
        btn.text = "Say 'Hello World'"
        //ставим действие на кнопку
        btn.onAction = EventHandler {
            println("Hello World!")
        }

        //создаём рут объект
        val root = StackPane()
        root.children.add(btn)

        //создаем окно
        val scene = Scene(root, 300.0, 250.0)

        primaryStage.title = "Hello World!"
        primaryStage.scene = scene
                primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(HelloWorld::class.java)
        }
    }
}
