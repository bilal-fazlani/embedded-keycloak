package tech.bilal.embedded_keycloak.impl.data

import tech.bilal.embedded_keycloak.{KeycloakData, Settings}

import scala.language.implicitConversions

class DataFeeder(settings: Settings, keycloakData: KeycloakData) {

  val realmFeeder = new RealmFeeder(settings)

  def feed(): Unit = {
    implicit val bearerToken: BearerToken =
      BearerToken.getBearerToken(settings.port, keycloakData.adminUser)

    keycloakData.realms.foreach(realmFeeder.feedRealm)
  }
}