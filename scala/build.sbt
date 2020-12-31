val scala3Version = "3.0.0-M2"

// enablePlugins(ScalaJSPlugin)

lazy val root = project
  .in(file("."))
  .settings(
    name := "pslab",
    version := "0.1.0",

    scalaVersion := scala3Version,

    // This is an application with a main method
    // scalaJSUseMainModuleInitializer := true
  )

//unmanagedSources / includeFilter := "main.scala" || "general.scala" || "*index.scala*"

scalacOptions ++= Seq(
  // "-language:strictEquality"
)