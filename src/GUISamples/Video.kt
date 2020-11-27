package GUISamples

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.web.WebView
import javafx.stage.Stage


class VideoPlayer : Application() {
    @Throws(Exception::class)
    override fun start(stage: Stage) {
        val webview = WebView()
        webview.engine.load(
            "https://www.youtube.com/watch?v=G51PJ5KWAnc"
        )
        webview.setPrefSize(640.0, 390.0)
        stage.scene = Scene(webview)
        stage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(VideoPlayer::class.java)
        }
    }
}