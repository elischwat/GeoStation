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


}







