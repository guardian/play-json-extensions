import ReleaseTransformations.*
import sbtversionpolicy.withsbtrelease.ReleaseVersion

organization := "com.gu"
name := "play-json-extensions"
scalaVersion := "2.13.15"
crossScalaVersions := (14 to scalaVersion.value.split('.').last.toInt).map(minor => s"2.13.$minor")
crossVersion := CrossVersion.full // see https://github.com/scala/bug/issues/12862#issuecomment-2457553135
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
releaseCrossBuild := true // true if you cross-build the project for multiple Scala versions
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
