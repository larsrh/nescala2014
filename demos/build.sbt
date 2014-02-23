scalaVersion := "2.10.3"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "com.chuusai" % "shapeless_2.10.2" % "2.0.0-SNAPSHOT"

libraryDependencies += "org.typelevel" %% "shapeless-scalaz" % "0.2-SNAPSHOT"

initialCommands in console := """
  import scalaz._
  import scalaz.syntax.order._
  import scalaz.std.anyVal._
  import scalaz.std.string._
  import shapeless.contrib.scalaz._
"""
