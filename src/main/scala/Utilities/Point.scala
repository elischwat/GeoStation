/**
  * Created by elischwat on 7/6/17.
  */
package Utilities

// Point
// simple utility class for GeoStation to use
class Point(val lonc: Double, val latc: Double) {
    var lon: Double = lonc
    var lat: Double = latc

    def move(dLon: Double, dLat: Double) {
        lon = lon + dLon
        lat = lat + dLat
    }
}