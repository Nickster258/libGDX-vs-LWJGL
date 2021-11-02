import scala.language.postfixOps
import scala.sys.process._

name := "libgdx_vs_lwjgl"

lazy val lwjglOsDistinction = System.getProperty("os.name") match {
  case n if n.startsWith("Linux")   => "linux"
  case n if n.startsWith("Mac")     => "macos"
  case n if n.startsWith("Windows") => "windows"
  case _ => throw new Exception("Unknown platform!")
}

lazy val libgdxVersion = "1.10.0"
lazy val lwjglVersion = "3.2.3"

lazy val libgdxPlatformStub = s"gdx-platform-$libgdxVersion-natives-desktop.jar"
lazy val libgdxPlatformUrl = s"https://repo1.maven.org/maven2/com/badlogicgames/gdx/gdx-platform/$libgdxVersion/$libgdxPlatformStub"

lazy val downloadNativesJar = taskKey[Unit]("Download the natives-desktop.jar")

lazy val libgdx = project.in(file("libgdx"))
  .settings(
    downloadNativesJar := {
      lazy val libgdxPlatformLocation = s"libgdx/lib/$libgdxPlatformStub"
      if(java.nio.file.Files.notExists(new File(libgdxPlatformLocation).toPath)) {
        println("Natives do not exist, downloading natives...")
        url(libgdxPlatformUrl) #> file(libgdxPlatformLocation) !
      } else {
        println("Natives exist, no need to download natives.")
      }
    },
    Compile / compile := (Compile / compile).dependsOn(downloadNativesJar).value,
    scalaVersion := "3.0.2",
    libraryDependencies ++= Seq(
      "com.badlogicgames.gdx" % "gdx" % libgdxVersion,
      "com.badlogicgames.gdx" % "gdx-backend-lwjgl" % libgdxVersion
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
