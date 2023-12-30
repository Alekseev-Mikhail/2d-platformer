package io.github

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle

const val FALL_SPEED = 9.8f

class Player(val rectangle: Rectangle, val texture: Texture) {
    private var isJumping = false
    private var isMoving = false
    private var isWasMoving = false
    private var isFalling = false
    private var jumpTime = 0f
    private var deltaJump = 0.1f
    private var deltaFall = 0f
    var isForward = true
    var speed = 6f

    fun rest(delta: Float, blocks: List<Block>) {
        if (isJumping) {
            val newY = rectangle.y + speed / deltaJump
            var newX = rectangle.x
            if (isWasMoving) {
                if (isForward) newX += speed / 2 else newX -= speed / 2
            }
            val newRectangle = Rectangle(newX, newY, rectangle.width, rectangle.height)
            if (!isOverlaps(blocks, newRectangle)) jump(delta, newRectangle) else jumpFinish()
        } else {
            val newRectangle = Rectangle(rectangle.x, rectangle.y - FALL_SPEED * deltaFall, rectangle.width, rectangle.height)
            if (!isOverlaps(blocks, newRectangle)) fall(newRectangle) else fallFinish()
        }
        isMoving = false
    }

    fun move(deltaX: Float, blocks: List<Block>, isForward: Boolean) {
        val newRectangle = Rectangle()
        newRectangle.set(rectangle)
        newRectangle.x += deltaX
        if (!isOverlaps(blocks, newRectangle)) rectangle.set(newRectangle)
        isMoving = true
        this.isForward = isForward
    }

    fun jump() {
        if (!isFalling) isJumping = true
        isWasMoving = isMoving
    }

    fun dispose() {
        texture.dispose()
    }

    private fun jump(delta: Float, newRectangle: Rectangle) {
        rectangle.set(newRectangle)
        deltaJump += 0.1f
        jumpTime += delta
        if (jumpTime >= 0.5f) {
            jumpFinish()
        }
    }

    private fun jumpFinish() {
        isJumping = false
        isFalling = true
        isWasMoving = false
        jumpTime = 0f
        deltaJump = 0.1f
    }

    private fun fall(newRectangle: Rectangle) {
        deltaFall += 0.1f
        if (newRectangle.y > 0f) {
            isFalling = true
            rectangle.set(newRectangle)
        } else {
            fallFinish()
        }
    }

    private fun fallFinish() {
        deltaFall = 0f
        isFalling = false
    }

    private fun isOverlaps(blocks: List<Block>, newRectangle: Rectangle): Boolean {
        var isOverlaps = false
        blocks.find { block ->
            isOverlaps = block.overlaps(newRectangle)
            isOverlaps
        }
        return isOverlaps
    }
}