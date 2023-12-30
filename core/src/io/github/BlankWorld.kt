package io.github

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils

class BlankWorld : Game() {
    private lateinit var batch: SpriteBatch

    override fun create() {
        batch = SpriteBatch()
        setScreen(GameScreen(batch))
    }

    override fun dispose() {
        batch.dispose()
    }
}
