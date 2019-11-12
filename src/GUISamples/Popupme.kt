package GUISamples

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.*
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Label
import javafx.stage.Stage
import javafx.stage.Popup

class Popupme : Application() {
    // launch the application
    override fun start(stage: Stage) {
        // create a button
        val button = Button("button")
        // create a tile pane
        val root = TilePane()


        // create a label
        val label = Label("This is a Popup")
        // create a popup
        val popup = Popup()
        // set background
        label.style = " -fx-background-color: white;"
        // add the label
        popup.content.add(label)
        // set size of label
        label.minWidth = 80.0
        label.minHeight = 50.0

        // when button is pressed
        button.onAction = EventHandler<ActionEvent> {
            if (!popup.isShowing)
                popup.show(stage)
            else
                popup.hide()
        }
        // add button
        root.children.add(button)

        stage.title = "Popup"
        val scene = Scene(root, 200.0, 200.0)
        stage.scene = scene
        stage.show()
    }

    companion object {

        // Main Method
        @JvmStatic
        fun main(args: Array<String>) {
            // launch the application
            launch(Popupme::class.java)
        }
    }
}