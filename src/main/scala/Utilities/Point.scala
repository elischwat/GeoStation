/**
  * Created by elischwat on 7/6/17.
  */
package Utilities

// Point
// simple utility class for GeoStation to use
class Point(val latc: Double, val lonc: Double) {
    var lon: Double = lonc
    var lat: Double = latc

    def move(dLon: Double, dLat: Double) {
        lon = lon + dLon
        lat = lat + dLat
    }

    override def toString: String = '(' + lat.toString + ',' + lon.toString + ')'
}