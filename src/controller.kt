package controller

import javafx.fxml.FXML
import javafx.scene.control.Button

/**
 * Created by naik on 06.02.16.
 */
class MainController {

    @FXML
    lateinit var testButton: Button

    fun initialize() {
        print("Controller working")
        testButton.setOnAction {
            print("Button clicked!")
        }
    }
}