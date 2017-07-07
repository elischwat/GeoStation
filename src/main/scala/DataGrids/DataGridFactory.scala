/**
  * Created by elischwat on 7/6/17.
  */
package DataGrids

import java.nio.file.Path
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.ByteBuffer
import java.io.FileInputStream

import Utilities.ScalaUtilities

import scala.io.{BufferedSource, Source}
import scala.collection.mutable.Buffer
import scala.collection.mutable.ArrayBuffer

// DataGridFactory
// implements the factory pattern to crate DataGrids of different types
// can handle files of different types
// INITIALLY: requires parameter for type of DataGrid to create
// LATER: sense automatically what type of DataGrid to create
object DataGridFactory {

    // getTempGrid
    // Params: hdrPath - path to .hdr file
    //         bilPath - path to .bil file
    // creates temp grid with .hdr/.bil pair
    def getTempGrid(hdrPath: Path, bilPath: Path): TempGrid = {
        val headerBuffer = Source.fromFile(hdrPath.toString)
        val headerLines = headerBuffer.getLines.toBuffer
        headerBuffer.close

        //get info from headerlines to fill TempGrid fields
            //use regex or something that can adjust based on diff .hdr files
        //get data fields with regex

        //FIELDS REQ'D to Constructor TempGrid
        //ulP      = new Point(0.0, 0.0)
        //brP      = new Point(0.0, 0.0)
        //nLatRows = 0
        //nLonCols = 0
        //lonDim   = 0
        //latDim   = 0
        //noData   = -9999


        val dataStream = new FileInputStream(bilPath.toFile)
        val floatArray = ScalaUtilities.getFloatArrayFromDataStream(dataStream)
        val tempMatrix = ScalaUtilities.getMatrixFromArray(floatArray, nrows, ncols )



    }



    def getPrecipGrid: PrecipGrid = {

    }

    def getElevGrid: ElevGrid = {

    }

}
