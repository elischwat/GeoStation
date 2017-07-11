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

        var station = new GeoStation

        val hdrPath = Paths.get("src/test/test_data/sample_data.hdr")
        val bilPath = Paths.get("src/test/test_data/sample_data.bil")

        val test = DataGridFactory.getTempGrid(hdrPath, bilPath)

        station.addTempGrid("testTemp", test)

        val testPt = new Point(42.0, -94.0)
        val testPt2 = new Point(40.0, -92.0)
        val testPtAvg = new Point(41.573455, -93.620937)

        val maxPt = test.maxData()

        println(maxPt.toString)

        //TODO: Change tests to accomodate for receiving tuple

        println("Point data: " + test.pointData(testPt))
        println("Point data: " + test.pointData(testPt2))
        println("Average data: " + test.averageData(testPtAvg, testPt2))
//        println("Max data: Point: " + (test.maxData)
//        println("Min data: " + test.minData())

    }
}
