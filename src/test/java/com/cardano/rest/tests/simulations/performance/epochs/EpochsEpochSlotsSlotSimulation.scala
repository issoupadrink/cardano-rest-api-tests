package com.cardano.rest.tests.simulations.performance.epochs

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt


class EpochsEpochSlotsSlotSimulation extends Simulation {
  val host: String = System.getProperty("host")
  val epoch = "1"
  val slot = "1"

  val httpConf: HttpProtocolBuilder = http.baseUrl(host)
    .header("Accept", "application/json")

  def getEpochsEpochSlotsSlot: ChainBuilder = {
    exec (
      http("Get epochs/{epoch}/slots/{slot}")
        .get(String.format("epochs/%s/slots/%s", epoch, slot))
        .check(status.is(200))
    )
  }

  val scn: ScenarioBuilder = scenario("performance test: epochs/{epoch}/slots/{slot}")
    .forever(
      exec(getEpochsEpochSlotsSlot)
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
