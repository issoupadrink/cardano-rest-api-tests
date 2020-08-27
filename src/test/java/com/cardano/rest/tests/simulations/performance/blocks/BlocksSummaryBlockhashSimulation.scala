package com.cardano.rest.tests.simulations.performance.blocks

import java.util.Properties

import com.typesafe.config.{Config, ConfigFactory}
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt
import scala.io.Source


class BlocksSummaryBlockhashSimulation extends Simulation {

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


  val blockhash = "534097d96a5ef35601ac5b5ea65d168858553cda7edd3f0e004c4129ee6c3172"

  val httpConf: HttpProtocolBuilder = http.baseUrl(host)
    .header("Accept", "application/json")

  def getBlocksSummaryBlockhashTotal: ChainBuilder = {
    exec (
      http("Get blocks/summary/{blockhash}")
        .get(String.format("blocks/summary/%s", blockhash))
        .check(status.is(200))
    )
  }

  val scn: ScenarioBuilder = scenario("performance test: blocks/summary/{blockhash}")
    .forever(
      exec(getBlocksSummaryBlockhashTotal)
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
