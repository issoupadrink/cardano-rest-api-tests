package com.cardano.rest.tests.simulations.performance.addresses

import java.util.Properties

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt
import scala.io.Source


class AddressesSummaryAddressSimulation extends Simulation {
  val conf = ConfigFactory.load()
  val host = conf.getString("host")

  // Set-up test data
  val address = "Ae2tdPwUPEZK72eZZqulakkhaUfTCcoaGepvQP718aYBczw5uZmp47h1k14"

  // Set-up the URL
  val httpConf: HttpProtocolBuilder = http.baseUrl(host)
    .header("Accept", "application/json")

  // Call the URL and set assertions
  def getAddressesSummaryAddress: ChainBuilder = {
    exec (
      http("Get addresses/summary/{address}")
        .get(String.format("addresses/summary/%s", address))
        .check(status.is(200))
    )
  }

  // Create the scenario
  val scn: ScenarioBuilder = scenario("performance test: addresses/summary/{address}")
    .forever(
      exec(getAddressesSummaryAddress)
        .pause(5 seconds)
    )

  // Run the test
  setUp(
    scn.inject(
      nothingFor(5 seconds),
      atOnceUsers(1),
      rampUsers(5) during (15 second)
    ).protocols(httpConf)
  ).maxDuration(30 seconds)
}
