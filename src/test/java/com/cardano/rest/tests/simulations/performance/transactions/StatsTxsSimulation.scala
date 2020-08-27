package com.cardano.rest.tests.simulations.performance.transactions

import java.util.Properties

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt
import scala.io.Source


class StatsTxsSimulation extends Simulation {
  val conf = ConfigFactory.load()
  val host = conf.getString("host")

  val httpConf: HttpProtocolBuilder = http.baseUrl(host)
    .header("Accept", "application/json")

  def getSupplyAda: ChainBuilder = {
    exec (
      http("Get stats/txs")
        .get("stats/txs")
        .check(status.is(200))
    )
  }

  val scn: ScenarioBuilder = scenario("performance test: stats/txs")
    .forever(
      exec(getSupplyAda)
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
