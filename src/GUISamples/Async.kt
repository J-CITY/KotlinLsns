package GUISamples

import javafx.application.Application
import javafx.concurrent.Task
import javafx.concurrent.WorkerStateEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ProgressIndicator
import javafx.scene.control.Spinner
import javafx.scene.layout.FlowPane
import javafx.stage.Stage
import java.util.*


class MyTask: Task<Int>() {
    override fun call(): Int {
        println("Task start")
        Thread.sleep(5000)
        println("Task end")
        return 0
    }
}

class Async : Application() {

    var task = MyTask()

    override fun start(primaryStage: Stage) {
        var status = Label()

        var startBtn = Button("Start")
        var stopBtn = Button("Stop")
        stopBtn.isDisable = true

        var spinner = ProgressIndicator()
        spinner.isVisible = false

        startBtn.onAction = EventHandler {
            spinner.isVisible = true
            startBtn.isDisable = true

            //spinner.isDisable = true

            spinner.progressProperty().bind(task.progressProperty())
            stopBtn.isDisable = false

            status.text = "Task is runnuing..."

            task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, EventHandler {
                status.text = "Finish!"
                startBtn.isDisable = false
                spinner.isVisible = false
                stopBtn.isDisable = true
            })
            Thread(task).start()
        }

        stopBtn.onAction = EventHandler {
            spinner.isVisible = false
            startBtn.isDisable = false
            stopBtn.isDisable = true
            status.text = "Task is canceled"
        }

        var root = FlowPane()
        root.children.addAll(status, spinner, startBtn, stopBtn)

        var scene = Scene(root, 500.0, 300.0)
        primaryStage.title = "Async test"
        primaryStage.scene = scene
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(arr: Array<String>) {
            launch(Async::class.java)
        }
    }
}