import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

object ShapeRender extends App :
  val config = new LwjglApplicationConfiguration
  config.title = "Shape Rendering"
  config.width = 640
  config.height = 480
  new LwjglApplication(new ShapeRenderingExample, config)

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
