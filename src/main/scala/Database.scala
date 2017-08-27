package GeoStation
import DataGrids.{DataGrid, TempGrid, PrecipGrid, ElevGrid}
import scala.collection.mutable.ListBuffer
//import java.nio.file.Files
import Utilities.Point

import scala.collection.mutable.Map

/** Database.scala
  * Database object for GeoStation. Holds all DataGrid and TimeSeries objects.
  * Accessed by DAO.
  *
  * Should be deprecated for superior (actual) Database.
  *
  * Created by elischwat on 7/19/17.
  */
class Database {

    //make data fields private
    //make climate station
    val ulP: Point = Point(0.0, 0.0)//upper left extent of all data
    val brP: Point = Point(0.0, 0.0)//bottom right extent of all data

    //TODO: MUST rethick organizing by map -- getTemp functions r overly complicated
    //Maps of DataGrids defined by Keys (Names)
    var temperatures = ListBuffer[TempGrid]()
    var precipitations = ListBuffer[PrecipGrid]()
    var elevations = ListBuffer[ElevGrid]()

    var tempGrids = ListBuffer[String]()
    var precipGrids = ListBuffer[String]()
    var elevGrids = ListBuffer[String]()


    /**
      * list temperatures in an ordered manner, the primary data grid first
      */
    def listTempGrids: Unit = {
        temperatures foreach {grid => println(grid.getDetail)}
    }

    /**
      * list precipitations in an ordered manner, the primary data grid first
      */
    def listPrecipGrids: Unit = {
        precipitations foreach {grid => println(grid.getDetail)}
    }

    /**
      * list elevations in an ordered manner, the primary data grid first
      */
    def listElevGrids: Unit = {
        elevations foreach {grid => println(grid.getDetail)}
    }

    /**
      * Returns temperature at the primary temp grid (temperatures[0])
      * @param pnt
      * @return
      */
    def getTemp(pnt: Point): Option[Float] = {
        if (temperatures.size  == 0) return None
        temperatures(0).pointData(pnt)
    }

    /**
      * Returns precipitation at the primary precip grid (precipitations[0])
      * @param pnt
      * @return
      */
    def getPrecip(pnt: Point): Option[Float] = {
        if (precipitations.size == 0) return None
        precipitations(0).pointData(pnt)
    }

    /**
      * Returns elevation at the primary elev grid (elevations[0])
      * @param pnt
      * @return
      */
    def getElev(pnt: Point): Option[Float] = {
        if (elevations.size == 0) return None
        elevations(0).pointData(pnt)
    }

    def getAvgTemp(pnt: Point): Option[Float] = {
        //TODO: FIGURE OUT ALGORITHM!!
        None
    }

    def getAvgPrecip(pnt: Point): Option[Float] = {
        //TODO: FIGURE OUT ALGORITHM!!
        None
    }

    def getAvgElev(pnt: Point): Option[Float] = {
        //TODO: FIGURE OUT ALGORITHM!!
        None
    }


    /**
      * Returns max temp from the primary temp grid (temperatures[0])
      * @return Option of Tuple2 containing Point and Value of max temp
      */
    def maxTemp(): Option[(Point, Float)] = {
        if (temperatures.size  == 0) return None
        temperatures(0).maxData()
    }

    /**
      * Returns max precip from the primary precip grid (precipitations[0])
      * @return Option of Tuple2 containing Point and Value of max precip
      */
    def maxPrecip(): Option[(Point, Float)] = {
        if (precipitations.size  == 0) return None
        precipitations(0).maxData()
    }

    /**
      * Returns max elev from the primary elev grid (elevations[0])
      * @return Option of Tuple2 containing Point and Value of max elev
      */
    def maxElev(): Option[(Point, Float)] = {
        if (elevations.size  == 0) return None
        elevations(0).maxData()
    }
}
