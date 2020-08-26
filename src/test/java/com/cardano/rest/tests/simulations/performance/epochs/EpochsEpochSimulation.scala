package com.cardano.rest.tests.simulations.performance.epochs

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt


class EpochsEpochSimulation extends Simulation {
  val host: String = System.getProperty("host")
  val epoch = "1"

  val httpConf: HttpProtocolBuilder = http.baseUrl(host)
    .header("Accept", "application/json")

  def getEpochsEpoch: ChainBuilder = {
    exec (
      http("Get epochs/{epoch}")
        .get(String.format("epochs/%s", epoch))
        .check(status.is(200))
    )
  }

  val scn: ScenarioBuilder = scenario("performance test: epochs/{epoch}")
    .forever(
      exec(getEpochsEpoch)
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
