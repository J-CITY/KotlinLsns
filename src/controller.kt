package controller

import javafx.fxml.FXML
import javafx.scene.control.Button

class MainController {
    @FXML
    lateinit var testButton: Button

    fun initialize() {
        println("Controller working")
        testButton.setOnAction {
            println("Button clicked!")
        }
    }
}

