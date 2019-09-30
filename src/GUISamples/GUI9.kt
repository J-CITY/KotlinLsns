package GUISamples

import javafx.application.Application
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker
import javafx.concurrent.Worker.State
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ProgressBar
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.web.WebEngine
import javafx.scene.web.WebView
import javafx.stage.Stage

class GUI9: Application() {
    override fun start(stage: Stage) {
        val addressBar = TextField()
        addressBar.text = "https://google.com"

        val stateLabel = Label()
        stateLabel.textFill = Color.RED
        val progressBar = ProgressBar()
        val browser = WebView()
        val webEngine = browser.engine

        // A Worker load the page
        val worker = webEngine.loadWorker

        // Listening to the status of worker
        worker.stateProperty().addListener { observable, oldValue, newValue ->
            stateLabel.text = "Loading state: $newValue"
            if (newValue == Worker.State.SUCCEEDED) {
                stage.title = webEngine.location
                stateLabel.text = "Finish!"
            }
        }
        addressBar.onKeyReleased = EventHandler { event ->
            if (event.code == KeyCode.ENTER) {
                val url = addressBar.text
                webEngine.load(url)
            }
        }

        // Bind the progress property of ProgressBar
        // with progress property of Worker
        progressBar.progressProperty().bind(worker.progressProperty())
        progressBar.maxHeight(10.0)
        val root = VBox()

        val loadBox = HBox()
        loadBox.children.addAll(progressBar, stateLabel)
        root.children.addAll(addressBar, loadBox, browser)

        val scene = Scene(root)
        stage.title = "WebView"
        stage.scene = scene
        stage.width = 800.0
        stage.height = 600.0
        stage.show()
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            launch(GUI9::class.java)
        }
    }

}