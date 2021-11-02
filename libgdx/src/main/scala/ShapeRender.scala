import com.badlogic.gdx.backends.lwjgl3.{Lwjgl3Application, Lwjgl3ApplicationConfiguration}
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.{ApplicationAdapter, Gdx}

object ShapeRender extends App :
  val config = new Lwjgl3ApplicationConfiguration
  config.setTitle("Shape Rendering")
  config.setWindowedMode(640, 480)
  new Lwjgl3Application(new ShapeRenderingExample, config)

class ShapeRenderingExample extends ApplicationAdapter :
  var shapeRenderer: ShapeRenderer = null

  override def create(): Unit =
    shapeRenderer = new ShapeRenderer

  override def render(): Unit =
    Gdx.gl.glClearColor(.25f, .25f, .25f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
    shapeRenderer.setColor(0, 1, 0, 1)
    shapeRenderer.circle(200, 100, 75)
    shapeRenderer.end()

  override def dispose(): Unit =
    shapeRenderer.dispose()
