/**
  * Created by elischwat on 7/6/17.
  */
package DataGrids

import java.io.FileInputStream
import java.nio.file.Path
import Utilities.{Point, ScalaUtilities}
import scala.io.Source

// TimeSeriesFactory
// implements the factory pattern to crate TimeSeries of different types
// Can handle files of different types (TODO)
object TimeSeriesFactory {

    // getPrecipGrid
    // Params:
    //
    // creates __ with __
    def getTimeSeries(dataPath: Path): TimeSeries = {
        val fileList = ScalaUtilities.getListOfFiles(dataPath.toString)
        val sortedList = fileList.sorted

        //create new TimeSeries
        val toReturn = new TimeSeries()

        //assumes files are in order, timewise, when sorted by name alphabetically
        for (file <- sortedList) {
            val extension = file.toString.takeRight(4)
            //if (extension == ".bil" || extension == ".flt" || )
        }
    }

    //get path to directory with all .bil files
    //create list of all file names, sort list
    //create time series with files in the order of the list


}
