import ReleaseTransformations.*
import sbtversionpolicy.withsbtrelease.ReleaseVersion

organization := "com.gu"
name := "play-json-extensions"
scalaVersion := "2.13.15"
description := "Additional type classes for the play-json serialization library"

startYear := Some(2015)
licenses += (
  "Two-clause BSD-style license",
  url("https://opensource.org/license/bsd-2-clause")
)

libraryDependencies ++=   Seq(
  "org.playframework" %% "play-json" % "3.0.4",
  "org.scala-lang" % "scala-compiler" % scalaVersion.value % "provided",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

scalacOptions ++= Seq(
  "-release:11",
  "-feature", "-deprecation", "-unchecked",
  "-language:experimental.macros"
)

Test / testOptions +=
  Tests.Argument(TestFrameworks.ScalaTest, "-u", s"test-results/scala-${scalaVersion.value}", "-o")
parallelExecution := false // <- until TMap thread-safety issues are resolved

// releaseVersion := ReleaseVersion.fromAggregatedAssessedCompatibilityWithLatestRelease().value,
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
