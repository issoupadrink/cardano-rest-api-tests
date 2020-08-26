package com.cardano.rest.tests.simulations.performance.blocks

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt


class BlocksPagesTotalSimulation extends Simulation {
  val host: String = System.getProperty("host")

  val httpConf: HttpProtocolBuilder = http.baseUrl(host)
    .header("Accept", "application/json")

  def getBlocksPagesTotal: ChainBuilder = {
    exec (
      http("Get blocks/pages/total")
        .get("blocks/pages/total")
        .check(status.is(200))
    )
  }

  val scn: ScenarioBuilder = scenario("performance test: blocks/pages/total")
    .forever(
      exec(getBlocksPagesTotal)
        .pause(5 seconds)
    )

  setUp(
    scn.inject(
      nothingFor(5 seconds),
      atOnceUsers(1),
      rampUsers(5) during (15 second)
    ).protocols(httpConf)
  ).maxDuration(30 seconds)
}
