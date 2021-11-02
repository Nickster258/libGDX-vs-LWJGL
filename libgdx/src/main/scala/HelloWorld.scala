import com.badlogic.gdx.{ApplicationAdapter, Gdx}
import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}

object HelloWorld extends App :
  val config = new LwjglApplicationConfiguration
  config.title = "Hello libGDX"
  config.width = 640
  config.height = 480
  new LwjglApplication(new HelloWorldGame, config)

class HelloWorldGame extends ApplicationAdapter :
  var batch: SpriteBatch = null
  var font: BitmapFont = null

  override def create(): Unit =
    batch = new SpriteBatch()
    font = new BitmapFont()

  override def render(): Unit =
    Gdx.gl.glClearColor(.25f, .25f, .25f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    font.draw(batch, "Hello libGDX!", Gdx.graphics.getWidth / 2, Gdx.graphics.getHeight / 2)
    batch.end()

  override def dispose(): Unit =
    batch.dispose()
    font.dispose()
