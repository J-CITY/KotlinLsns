package GUISamples

import javafx.application.Application
import javafx.beans.Observable
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import javafx.scene.control.Alert.AlertType
import javafx.scene.layout.VBox
import java.util.Optional
import javafx.scene.control.ButtonBar.ButtonData
import javafx.scene.layout.GridPane
import javax.security.auth.callback.Callback


class PhoneBook(_name: String, _phone: String) {
    var name = _name
    var phone = _phone
}

class GUI3 : Application() {
    override fun start(primaryStage: Stage) {
        //создаем кнопку
        val btn1 = Button()
        btn1.text = "Simple alert'"
        //ставим действие на кнопку
        btn1.onAction = EventHandler {
            val alert = Alert(AlertType.ERROR)
            alert.title = "Some text"
            alert.headerText = "Information Alert"
            val s = "This is an example text"
            alert.contentText = s
            alert.show()
        }

        //создаем кнопку
        val btn2 = Button()
        btn2.text = "Confirm alert'"
        //ставим действие на кнопку
        btn2.onAction = EventHandler {
            val alert = Alert(AlertType.CONFIRMATION)
            alert.title = "Some alert"
            val s = "Confirm to clear text in text field !"
            alert.contentText = s
            val result = alert.showAndWait()
            if (result.isPresent && result.get() == ButtonType.OK) {
                print("Press OK")
            }
        }

        //создаем кнопку
        val btn3 = Button()
        btn3.text = "Custom alert'"
        //ставим действие на кнопку
        btn3.onAction = EventHandler {
            val dialog = Dialog<PhoneBook>()
            dialog.setTitle("Custom")
            dialog.setHeaderText("This is a custom dialog. Enter info and \n" + "press Okay (or click title bar 'X' for cancel).")
            dialog.setResizable(true)

            val label1 = Label("Name: ")
            val label2 = Label("Phone: ")
            val text1 = TextField()
            val text2 = TextField()

            val grid = GridPane()
            grid.add(label1, 1, 1)
            grid.add(text1, 2, 1)
            grid.add(label2, 1, 2)
            grid.add(text2, 2, 2)
            dialog.getDialogPane().setContent(grid)

            val buttonTypeOk = ButtonType("Okay", ButtonData.OK_DONE)
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk)


            dialog.setResultConverter {
                if (it == buttonTypeOk) {
                    PhoneBook(text1.getText(), text2.getText())
                } else
                    null
            }

            val result = dialog.showAndWait()

            if (result.isPresent()) {
                val p = result.get()
                println("" + p.name + " " + p.phone)
            }
        }

        val box = VBox()
        box.children.add(btn1)
        box.children.add(btn2)
        box.children.add(btn3)

        //создаём рут объект
        val root = StackPane()
        root.children.add(box)
        //создаем окно
        val scene = Scene(root, 300.0, 250.0)
        primaryStage.title = "Hello World!"
        primaryStage.scene = scene
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(GUI3::class.java)
        }
    }
}