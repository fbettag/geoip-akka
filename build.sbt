name := "geoip-akka"

organization := "ag.bett.scala"

version := "0.1"

scalaVersion := "2.9.1"

//scalacOptions ++= Seq("-deprecation")
scalacOptions ++= Seq("-unchecked", "-deprecation")

//seq(site.settings:_*)

//seq(ghpages.settings:_*)

//git.remoteRepo := "git@github.com:fbettag/geo-akka.git"


resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/snapshots/",
  "Repo Maven" at "http://repo1.maven.org/maven2/",
  "Scala Tools Releases" at "http://scala-tools.org/repo-releases/",
  "Scala Tools Snapshot" at "http://scala-tools.org/repo-snapshots/",
  "Nexus Releases" at "http://nexus.scala-tools.org/content/repositories/releases",
  "Nexus Snapshots" at "http://nexus.scala-tools.org/content/repositories/snapshots",
  "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
  "Bryan J Swift Repository" at "http://repos.bryanjswift.com/maven2/",
  "Evil Packet" at "http://evil-packet.org/m2/"
)

// if you have issues pulling dependencies from the scala-tools repositories (checksums don't match), you can disable checksums
//checksums := Nil

// Akka
libraryDependencies ++= {
  val akkaVersion = "2.0"
  Seq(
    "com.typesafe.akka" % "akka-kernel" % akkaVersion,
    "com.typesafe.akka" % "akka-actor" % akkaVersion,
    "com.typesafe.akka" % "akka-remote" % akkaVersion
  )
}

libraryDependencies ++= Seq()

