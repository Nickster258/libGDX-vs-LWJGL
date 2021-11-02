import scala.language.postfixOps

name := "libgdx_vs_lwjgl"

lazy val lwjglOsDistinction = System.getProperty("os.name") match {
  case n if n.startsWith("Linux")   => "linux"
  case n if n.startsWith("Mac")     => "macos"
  case n if n.startsWith("Windows") => "windows"
  case _ => throw new Exception("Unknown platform!")
}

lazy val libgdxVersion = "1.10.0"
lazy val lwjglVersion = "3.2.3"

lazy val libgdx = project.in(file("libgdx"))
  .settings(
    scalaVersion := "3.0.2",
    libraryDependencies ++= Seq(
      "com.badlogicgames.gdx" % "gdx" % libgdxVersion,
      "com.badlogicgames.gdx" % "gdx-backend-lwjgl3" % libgdxVersion,
      "com.badlogicgames.gdx" % "gdx-platform" % libgdxVersion classifier "natives-desktop"
    )
  )

lazy val lwjgl = project.in(file("lwjgl"))
  .settings(
    scalaVersion := "3.0.2",
    libraryDependencies ++= {
      Seq("lwjgl", "lwjgl-glfw", "lwjgl-opengl").flatMap {
        module => {
          Seq("org.lwjgl" % module % lwjglVersion, "org.lwjgl" % module % lwjglVersion classifier s"natives-$lwjglOsDistinction")
        }
      }
    }
  )
