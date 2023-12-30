package io.github

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle

abstract class Block(x: Float, y: Float, path: String) {
    val texture = Texture(path)
    val rectangle = Rectangle(x, y, texture.width.toFloat(), texture.height.toFloat())

    abstract fun overlaps(otherRectangle: Rectangle): Boolean
}