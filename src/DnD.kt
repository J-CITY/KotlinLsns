import javafx.application.Application
import javafx.event.EventHandler
import javafx.geometry.Point2D
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.input.ClipboardContent
import javafx.scene.input.DataFormat
import javafx.scene.input.DragEvent
import javafx.scene.input.TransferMode
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage

class DnD: Application() {

    private var mContextDragOver: EventHandler<DragEvent>? = null
    private var mContextDragDropped: EventHandler<DragEvent>? = null

    lateinit var btn: Button
    var state = DataFormat("state")
    override fun start(primaryStage: Stage) {
        btn = Button()

        var root = AnchorPane()
        root.children.add(btn)

        var scene = Scene(root, 640.0, 480.0)

        mContextDragOver = EventHandler { event ->
            println("Continue")
            val p = Point2D(event.sceneX, event.sceneY)
            val localCoords = btn.parent.sceneToLocal(p)
            btn.relocate(
                (localCoords.x),
                (localCoords.y)
            )
            event.consume()
        }

        mContextDragDropped = EventHandler { event ->
            println("Over")
            btn.parent.onDragOver = null
            btn.parent.onDragDropped = null

            val p = Point2D(event.sceneX, event.sceneY)
            val localCoords = btn.parent.sceneToLocal(p)
            btn.relocate(
                (localCoords.x),
                (localCoords.y)
            )

            event.isDropCompleted = true
            event.consume()
        }

        btn.setOnDragDetected {event ->
            println("Drag start")

            val b = event.source as Button
            b.parent.onDragOver = null
            b.parent.onDragDropped = null

            b.parent.onDragOver = mContextDragOver
            b.parent.onDragDropped = mContextDragDropped

            val content = ClipboardContent()
            content[state] = 1
            b.startDragAndDrop(*TransferMode.ANY).setContent(content)
            event.consume()
        }
        primaryStage.scene = scene
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(DnD::class.java)
        }
    }
}