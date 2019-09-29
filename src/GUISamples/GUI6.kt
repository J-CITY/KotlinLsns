package GUISamples

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import javafx.geometry.Pos
import javafx.scene.layout.GridPane
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.text.Text


class GUI6 : Application() {
    override fun start(stage: Stage) {
        //creating label email
        val text1 = Text("Email")

        //creating label password
        val text2 = Text("Password")

        //Creating Text Filed for email
        val textField1 = TextField()

        //Creating Text Filed for password
        val textField2 = PasswordField()

        //Creating Buttons
        val button1 = Button("Submit")
        val button2 = Button("Clear")

        //Creating a Grid Pane
        val gridPane = GridPane()

        //Setting size for the pane
        gridPane.setMinSize(400.0, 200.0)

        //Setting the padding
        gridPane.padding = Insets(10.0, 10.0, 10.0, 10.0)

        //Setting the vertical and horizontal gaps between the columns
        gridPane.vgap = 5.0
        gridPane.hgap = 5.0

        //Setting the Grid alignment
        gridPane.alignment = Pos.CENTER

        //Arranging all the nodes in the grid
        gridPane.add(text1, 0, 0)
        gridPane.add(textField1, 1, 0)
        gridPane.add(text2, 0, 1)
        gridPane.add(textField2, 1, 1)
        gridPane.add(button1, 0, 2)
        gridPane.add(button2, 1, 2)

        //Styling nodes
        button1.style = "-fx-background-color: darkslateblue; -fx-text-fill: white;"
        button2.style = "-fx-background-color: darkslateblue; -fx-text-fill: white;"

        text1.setStyle("-fx-font: normal bold 20px 'serif' ")
        text2.setStyle("-fx-font: normal bold 20px 'serif' ")
        gridPane.style = "-fx-background-color: BEIGE;"

        // Creating a scene object
        val scene = Scene(gridPane)

        // Setting title to the Stage
        stage.title = "CSS Example"

        // Adding scene to the stage
        stage.scene = scene

        //Displaying the contents of the stage
        stage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(GUI6::class.java)
        }
    }
}