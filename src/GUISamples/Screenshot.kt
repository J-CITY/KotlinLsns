package GUISamples

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.Slider
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler
import javafx.scene.canvas.GraphicsContext
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import java.io.IOException
import javafx.embed.swing.SwingFXUtils
import javax.imageio.ImageIO
import java.io.File
import javafx.scene.SnapshotParameters
import javafx.scene.image.WritableImage
import java.awt.Dimension
import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit
import java.awt.image.BufferedImage


class Screenshot : Application() {

    var brushSize = 18.0
    override fun start(primaryStage: Stage) {
        var takeScreenshot = Button("Screenshot")

        takeScreenshot.onAction = EventHandler {
            println("Take screenshot")
            //saveAsPng()
            savePart()
        }

        val root = VBox()

        root.children.add(takeScreenshot)

        //создаем окно
        val scene = Scene(root, 500.0, 450.0)

        primaryStage.title = "Paint"
        primaryStage.scene = scene
        primaryStage.show()
    }

    fun saveAsPng() {
        try {
            var robot = Robot()
            var fileName = "C:\\Users\\333da\\Desktop\\Lsns\\KotlinLsns\\screen.jpg"

            var screenRect = Rectangle(Toolkit.getDefaultToolkit().getScreenSize())
            var screenFullImage = robot.createScreenCapture(screenRect)
            ImageIO.write(screenFullImage, "jpg", File(fileName))
            print("Done")
        } catch (ex: IOException) {
            print(ex)
        }
    }

    fun savePart() {
        try {
            var robot = Robot()
            var fileName = "C:\\Users\\333da\\Desktop\\Lsns\\KotlinLsns\\screen.jpg"

            var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            var captureRect = Rectangle(0, 0, screenSize.width / 2, screenSize.height / 2);
            var screenFullImage = robot.createScreenCapture(captureRect);
            ImageIO.write(screenFullImage, "jpg", File(fileName));

            print("Done")
        } catch (ex: IOException) {
            print(ex)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(Screenshot::class.java)
        }
    }
}