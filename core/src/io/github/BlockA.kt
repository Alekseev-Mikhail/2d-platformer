package io.github

import com.badlogic.gdx.math.Rectangle

class BlockA(x: Float, y: Float, path: String) : Block(x, y, path) {
    override fun overlaps(otherRectangle: Rectangle): Boolean {
        return rectangle.overlaps(otherRectangle)
    }
}