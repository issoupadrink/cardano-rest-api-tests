package com.cardano.rest.tests.simulations.load

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt


class GenesisSummarySimulation extends Simulation {
  val host: String = System.getProperty("host")

  val httpConf: HttpProtocolBuilder = http.baseUrl(host)
    .header("Accept", "application/json")

  def getGenesisSummary: ChainBuilder = {
    exec (
      http("Get genesis/summary")
        .get("genesis/summary")
        .check(status.is(200))
    )
  }

  val scn: ScenarioBuilder = scenario("Genesis Summary Scenario")
    .forever(
      exec(getGenesisSummary)
        .pause(5 seconds)
    )

  setUp(
    scn.inject(
      nothingFor(5 seconds),
      atOnceUsers(1),
      rampUsers(5) during (15 second)
    ).protocols(httpConf)
  ).maxDuration(30 seconds)
    .assertions(
      global.responseTime.percentile(90).lt(1 second),
      global.successfulRequests.percent.gt(95)
    )
}
