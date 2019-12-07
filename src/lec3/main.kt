import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.stage.Stage


interface _Button {
    fun render()
    fun onClick()
}

class HtmlButton(private val config: Builder)  : _Button {
    var btn = ""
    override fun render() {
        btn = "<style>button { \n" +
                "height: ${config.height}px;\n" +
                "width: ${config.width}px;\n" +
                "${config.style}\n" +
                "}</style>"
        btn += "\n" + "<button>${config.title}</button>"
        println(btn)
        onClick()
    }
    override fun onClick() {
        println("Click. Button HTML")
    }

    companion object Builder {
        protected var width: Float = 0.0f
        protected var height: Float = 0.0f
        protected var style: String = ""
        protected var title: String = ""

        fun width(value: Float) = apply { this.width = value }
        fun height(value: Float) = apply { this.height = value }
        fun style(value: String) = apply { this.style = value }
        fun title(value: String) = apply { this.title = value }
        fun build() = HtmlButton(this)
    }
}

class WindowsButton(private val config: Builder) : _Button {
    var button: Button? = null
    override fun render() {
        onClick()
    }

    override fun onClick() {
        button = Button(config.title)
        button!!.minWidth = config.width.toDouble()
        button!!.minHeight = config.height.toDouble()
        button!!.style = config.style
        button!!.onAction = EventHandler { value: ActionEvent? ->
            println("Click. Windows button");
        }
    }
    companion object Builder {
        protected var width: Float = 0.0f
        protected var height: Float = 0.0f
        protected var style: String = ""
        protected var title: String = ""

        fun width(value: Float) = apply { this.width = value }
        fun height(value: Float) = apply { this.height = value }
        fun style(value: String) = apply { this.style = value }
        fun title(value: String) = apply { this.title = value }
        fun build() = WindowsButton(this)
    }

    fun getBtn(): Button? {
        return button
    }
}

class CssAdapter(_json: String) {
    var json: String
    init {
        json = _json
    }

    fun getCss(): String {
        return json.substring(1, json.length-1)
    }
}

interface Dialog {
    fun renderWindow()
    fun createButton(): _Button
}
class HtmlDialog : Dialog {
    override fun renderWindow() {
        val b = createButton()
        b.render()
    }

    override fun createButton(): _Button {
        val b = HtmlButton.Builder.height(200.0f)
            .width(20.0f).title("HTML").style(CssAdapter("{background-color: powderblue;}").getCss()).build()
        return b
    }
}

class WindowsDialog : Dialog, Application() {
    lateinit var b: _Button
    override fun renderWindow() {

        launch(WindowsDialog::class.java)
    }

    override fun createButton(): _Button {
        val b = WindowsButton.Builder.height(20.0f)
            .width(200.0f).title("WINDOW").style("-fx-border-color: #ff0000; -fx-border-width: 5px;").build()
        return b
    }

    override fun start(primaryStage: Stage?) {
        primaryStage!!.setTitle("HBox Experiment 1")

        b = createButton()
        b.render()

        val hbox = HBox((b as WindowsButton).getBtn())

        val scene = Scene(hbox, 400.0, 150.0)
        primaryStage.setScene(scene)
        primaryStage.show()
    }
}

object OSChecker{
    fun getOsName(): String{
        return System.getProperty("os.name")
    }

    fun check(): Boolean {
        return System.getProperty("os.name") == "Windows 10"
    }
}

object Demo {
    private var dialog: Dialog? = null
    @JvmStatic
    fun main(args: Array<String>) {
        configure()
        runBusinessLogic()
    }

    fun configure() {
        dialog = if (OSChecker.check()) {
            WindowsDialog()
        } else {
            HtmlDialog()
        }
    }

    fun runBusinessLogic() {
        dialog!!.renderWindow()
    }
}
