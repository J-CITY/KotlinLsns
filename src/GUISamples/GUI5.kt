package GUISamples

import javafx.application.Application
import javafx.scene.Group
import javafx.scene.PerspectiveCamera
import javafx.scene.Scene
import javafx.scene.shape.Box
import javafx.scene.shape.DrawMode
import javafx.stage.Stage

class GUI5 : Application() {
    override fun start(stage: Stage) {
        //Drawing a Box
        val box2 = Box()
        //Setting the properties of the Box
        box2.width = 100.0
        box2.height = 100.0
        box2.depth = 100.0
        //Setting the position of the box
        box2.translateX = 450.0 //450
        box2.translateY = 150.0//150
        box2.translateZ = 300.0

        //Setting the drawing mode of the box
        box2.drawMode = DrawMode.FILL

        Thread(Runnable {
            while (true) {
                if (box2 != null) {
                    box2.rotate = (box2.rotate + 5) % 360
                    println(box2.rotate)
                }
                try {
                    Thread.sleep(20)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }).start()

        //Creating a Group object
        val root = Group(box2)
        //Creating a scene object
        val scene = Scene(root, 600.0, 300.0)
        //Setting camera
        val camera = PerspectiveCamera(false)
        camera.translateX = 0.0
        camera.translateY = 0.0
        camera.translateZ = 0.0
        scene.camera = camera

        //Setting title to the Stage
        stage.title = "Drawing a Box"
        //Adding scene to the stage
        stage.scene = scene
        //Displaying the contents of the stage
        stage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(GUI5::class.java)
        }
    }
}