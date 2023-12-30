package io.github

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

fun main() {
    val config = Lwjgl3ApplicationConfiguration()
    config.setTitle("The Blank World")
    config.setWindowedMode(1600, 900)
    config.setResizable(false)
    config.useVsync(true)
    config.setWindowIcon("badlogic.jpg")
    Lwjgl3Application(BlankWorld(), config)
}
