/**
  * Created by elischwat on 7/6/17.
  */
package DataGrids

import java.nio.file.Path
import java.io.FileInputStream
import Utilities.ScalaUtilities
import scala.io.Source
import Utilities.Point

//Deprecated:
import java.nio.file.Files
import java.nio.file.Paths  //gets Path from string (EX: "val dat = Paths.get("str.txt")
import java.nio.ByteBuffer
import scala.io.Source.fromFile
import scala.io.BufferedSource
import scala.collection.mutable.Buffer
import scala.collection.mutable.ArrayBuffer

// DataGridFactory
// implements the factory pattern to crate DataGrids of different types
// can handle files of different types
// INITIALLY: requires parameter for type of DataGrid to create
// LATER: sense automatically what type of DataGrid to create
object DataGridFactory {

    // getPrecipGrid
    // Params:
    //
    // creates __ with __
//    def getPrecipGrid: PrecipGrid = {
//
//    }

    // getElevGrid
    // Params:
    //
    // creates __ with __
//    def getElevGrid: ElevGrid = {
//
//    }


    // getTempGrid
    // Params: hdrPath - path to .hdr file
    //         bilPath - path to .bil file
    // creates temp grid with .hdr/.bil pair
    // Uses TempGrid default contructor TempGrid(ulPP: Point, brPP: Point, nLatRowsP: Int, nLonColsP: Int, lonDimP: Double, latDimP: Double, noDataP: Int, data_2DArrayP: Array[Array[Float]])
    def getTempGrid(hdrPath: Path, bilPath: Path): TempGrid = {

        //  hdrString = String containing entirety of file @ hdrPath
        val hdrString = Source.fromFile(hdrPath.toString).mkString //get entire hdr file into 1 string

        //FILL VARS FROM .HDR FILE
        //parsing commands specific to .hdr file
        //FUTURE: map data from text into flexible variable names for tempGrid
        val nLatRows = GetVariableFromText("NROWS", hdrString).toInt
        val nLonCols = GetVariableFromText("NCOLS", hdrString).toInt
        val lonDim   = GetVariableFromText("XDIM", hdrString).toDouble
        val latDim = GetVariableFromText("YDIM", hdrString).toDouble
        val noData   = GetVariableFromText("NODATA", hdrString).toInt
        val ulx = GetVariableFromText("ULXMAP", hdrString).toDouble
        val uly = GetVariableFromText("ULYMAP", hdrString).toDouble
        val ulp = new Point(uly.toDouble, ulx.toDouble)
        val brP = new Point(ulp.lat + (nLatRows * latDim), ulp.lon + (nLonCols * lonDim))

        //FILL ARRAY FROM .BIL FILE
        val dataStream = new FileInputStream(bilPath.toFile)
        val floatArray = ScalaUtilities.getFloatArrayFromDataStream(dataStream)
        val tempMatrix = ScalaUtilities.getMatrixFromArray(floatArray, nLatRows, nLonCols)

        //create and return generated TempGrid
        val toReturn = new TempGrid(ulp, brP, nLatRows, nLonCols, lonDim,
            latDim, noData, tempMatrix)
        return toReturn

    }






    //HELPER FUNCTIONS
    //getNumFromVarName
    //gets the value from a text line containing both var name and value
    //returns String - leaves data typing to the caller
    def GetVariableFromText(varName: String, txt: String): String = {
        val pattern_grab_line = ("""(?<=""" + varName + """).*(?=\s)""").r //gets line w/ "varName"
        val pattern_grab_num = """[^\s]+""".r             //grabs number from that line
        val varLine: String = pattern_grab_line.findFirstIn(txt).getOrElse("")
        val value: String = pattern_grab_num.findFirstIn(varLine).getOrElse("")
        return value
    }


}
