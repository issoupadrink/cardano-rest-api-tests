package com.cardano.rest.tests.simulations.performance.transactions

import java.util.Properties

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt
import scala.io.Source


class TxsSummaryTxSimulation extends Simulation {
  val conf = ConfigFactory.load()
  val host = conf.getString("host")

  val tx: String = "3c89f7d9ff6c06468e32fd916d153b033264f780e11fca7750cb85f56d4f31d0"

  val httpConf: HttpProtocolBuilder = http.baseUrl(host)
    .header("Accept", "application/json")

  def getTxsSummaryTx: ChainBuilder = {
    exec (
      http("Get txs/summary/{tx}")
        .get(String.format("txs/summary/%s", tx))
        .check(status.is(200))
    )
  }

  val scn: ScenarioBuilder = scenario("performance test: txs/summary/{tx}")
    .forever(
      exec(getTxsSummaryTx)
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
