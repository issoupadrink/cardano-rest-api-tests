package com.cardano.rest.tests.simulations.performance.blocks

import java.util.Properties

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt
import scala.io.Source


class BlocksSummaryBlockhashSimulation extends Simulation {
  val conf = ConfigFactory.load()
  val host = conf.getString("host")

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
