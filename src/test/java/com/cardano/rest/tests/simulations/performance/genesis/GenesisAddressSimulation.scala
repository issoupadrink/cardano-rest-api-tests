package com.cardano.rest.tests.simulations.performance.genesis

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt


class GenesisAddressSimulation extends Simulation {
  val host: String = System.getProperty("host")

  val httpConf: HttpProtocolBuilder = http.baseUrl(host)
    .header("Accept", "application/json")

  def getGenesisAddress: ChainBuilder = {
    exec (
      http("Get genesis/address")
        .get("genesis/address")
        .check(status.is(200))
    )
  }

  val scn: ScenarioBuilder = scenario("performance test: genesis/address")
    .forever(
      exec(getGenesisAddress)
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
