import GeoStation.GeoStation

import DataGrids.DataGridFactory
import java.nio.file.Paths
import Utilities.Point

/**
  * Created by elischwat on 7/10/17.
  */

//FUTURE: create in the ScalaTest framework
object TestDriver {
    def main(args: Array[String]): Unit = {

        //create station
        var station = new GeoStation
        //todo: test defualt GeoStation attributes

        //Test TempGrid:
        println("Testing TempGrid:")
        println("=================")
        val hdrPath1 = Paths.get("src/test/test_data/sample_data.hdr")
        val bilPath = Paths.get("src/test/test_data/sample_data.bil")
        val testTempGrid = DataGridFactory.getTempGrid(hdrPath1, bilPath)
        station.addTempGrid("testTemp", testTempGrid)

        val testPt = new Point(42.0, -94.0)
        val testPt2 = new Point(40.0, -92.0)
        val testPtAvg = new Point(41.573455, -93.620937)
        //TODO: Change tests to accomodate for receiving tuple
        println("Point data: " + testTempGrid.pointData(testPt))
        println("Point data: " + testTempGrid.pointData(testPt2))
        println("Average data: " + testTempGrid.averageData(testPtAvg, testPt2))
        println("Max data: Point: " + (testTempGrid.maxData))
        println("Min data: " + testTempGrid.minData())

        //Test ElevGrid:
        println("Testing ElevGrid:")
        println("=================")
        val hdrPath2 = Paths.get("src/test/test_data/elev_gridfloat_n39w076/usgs_ned_13_n39w076_gridfloat.hdr")
        val fltPath = Paths.get("src/test/test_data/elev_gridfloat_n39w076/usgs_ned_13_n39w076_gridfloat.flt")
        val testElevGrid = DataGridFactory.getElevGrid(hdrPath2, fltPath)
        station.addElevGrid("testElev", testElevGrid)
        val testPt3 = new Point(38.386625, -75.564854)
        val testPt4 = new Point(38.52413, -75.43344)
        val testPt5 = new Point(39.52413, -75.43344) //out of boundries
        println("Point data: " + testElevGrid.pointData(testPt3))
        println("Point data: " + testElevGrid.pointData(testPt4))
        //should throw error:
        println("Point data: " + testElevGrid.pointData(testPt5))
    }
}
