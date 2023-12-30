package io.github

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.ScreenUtils

class GameScreen(private val batch: SpriteBatch) : ScreenAdapter() {
    private val camera = OrthographicCamera(1920f, 1080f)
    private val player = Player(Rectangle(0f, 500f, 0f, 0f), Texture("head.png"))
    private val blocks = mutableListOf(
        BlockA(100f, 150f, "block.jpg"),
        BlockA(100f, 400f, "block.jpg"),
        BlockA(500f, 200f, "block.jpg"),
//        BlockA(300f, 200f, "block.jpg"),
//        BlockA(500f, 200f, "block.jpg"),
//        BlockA(500f, 200f, "block.jpg"),
    )

    override fun show() {
        camera.position.x = player.rectangle.x
        camera.position.y = 450f
        player.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        player.rectangle.setSize(player.texture.width.toFloat() / 2, player.texture.height.toFloat() / 4)
    }

    override fun render(delta: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.move(-player.speed, blocks, false)
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.move(player.speed, blocks, true)
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) player.jump()
        }

        player.rest(delta, blocks)
//        camera.position.x = player.rectangle.x

        ScreenUtils.clear(1f, 1f, 1f, 1f)
        camera.update()
        batch.projectionMatrix = camera.combined
        batch.begin()
        blocks.forEach { block -> batch.draw(block.texture, block.rectangle) }
        batch.draw(
            player.texture,
            player.rectangle.x,
            player.rectangle.y,
            player.texture.width.toFloat() / 2f,
            player.texture.height.toFloat() / 2f,
            0,
            0,
            player.texture.width,
            player.texture.height,
            player.isForward,
            false
        )
        batch.end()
    }

    private fun Batch.draw(texture: Texture, rectangle: Rectangle) {
        draw(texture, rectangle.x, rectangle.y)
    }

    override fun dispose() {
        player.dispose()
    }
}