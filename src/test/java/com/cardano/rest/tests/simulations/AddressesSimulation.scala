package com.cardano.rest.tests.simulations

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class AddressesSimulation extends Simulation {
  val nbRuns: String = System.getProperty("runs", "10")
  val nbUsers: Int = Integer.getInteger("users", 1)

  val httpConf: HttpProtocolBuilder = http.baseUrl("https://explorer.cardano-testnet.iohkdev.io/api/")
    .header("Accept", "application/json")
    .proxy(Proxy("localhost", 8888))

  val scn: ScenarioBuilder = scenario("Genesis Summary Scenario").repeat(nbRuns) {
    exec(
      http("Genesis Summary")
        .get("genesis/summary")
        .check(status.is(200))
    )
  }

  setUp(
    scn.inject(atOnceUsers(nbUsers))
  ).protocols(httpConf)
}
