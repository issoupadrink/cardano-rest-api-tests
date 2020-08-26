package com.cardano.rest.tests.simulations.performance.addresses

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt


class AddressesSummaryAddressSimulation extends Simulation {
  val host: String = System.getProperty("host")
  val address = "Ae2tdPwUPEZK72eZZqulakkhaUfTCcoaGepvQP718aYBczw5uZmp47h1k14"

  val httpConf: HttpProtocolBuilder = http.baseUrl(host)
    .header("Accept", "application/json")

  def getAddressesSummaryAddress: ChainBuilder = {
    exec (
      http("Get addresses/summary/{address}")
        .get(String.format("addresses/summary/%s", address))
        .check(status.is(200))
    )
  }

  val scn: ScenarioBuilder = scenario("performance test: addresses/summary/{address}")
    .forever(
      exec(getAddressesSummaryAddress)
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
