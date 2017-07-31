package GeoStation
import java.nio.file.Paths

import DataGrids.DataGridFactory
import Utilities.Point
import org.scalatest.Matchers._

/**
  * Created by elischwat on 7/10/17.
  */

//FUTURE: create in the ScalaTest framework
object TestDriver_Database {
    def main(args: Array[String]) {

        //create database
        val db = DatabaseFactory.getDatabase(Paths.get("/Users/elischwat/Documents/GeoStation/src/test/test_data"))

        println("Num temp grids: " + db.tempGrids.length)
        db.tempGrids.length shouldEqual db.temperatures.length
        println("Num precip grids: " + db.precipGrids.length)
        db.precipGrids.length shouldEqual db.precipitations.length
        println("Num elev grids: " + db.elevGrids.length)
        db.elevGrids.length shouldEqual db.elevations.length

        println("\nElevation grids:")
        db.listElevGrids
        println("\nPrecipitation grids:")
        db.listPrecipGrids
        println("\nTemperature grids:")
        db.listTempGrids
    }


}

