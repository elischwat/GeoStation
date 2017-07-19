/**
  * Created by elischwat on 7/6/17.
  */
package GeoStation

import DataGrids.{DataGrid, ElevGrid, PrecipGrid, TempGrid}
import Utilities.Point
import scala.collection.mutable.Map

object GeoStation {

}

class GeoStation() {

    //make data fields private
    //make climate station
    val ulP: Point = new Point(0.0, 0.0)//upper left point
    val brP: Point = new Point(0.0, 0.0)//bottom right point

    //Maps of DataGrids defined by Keys (Names)
    var temperatures = Map[String, TempGrid]()
    var precipitations = Map[String, PrecipGrid]()
    var elevations = Map[String, ElevGrid]()

    //get temperatures list of grids
    def listTemperatures: String = {
        temperatures.keySet.toString()
    }

    //get precipitations list of grids
    def listPrecipitations: String = {
        precipitations.keySet.toString()
    }

    //get elevations list of grids
    def listElevations: String = {
        elevations.keySet.toString()
    }

    //addTempGrid
    //Param: newDataGrid - is added to appropriate private var based on type
    def addTempGrid(name: String, newGrid: TempGrid) {
        temperatures += (name -> newGrid)
    }

    def addPrecipGrid(name: String, newGrid: PrecipGrid) : Unit = {
        precipitations += (name -> newGrid)
    }

    def addElevGrid(name: String, newGrid: ElevGrid): Unit = {
        elevations += (name -> newGrid)
    }

    //HOW TO ITERATE OVER MAPS:
    //- for ((k,v) <- myMap) doSomeMutherfkin(k) and shit(v) _with(kend v)


}







