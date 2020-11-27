package GUISamples

import javafx.animation.FadeTransition
import javafx.application.Application
import javafx.concurrent.Task
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.ImagePattern
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.stage.Screen
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.util.Duration
import java.io.File

class Notify: Application() {

    var content = HBox()

    enum class Position {
        RIGHT_BOTTOM,
        RIGHT_TOP,
        LEFT_BOTTOM,
        LEFT_TOP
    }

    enum class Border {
        SQUARE,
        CIRCLE
    }

    open class Car {
        public var speed = 10.0
        private var gas = 5

        fun addGas(_g: Int) {
            gas += _g
        }
        fun addGas(_g: Float) {

        }
    }

    class Nissan: Car() {

    }

    fun myfun() {
        var car = Nissan()

    }

    class Config {
        var pos = Position.RIGHT_TOP

        var title = "TITLE"
        var msg = "MESSAGE"
        var appName = "app_name"

        var iconBorder = Border.CIRCLE
        var iconPath = "https://softboxmarket.com/images/thumbnails/618/540/detailed/3/official-bts-wings-2nd-album-cd-poster-po_00.jpg"

        var textColor = "#FFFFFF"

        var bgColor = "#000000"
        var bgOpacity = 0.9

        var waitTime = 3000
    }

    var defWwidth = 300.0
    var defHeight = 140.0

    var config = Config()

    var popup = Stage()

    override fun start(primaryStage: Stage?) {
        build()

        var screenRect = Screen.getPrimary().bounds

        var shift = 10.0

        when (config.pos) {
            Position.LEFT_BOTTOM -> {
                popup.x = shift
                popup.y = screenRect.height - defHeight - shift
            }
            Position.LEFT_TOP -> {
                popup.x = shift
                popup.y = shift
            }
            Position.RIGHT_BOTTOM -> {
                popup.x = screenRect.width - defWwidth - shift
                popup.y = screenRect.height - defHeight - shift
            }
            Position.RIGHT_TOP -> {
                popup.x = screenRect.width - defWwidth - shift
                popup.y = shift
            }
        }

        val task = object: Task<Void>() {
            @Throws(InterruptedException::class)
            override fun call(): Void? {
                Thread.sleep(config.waitTime.toLong())
                cloaseAnim()
                return null
            }
        }
        Thread(task).start()

        popup.addEventFilter(MouseEvent.MOUSE_PRESSED, EventHandler { me: MouseEvent ->
            cloaseAnim()
        })

        popup.scene = Scene(content)
        popup.initOwner(primaryStage)
        popup.initStyle(StageStyle.TRANSPARENT)
        popup.opacity = config.bgOpacity
        popup.show()
        openAnim()
    }

    fun openAnim() {
        val ft = FadeTransition(Duration.millis(1500.0), content)
        ft.fromValue = 0.0
        ft.toValue = config.bgOpacity
        ft.cycleCount = 1
        ft.play()
    }

    fun cloaseAnim() {
        val ft = FadeTransition(Duration.millis(1500.0), content)
        ft.fromValue = config.bgOpacity
        ft.toValue = 0.0
        ft.cycleCount = 1
        ft.setOnFinished {
            println("close")
            popup.close()
        }
        ft.play()
    }

    fun build() {
        content.setPrefSize(defWwidth, defHeight)
        content.setPadding(Insets(5.0, 5.0, 5.0, 5.0))
        content.spacing = 10.0
        content.style = "-fx-background-color:" + config.bgColor

        var path = config.iconPath
        if (!config.iconPath.isEmpty()) {
            if (config.iconPath.substring(0, 4) != "http") {
                path = File(config.iconPath).toURI().toURL().toString();
            }

            var icoBorder = if (config.iconBorder == Border.CIRCLE) {
                Circle(defHeight / 2 , defHeight /2 , defHeight / 2)
            } else {
                Rectangle(defHeight / 2 , defHeight /2, defHeight, defHeight)
            }
            icoBorder.setFill(ImagePattern(Image(path)))
            content.children.add(icoBorder)
        }

        var msgLayout = VBox()

        var title = Label(config.title)
        title.font = Font(24.0)
        title.style = "-fx-font-weight: bold; -fx-text-fill:"+config.textColor

        var message = Label(config.msg)
        message.font = Font(20.0)
        message.style = "-fx-text-fill:"+config.textColor

        var app = Label(config.appName)
        app.font = Font(16.0)
        app.style = "-fx-text-fill:"+config.textColor

        msgLayout.children.addAll(title, message, app)
        content.children.add(msgLayout)

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Notify::class.java)
        }
    }

}