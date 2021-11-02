import com.badlogic.gdx.backends.lwjgl3.{Lwjgl3Application, Lwjgl3ApplicationConfiguration}
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}
import com.badlogic.gdx.{ApplicationAdapter, Gdx}

object HelloWorld extends App :
  val config = new Lwjgl3ApplicationConfiguration
  config.setTitle("Hello libGDX")
  config.setWindowedMode(640, 480)
  new Lwjgl3Application(new HelloWorldGame, config)

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
