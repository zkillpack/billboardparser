name := "billboardparser"

version := "1.0"

scalaVersion := "2.11.6"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.lihaoyi" %% "fastparse" % "0.1.7"

libraryDependencies += "org.scala-lang.modules" %% "scala-pickling" % "0.10.1"

libraryDependencies += "io.spray" %%  "spray-json" % "1.3.2"
