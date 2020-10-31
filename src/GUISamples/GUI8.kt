package GUISamples


import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.stage.FileChooser
import javafx.stage.FileChooser.ExtensionFilter
import javafx.stage.Stage
import kotlinx.coroutines.Runnable

import java.io.File
import java.util.regex.Pattern

class MPlayer : Application() {
    internal var selectedFile: File? = null
    internal var mplayer: MediaPlayer? = null
    internal var musicSlider: Slider

    init {
        musicSlider = Slider()
    }

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val root = object : BorderPane() {
            init {
                val filenameLabel = Label("")
                val fileChooser = FileChooser()
                fileChooser.title = "Open File"

                fileChooser.extensionFilters.addAll(
                    ExtensionFilter("Audio Files", "*.wav", "*.mp3")
                )

                val vbox = object : VBox() {
                    init {
                        children.add(filenameLabel)
                        val hbox = object : HBox() {
                            init {
                                val playButton = Button("Play")
                                val pauseButton = Button("Pause")

                                playButton.setOnAction { e -> mplayer?.play() }
                                pauseButton.setOnAction { e -> mplayer?.pause() }

                                val stopButton = object : Button("Stop") {
                                    init {
                                        setOnAction { e -> mplayer?.stop() }
                                    }
                                }
                                children.addAll(playButton, pauseButton, stopButton)
                            }
                        }
                        children.add(hbox)
                    }
                }
                center = vbox

                val menubar = object : MenuBar() {
                    init {
                        val menu = object : Menu("File") {
                            init {
                                val selectMenuItem = object : MenuItem("Select") {
                                    init {
                                        setOnAction { e ->
                                            selectedFile = fileChooser.showOpenDialog(primaryStage)
                                            if (selectedFile != null) {
                                                var media: Media? = null

                                                val spacePattern = Pattern.compile(" ")
                                                var url = selectedFile!!.toURI()
                                                //val matcher = spacePattern.matcher(url)
                                                //url = matcher.replaceAll("\\ ")

                                                println(url.toString())
                                                media = Media(url.toString())

                                                mplayer = MediaPlayer(media)

                                                musicSlider.min = 0.0
                                                musicSlider.max = 100.0
                                            }
                                        }
                                    }
                                }
                                val pauseMenuItem = MenuItem("Pause")
                                val playMenuItem = MenuItem("Play")
                                val stopMenuItem = MenuItem("Stop")

                                items.addAll(selectMenuItem, playMenuItem, pauseMenuItem, stopMenuItem)
                            }
                        }
                        menus.add(menu)
                    }
                }
                top = menubar

                bottom = musicSlider
            }
        }

        Thread(Runnable {
            while (true) {
                if (mplayer != null) {
                    val currentTime = mplayer?.currentTime!!.toSeconds()
                    val allTime =  mplayer?.stopTime!!.toSeconds()

                    musicSlider.value = currentTime * 100.0 / allTime
                    println("Cur time " + currentTime * 100.0 / allTime)
                }
                try {
                    Thread.sleep(900)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }).start()


        val scene = Scene(root, 400.0, 100.0)

        primaryStage.scene = scene
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MPlayer::class.java)
        }
    }
}