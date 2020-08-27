package com.cardano.rest.tests.simulations.performance.addresses


import java.util.Properties

import com.typesafe.config.{Config, ConfigFactory}
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import io.restassured.internal.common.assertion.AssertionSupport.properties

import scala.concurrent.duration.DurationInt
import scala.io.Source


class AddressesSummaryAddressSimulation extends Simulation {

  var properties : Properties = null
  val url = getClass.getResource("/config.properties")
  if (url != null) {
    val source = Source.fromURL(url)

    properties = new Properties()
    properties.load(source.bufferedReader())
  }

  val host: String = properties.getProperty("host")
  val pauseBetweenTests: Int = properties.getProperty("pauseBetweenTests").toInt
  val pauseBetweenRequests: Int = properties.getProperty("pauseBetweenRequests").toInt
  val startingUsers: Int = properties.getProperty("startingUsers").toInt
  val maximumUsers: Int = properties.getProperty("maximumUsers").toInt
  val timeFrameToIncreaseUsers: Int = properties.getProperty("timeFrameToIncreaseUsers").toInt
  val maxTestDuration: Int = properties.getProperty("maxTestDuration").toInt

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
        .pause(pauseBetweenRequests seconds)
    )

  setUp(
    scn.inject(
      nothingFor(pauseBetweenTests seconds),
      atOnceUsers(startingUsers),
      rampUsers(maximumUsers) during (timeFrameToIncreaseUsers seconds)
    ).protocols(httpConf)
  ).maxDuration(maxTestDuration seconds)
}
