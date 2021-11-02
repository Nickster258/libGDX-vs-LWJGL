import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

object Animation extends App :
  val config = new LwjglApplicationConfiguration
  config.title = "Animation"
  config.width = 640
  config.height = 480
  new LwjglApplication(new AnimationExample, config)

class AnimationExample extends ApplicationAdapter :
  var shapeRenderer: ShapeRenderer = null
  var circleX = 200
  var circleY = 100
  var xSpeed = 2
  var ySpeed = 1

  override def create(): Unit =
    shapeRenderer = new ShapeRenderer

  override def render(): Unit =
    circleX += xSpeed
    circleY += ySpeed
    if (circleX < 0 || circleX > Gdx.graphics.getWidth) xSpeed *= -1
    if (circleY < 0 || circleY > Gdx.graphics.getHeight) ySpeed *= -1
    Gdx.gl.glClearColor(.25f, .25f, .25f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
    shapeRenderer.setColor(0, 1, 0, 1)
    shapeRenderer.circle(circleX, circleY, 75)
    shapeRenderer.end()

  override def dispose(): Unit =
    shapeRenderer.dispose()
