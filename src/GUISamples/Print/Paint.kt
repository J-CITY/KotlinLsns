package GUISamples.Print

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.stage.Stage
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color


class Paint : Application() {

    var brushSize = 18.0
    override fun start(primaryStage: Stage) {
        var canvas = Canvas()
        canvas.height=450.0
        canvas.width=500.0

        var g = canvas.graphicsContext2D

        canvas.onMouseDragged = EventHandler<MouseEvent>  { e ->
            val size = brushSize
            val x = e.x - size / 2
            val y = e.y - size / 2

            if (e.button == MouseButton.SECONDARY) {
                g.clearRect(x, y, size, size)
            } else {
                g.fill = Color.RED
                g.fillRect(x, y, size, size)
            }
        }

        val root = VBox()

        root.children.add(canvas)

        //создаем окно
        val scene = Scene(root, 500.0, 450.0)

        primaryStage.title = "Paint"
        primaryStage.scene = scene
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(Paint::class.java)
        }
    }
}