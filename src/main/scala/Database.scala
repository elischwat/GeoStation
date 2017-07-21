import DataGrids.DataGrid
//import java.nio.file.Files
import Utilities.Point

import scala.collection.mutable.Map

/** DataAccessor.scala
  * Interface object used to access data for the GeoStation class.
  * Abstracts GeoStation and associated functionality from pertinent data
  * structures.
  *
  * All
  *
  * Created by elischwat on 7/19/17.
  */

//Data
class Database {

    //make data fields private
    //make climate station
    val ulP: Point = Point(0.0, 0.0)//upper left extent of all data
    val brP: Point = Point(0.0, 0.0)//bottom right extent of all data

    //TODO: MUST rethick organizing by map -- getTemp functions r overly complicated
    //Maps of DataGrids defined by Keys (Names)
    var temperatures = Map[String, DataGrid]()
    var precipitations = Map[String, DataGrid]()
    var elevations = Map[String, DataGrid]()


    def getTemp(pnt: Point): Option[Float] = {
        var x: Float = 0
        var net: Float = 0

        for (i <- temperatures.values) {
            val temp = i.pointData(pnt)
            temp match {
                case None =>
                case Some[Float] => net += temp
        }

    }

    def getPrecip(point: Point): Option[Float] = {

    }

    def getElev(local: Point, local2: Point): Option[Float] = {

    }

    def maxTemp(): Option[(Point, Float)] = {

    }

    def maxPrecip(): Option[(Point, Float)] = {

    }

    def maxElev(): Option[(Point, Float)] = {

    }
}
