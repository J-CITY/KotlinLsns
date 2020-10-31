package GUISamples

//https://o7planning.org/ru/11151/javafx-webview-and-webengine-tutorial
//ещё можно работать прямо с js объектами

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.web.WebEngine
import javafx.scene.web.WebView
import javafx.stage.Stage
import java.text.SimpleDateFormat
import java.text.DateFormat
import java.util.*
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker
import netscape.javascript.JSObject

class GUI10 : Application() {
    private val df = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
    private lateinit var label: Label

    // A Bridge class and must a public class
    inner class Bridge {
        fun showTime() {
            println("Show Time")
            label!!.setText("Now is: " + df.format(Date()))
        }
    }

    override fun start(stage: Stage) {
        val button = Button("Execute Javascript (Call from JavaFX)")

        val browser = WebView()
        val webEngine = browser.engine

        label = Label("")

        // Enable Javascript.
        webEngine.isJavaScriptEnabled = true
        webEngine.loadContent(HTML_STRING)

        // A Worker load the page
        val worker = webEngine.loadWorker
        // Listening to the status of worker
        worker.stateProperty().addListener { observable, oldValue, newValue ->
            // When load successed.
            if (newValue === Worker.State.SUCCEEDED) {
                // Get window object of page.
                val jsobj = webEngine.executeScript("window") as JSObject
                // Set member for 'window' object.
                // In Javascript access: window.myJavaMember....
                jsobj.setMember("myJavaMember", Bridge())
            }
        }

        button.onAction = EventHandler {
            // Call a JavaScript function of the current page
            webEngine.executeScript("changeBgColor();")
        }

        val root = VBox()
        root.padding = Insets(5.0)
        root.spacing = 5.0
        root.children.addAll(label, button, browser)

        val scene = Scene(root)

        stage.title = "WebView"
        stage.scene = scene
        stage.width = 800.0
        stage.height = 600.0
        stage.show()
    }

    // A HTML Content with a javascript function.
    private val HTML_STRING = """
        <html>
        <head>
          <script language='javascript'>
             function changeBgColor()  {
               var color= document.getElementById('color').value;
               document.body.style.backgroundColor= color;
             }
             function callToJavaFX()  {
                myJavaMember.showTime();
             }
          </script>
        </head>
        <body>
            <h2>This is Html content</h2>
            <b>Enter Color:</b>
            <input id='color' value='yellow' />
            <br>
            <button onclick='changeBgColor();'>Change Bg Color</button>
            <br>
            <button onclick='callToJavaFX();'>Call To JavaFX</button>
        </body>
        </html>"""

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(GUI10::class.java)
        }
    }

}