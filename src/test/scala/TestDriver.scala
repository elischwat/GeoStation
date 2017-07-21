package GeoStation
import org.scalatest.Matchers._
import DataGrids.DataGridFactory
import java.nio.file.Paths
import Utilities.Point

/**
  * Created by elischwat on 7/10/17.
  */

//FUTURE: create in the ScalaTest framework
object TestDriver {
    def main(args: Array[String]) {
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
        println(station.listTemperatures)

        val testPt = Point(42.0, -94.0)
        val testPt2 = Point(40.0, -92.0)
        val testPtAvg = Point(41.573455, -93.620937)
        //TODO: Change tests to accomodate for receiving tuple
        println("Point data: " + testTempGrid(testPt))
        println("Point data: " + testTempGrid(testPt2))
        println("Average data: " + testTempGrid.averageData(testPtAvg, testPt2))
        println("Max data: Point: " + (testTempGrid.maxData))
        println("Min data: " + testTempGrid.minData() + "\n")

        //Test ElevGrid:
        println("Testing ElevGrid:")
        println("=================")
        val hdrPath2 = Paths.get("src/test/test_data/elev_gridfloat_n39w076/usgs_ned_13_n39w076_gridfloat.hdr")
        val fltPath = Paths.get("src/test/test_data/elev_gridfloat_n39w076/usgs_ned_13_n39w076_gridfloat.flt")
        val testElevGrid = DataGridFactory.getElevGrid(hdrPath2, fltPath)
        station.addElevGrid("testElev", testElevGrid)
        println(station.listElevations)


        println("NoData should return None" + Point)
        val testPt3 = Point(38.386625, -75.564854)
        val testPt4 = Point(38.52413, -75.43344)
        val testPt5 = Point(39.52413, -75.43344) //out of boundries
        println("Point data: " + testElevGrid(testPt3))
        println("Point data: " + testElevGrid(testPt4))
        println("Max data: Point: " + (testElevGrid.maxData))
        println("Min data: " + testElevGrid.minData())
        testElevGrid(testPt5) shouldBe None //bc testPt5 is out of bounds
    }


}

