Play-Json extensions
==========================

[![play-json-extensions Scala version support](https://index.scala-lang.org/guardian/play-json-extensions/play-json-extensions/latest-by-scala-version.svg?platform=jvm)](https://index.scala-lang.org/guardian/play-json-extensions/play-json-extensions)

This is a minimal fork of [bizzabo/play-json-extensions](https://github.com/bizzabo/play-json-extensions).

This fork is restricted in the versions it supports:

* Scala 2.13 (some of this library may eventually be available for Scala 3)
* play-json 3.0.x, specifically only for the `org.playframework` group id, not `com.typesafe.play`
  (see https://github.com/guardian/maintaining-scala-projects/issues/4 for the bad consequences of mixing)

#### Difference from `bizzabo/play-json-extensions`

The fork was taken at commit [ec3a07e5](https://github.com/bizzabo/play-json-extensions/commit/ec3a07e5a0953ead7bb70f604bd626bb2bda2f69).
It has some modifications:

* [Modifications](https://github.com/scala/bug/issues/12862#issuecomment-1799369339) to compile on later
versions of Scala 2.13
* Maven Group id: `ai.x` → `com.gu`
* Class package: `ai.x.play.json` → `com.gu.ai.x.play.json`
