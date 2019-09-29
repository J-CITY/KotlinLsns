package GUISamples


import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.effect.BlendMode
import javafx.scene.effect.BoxBlur
import javafx.scene.paint.Color
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.Stop
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.scene.shape.StrokeType
import javafx.stage.Stage
import javafx.util.Duration
import java.lang.Math.random

class GUI4 : Application() {

    override fun start(primaryStage: Stage) {
        /*
        Group является контейнером, это компонент, который не применяет Layout для своих подкомпонентов.
        Все подкомпоненты находятся в 0,0. Цель Group это сгруппировать компоненты в одной группе и
        выполнить определенную задачу с ними.
         */
        val root = Group()
        /*
        Создаем окно
         */
        val scene = Scene(root, 800.0, 600.0, Color.BLACK)
        primaryStage.scene = scene

        /*
        Созжаём группу для кругов
         */
        val circles = Group()
        for (i in 0..29) {
            val circle = Circle(150.0, Color.web("white", 0.05))
            circle.strokeType = StrokeType.OUTSIDE
            circle.stroke = Color.web("white", 0.16)
            circle.strokeWidth = 4.0
            circles.children.add(circle)
        }
        /*
        Создаём сам гардиент
         */
        val colors = Rectangle(
            scene.width, scene.height,
            LinearGradient(
                0.0,
                1.0,
                1.0,
                0.0,
                true, //растягиваем на все прострпнство
                CycleMethod.NO_CYCLE,
                *arrayOf(
                    Stop(0.0, Color.web("#f8bd55")),
                    Stop(0.14, Color.web("#c0fe56")),
                    Stop(0.28, Color.web("#5dfbc1")),
                    Stop(0.43, Color.web("#64c2f8")),
                    Stop(0.57, Color.web("#be4af7")),
                    Stop(0.71, Color.web("#ed5fc2")),
                    Stop(0.85, Color.web("#ef504c")),
                    Stop(1.0, Color.web("#f2660f"))
                )
            )
        )
        colors.widthProperty().bind(scene.widthProperty())
        colors.heightProperty().bind(scene.heightProperty())

        /*
        Добавляем созданные объекты в группу
         */
        val blendModeGroup = Group(
            Group(
                Rectangle(
                    scene.width, scene.height,
                    Color.BLACK
                ), circles
            ), colors
        )
        //Ставим гардиент как оверлей, тем самым получая интересный эввект
        colors.blendMode = BlendMode.OVERLAY
        //добавляем в рут
        root.children.add(blendModeGroup)
        circles.effect = BoxBlur(4.0, 4.0, 3)
        val timeline = Timeline()
        //добавляем анимацию
        for (circle in circles.children) {
            timeline.keyFrames.addAll(
                KeyFrame(
                    Duration.ZERO, // set start position at 0
                    KeyValue(circle.translateXProperty(), random() * 800),
                    KeyValue(circle.translateYProperty(), random() * 600)
                ),
                KeyFrame(
                    Duration(40000.0), // set end position at 40s
                    KeyValue(circle.translateXProperty(), random() * 800),
                    KeyValue(circle.translateYProperty(), random() * 600)
                )
            )
        }
        // play 40s of animation
        timeline.play()

        primaryStage.show()
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            launch(GUI4::class.java)
        }
    }
}