package com.sanoma.cda.geo.model

import scala.collection.JavaConverters._

trait JavaUtils {
  implicit class ConvertedMap[A,B](x: java.util.Map[A,B]) {
    def toScalaMap = x.asScala.toMap
  }
}

abstract class Record {
  val name: String
  val names: Map[String, String]
  val geoNameId: Int
}

trait Confidence {
  val confidence: Option[Int]
}

case class City(name: String, geoNameId: Int, names: Map[String, String], confidence: Option[Int]) extends Record with Confidence

object City extends JavaUtils {
  def apply(city: com.maxmind.geoip2.record.City): City =
    City(
      name       = city.getName,
      geoNameId  = city.getGeoNameId,
      names      = city.getNames.toScalaMap,
      confidence = Option(city.getConfidence()).map(_.toInt)
    )
}

case class Country(name: String, geoNameId: Int, names: Map[String, String], isoCode: String, confidence: Option[Int]) extends Record with Confidence

object Country extends JavaUtils {
  def apply(country: com.maxmind.geoip2.record.Country): Country =
    Country(
      name       = country.getName,
      geoNameId  = country.getGeoNameId,
      names      = country.getNames.toScalaMap,
      isoCode    = country.getIsoCode,
      confidence = Option(country.getConfidence).map(_.toInt)
    )
}

case class Continent(name: String, geoNameId: Int, names: Map[String, String], code: String)

object Continent extends JavaUtils {
  def apply(continent: com.maxmind.geoip2.record.Continent): Continent =
    Continent(
      name      = continent.getName,
      geoNameId = continent.getGeoNameId,
      names     = continent.getNames.toScalaMap,
      code      = continent.getCode
    )
}

case class Postal(code: String, confidence: Option[Int]) extends Confidence

object Postal extends JavaUtils {
  def apply(postal: com.maxmind.geoip2.record.Postal): Postal =
    Postal(
      code       = postal.getCode,
      confidence = Option(postal.getConfidence).map(_.toInt)
    )
}

case class Subdivision(name: String, geoNameId: Int, names: Map[String, String], isoCode: String, confidence: Option[Int]) extends Record with Confidence

object Subdivision extends JavaUtils {
  def apply(sub: com.maxmind.geoip2.record.Subdivision): Subdivision =
    Subdivision(
      name       = sub.getName,
      geoNameId  = sub.getGeoNameId,
      names      = sub.getNames.toScalaMap,
      isoCode    = sub.getIsoCode,
      confidence = Option(sub.getConfidence).map(_.toInt)
    )
}