import javafx.application.Application
import javafx.beans.binding.Bindings
import javafx.beans.binding.When
import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.concurrent.Task
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.geometry.Point2D
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.input.*
import javafx.scene.layout.AnchorPane
import javafx.scene.shape.Circle
import javafx.scene.shape.CubicCurve
import javafx.stage.Stage
import java.io.IOException
import java.io.Serializable
import java.util.*

class Point2dSerial(x: Double, y: Double) : Point2D(x, y), Serializable
var stateAddLink = DataFormat("linkAdd")
var stateAddNode = DataFormat("nodeAdd")

lateinit var btn1: DraggableNode
lateinit var btn2: DraggableNode

class DraggableNode : AnchorPane() {
    @FXML
    var root_pane: AnchorPane? = null

    @FXML
    var left_link_handle: AnchorPane? = null

    @FXML
    var right_link_handle: AnchorPane? = null

    @FXML
    var title_bar: Label? = null

    lateinit var contextDragOver: EventHandler<DragEvent>
    lateinit var contextDragDropped: EventHandler<DragEvent>

    lateinit var linkDragDetected: EventHandler<MouseEvent>
    lateinit var linkDragDropped: EventHandler<DragEvent>
    lateinit var contextLinkDragOver: EventHandler<DragEvent>
    lateinit var contextLinkDagDropped: EventHandler<DragEvent>

    var myLink = NodeLink()
    var offset = Point2D(0.0, 0.0)

    var superParent: AnchorPane? = null

    @FXML
    private fun initialize() {
        nodeHandlers()
        linkHandlers()

        left_link_handle!!.onDragDetected = linkDragDetected
        left_link_handle!!.onDragDropped = linkDragDropped
        right_link_handle!!.onDragDetected = linkDragDetected
        right_link_handle!!.onDragDropped = linkDragDropped

        myLink.isVisible = false

        parentProperty().addListener{ o, old, new -> superParent = parent as AnchorPane}
    }

    fun updatePoint(p: Point2D) {
        var local = parent.sceneToLocal(p)
        relocate(
            (local.x - offset.x),
            (local.y - offset.y)
        )
    }

    fun nodeHandlers() {

        contextDragOver = EventHandler { event ->
            updatePoint(Point2D(event.sceneX, event.sceneY))
            event.consume()
        }

        contextDragDropped = EventHandler { event ->
            parent.onDragDropped = null
            parent.onDragOver = null
            event.isDropCompleted = true
            event.consume()
        }

        title_bar!!.onDragDetected = EventHandler { event->
            parent.onDragOver = contextDragOver
            parent.onDragDropped = contextDragDropped

            offset = Point2D(event.x, event.y)
            updatePoint(Point2D(event.sceneX, event.sceneY))

            val content = ClipboardContent()
            content[stateAddNode] = "node"
            startDragAndDrop(*TransferMode.ANY).setContent(content)
        }



    }

    fun linkHandlers() {

        linkDragDetected = EventHandler { event ->
            parent.onDragOver = null
            parent.onDragDropped = null

            parent.onDragOver = contextLinkDragOver
            parent.onDragDropped = contextLinkDagDropped

            superParent!!.children.add(0, myLink)
            myLink.isVisible = true

            val p = Point2D(layoutX + width/2, layoutY+height/2)
            myLink.setStart(p)

            val content = ClipboardContent()
            content[stateAddLink] = "link"
            startDragAndDrop(*TransferMode.ANY).setContent(content)
            event.consume()
        }

        linkDragDropped = EventHandler { event ->
            println("link connect")
            parent.onDragOver = null
            parent.onDragDropped = null

            myLink.isVisible = false
            superParent!!.children.removeAt(0)

            //val source = event.source as AnchorPane

            val link = NodeLink()
            link.bindStartEnd(btn1, btn2)
            superParent!!.children.add(0, link)

            val content = ClipboardContent()
            content[stateAddLink] = "link"
            startDragAndDrop(*TransferMode.ANY).setContent(content)
            event.isDropCompleted = true
            event.consume()
        }


        contextLinkDragOver = EventHandler { event ->
            event.acceptTransferModes(*TransferMode.ANY)
            if (!myLink.isVisible) myLink.isVisible = true
            myLink.setEnd(Point2D(event.x, event.y))

            event.consume()
        }

        contextLinkDagDropped = EventHandler { event ->
            println("link dropped")
            parent.onDragDropped = null
            parent.onDragOver = null

            myLink.isVisible = false
            superParent!!.children.removeAt(0)

            event.isDropCompleted = true
            event.consume()
        }
    }

    init {
        val fxmlLoader = FXMLLoader(
            javaClass.getResource("/resources/DraggableNode.fxml")
        )
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
        id = UUID.randomUUID().toString()
    }
}

class NodeLink : AnchorPane() {
    @FXML
    var node_link: CubicCurve? = null

    val offsetX = SimpleDoubleProperty()
    val offsetY = SimpleDoubleProperty()
    val offsetDirX1 = SimpleDoubleProperty()
    val offsetDirX2 = SimpleDoubleProperty()
    val offsetDirY1 = SimpleDoubleProperty()
    val offsetDirY2 = SimpleDoubleProperty()

    @FXML
    private fun initialize() {
        offsetX.set(100.0)
        offsetY.set(50.0)

        offsetDirX1.bind(
            When(node_link!!.startXProperty().greaterThan(node_link!!.endXProperty())).then(-1.0).otherwise(1.0))

        offsetDirX2.bind(
            When(node_link!!.startXProperty().greaterThan(node_link!!.endXProperty())).then(1.0).otherwise(-1.0))

        node_link!!.controlX1Property().bind(Bindings.add(node_link!!.startXProperty(), offsetX.multiply(offsetDirX1)))
        node_link!!.controlX2Property().bind(Bindings.add(node_link!!.endXProperty(), offsetX.multiply(offsetDirX2)))
        node_link!!.controlY1Property().bind(Bindings.add(node_link!!.startYProperty(), offsetY.multiply(offsetDirY1)))
        node_link!!.controlY2Property().bind(Bindings.add(node_link!!.endYProperty(), offsetY.multiply(offsetDirY2)))
    }

    fun setStart(point: Point2D) {
        node_link!!.startX = point.x
        node_link!!.startY = point.y
    }

    fun setEnd(point: Point2D) {
        node_link!!.endX = point.x
        node_link!!.endY = point.y
    }

    fun bindStartEnd(source1: DraggableNode, source2: DraggableNode) {
        node_link!!.startXProperty().bind(Bindings.add(source1.layoutXProperty(), source1.width/2.0))
        node_link!!.startYProperty().bind(Bindings.add(source1.layoutYProperty(), source1.height/2.0))
        node_link!!.endXProperty().bind(Bindings.add(source2.layoutXProperty(), source2.width/2.0))
        node_link!!.endYProperty().bind(Bindings.add(source2.layoutYProperty(), source2.height/2.0))
    }

    init {
        val fxmlLoader = FXMLLoader(
            javaClass.getResource("/resources/NodeLink.fxml")
        )
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        try {
            fxmlLoader.load<Any>()
        } catch (exception: IOException) {
            throw RuntimeException(exception)
        }
        id = UUID.randomUUID().toString()
    }
}


class DnD2: Application() {

    override fun start(primaryStage: Stage) {
        btn1 = DraggableNode()
        btn2 = DraggableNode()

        var root = AnchorPane()
        root.children.add(btn1)
        root.children.add(btn2)

        var scene = Scene(root, 640.0, 480.0)
        primaryStage.scene = scene
        primaryStage.show()
    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(DnD2::class.java)
        }
    }
}