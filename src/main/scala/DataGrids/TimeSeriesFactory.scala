/**
  * Created by elischwat on 7/6/17.
  */
package DataGrids

import java.io.FileInputStream
import java.nio.file.Path
import Utilities.{Point, ScalaUtilities}

import scala.io.Source

//Deprecated:

// DataGridFactory
// implements the factory pattern to crate DataGrids of different types
// can handle files of different types
// INITIALLY: requires parameter for type of DataGrid to create
// LATER: sense automatically what type of DataGrid to create
object TimeSeriesFactory {

    // getPrecipGrid
    // Params:
    //
    // creates __ with __
    def getTimeSeries(dataPath: Path): PrecipGrid = {
        val fileList = ScalaUtilities.getListOfFiles(dataPath.toString)
        val sortedList = fileList.sorted
        for (file <- sortedList) {
            val extension = file.toString.takeRight(4)
            if (extension == ".bil" || extension == ".flt" || 
        }
    }

    //get path to directory with all .bil files
    //create list of all file names, sort list
    //create time series with files in the order of the list


}
