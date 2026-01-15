import ReleaseTransformations.*
import sbtversionpolicy.withsbtrelease.ReleaseVersion

organization := "com.gu"
name := "play-json-extensions"
scalaVersion := "2.13.18"
description := "Additional type classes for the play-json serialization library"

startYear := Some(2015)
licenses += (
  "Two-clause BSD-style license",
  url("https://opensource.org/license/bsd-2-clause")
)

/*
 * To test whether any of these entries are redundant:
 * 1. Comment it out
 * 2. Run `sbt Runtime/dependencyList`
 * 3. If no earlier version appears in the dependency list, the entry can be removed.
 */
val safeTransitiveDependencies = {
  val jacksonVersion = "2.20.1"
  Seq(
    "com.fasterxml.jackson.core" % "jackson-annotations" % "2.20",
    "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
    "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % jacksonVersion,
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % jacksonVersion,
  )
}

libraryDependencies ++=   Seq(
  "org.playframework" %% "play-json" % "3.0.6",
  "org.scala-lang" % "scala-compiler" % scalaVersion.value % "provided",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
) ++ safeTransitiveDependencies

scalacOptions ++= Seq(
  "-release:11",
  "-feature", "-deprecation", "-unchecked",
  "-language:experimental.macros",
  "-Xfatal-warnings"
)

Test / testOptions +=
  Tests.Argument(TestFrameworks.ScalaTest, "-u", s"test-results/scala-${scalaVersion.value}", "-o")
parallelExecution := false // <- until TMap thread-safety issues are resolved

releaseVersion := ReleaseVersion.fromAggregatedAssessedCompatibilityWithLatestRelease().value
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  setNextVersion,
  commitNextVersion
)
