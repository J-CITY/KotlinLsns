package GUISamples

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.stage.Stage
import java.util.Collections.addAll
import javafx.scene.control.Slider
import javafx.scene.layout.*

class GUI1 : Application() {
    override fun start(primaryStage: Stage) {

        //создаём лайауты
        val root = VBox()
        val hBox = HBox()
        hBox.children.addAll(Button("Button#1"), Button("Button#2"))
        val slider = Slider(1.0, 10.0, 4.0)
        slider.isShowTickLabels = true
        root.children.add(Label("Label"))
        root.children.addAll(hBox, slider)

        var grid = GridPane();
        grid.setGridLinesVisible(true);
        grid.add(Label("0x0"), 0, 0);
        grid.add(Label("0x1"), 0, 1);
        grid.add(Label("1x1"), 1, 1);
        grid.add(Label("1x2"), 1, 2);
        grid.add(Label("5x5"), 5, 5);

        val columnConstraints = ColumnConstraints()
        columnConstraints.setPrefWidth(130.0)
        val columnConstraints1 = ColumnConstraints()
        columnConstraints1.setPercentWidth(20.0)
        grid.getColumnConstraints().addAll(columnConstraints, columnConstraints1)

        hBox.children.add(grid)

        //создаем окно
        val scene = Scene(root, 500.0, 450.0)

        primaryStage.title = "Hello World!"
        primaryStage.scene = scene
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(GUI1::class.java)
        }
    }
}