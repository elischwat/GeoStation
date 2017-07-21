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

    /** getElevGrid
      * Params: hdrPath - path to .hdr file
      * Params: fltPath - path to .flt file
      * creates ElevGrid with __, with __, with .hdr/.flt file pair of the GridFloat file format
      * Uses to ElevGrid default constructor
      * .flt file can be read the same as a .bil, interpret as a stream of 32bit Floats, little-endian
      */
    def getElevGrid(hdrPath: Path, fltPath: Path): ElevGrid = {

        val hdrString = Source.fromFile(hdrPath.toString).mkString
        //FILL VARS FROM .HDR FILE
        //parsing commands specific to .hdr file for GridFloat file format
        val nLatRows = GetVariableFromText("nrows", hdrString).toInt
        val nLonCols = GetVariableFromText("ncols", hdrString).toInt
        val llx = GetVariableFromText("xllcorner", hdrString).toFloat
        val lly = GetVariableFromText("yllcorner", hdrString).toFloat
        //debug: toFloat may not work b/c hdr contains textual  scientific notation:
        val cellSize = GetVariableFromText("cellsize", hdrString).toFloat
        val lonDim   = cellSize
        val latDim = cellSize
        val noData   = GetVariableFromText("NODATA_value", hdrString).toInt

        val ulx = llx
        //debug: check if this math is right
        val uly = lly + (cellSize * nLatRows)

        val ulp = Point(uly.toFloat, ulx.toFloat)
        val brP = Point(ulp.lat - (nLatRows * latDim), ulp.lon + (nLonCols * lonDim))

        //FILL ARRAY FROM .BIL FILE
        val dataStream = new FileInputStream(fltPath.toFile)
        val floatArray = ScalaUtilities.getFloatArrayFromDataStream(dataStream)
        val tempMatrix = ScalaUtilities.getMatrixFromArray(floatArray, nLatRows, nLonCols)

        //create and return generated TempGrid
        val toReturn = new ElevGrid(ulp, brP, nLatRows, nLonCols, lonDim,
            latDim, noData, tempMatrix)
        return toReturn
    }


    // getTempGrid
    // Params: hdrPath - path to .hdr file
    //         bilPath - path to .bil file
    // creates temp grid with .hdr/.bil pair
    // Uses TempGrid default contructor TempGrid(ulPP: Point, brPP: Point, nLatRowsP: Int, nLonColsP:
    //      Int, lonDimP: Float, latDimP: Float, noDataP: Int, data_2DArrayP: Array[Array[Float]])
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
        val brP = new Point(ulp.lat - (nLatRows * latDim), ulp.lon + (nLonCols * lonDim))
        //FILL ARRAY FROM .BIL FILE
        val dataStream = new FileInputStream(bilPath.toFile)
        val floatArray = ScalaUtilities.getFloatArrayFromDataStream(dataStream)
        val tempMatrix = ScalaUtilities.getMatrixFromArray(floatArray, nLatRows, nLonCols)

        //create and return generated TempGrid
        val toReturn = new TempGrid(ulp, brP, nLatRows, nLonCols, lonDim,
            latDim, noData, tempMatrix)
        return toReturn

    }


    /**getNumFromVarName
      * gets the value from a text line containing both var name and value
      * Regex pattern relies upon the fact that there are two tokens on the line,
      * the first token is composed of letters and the two tokens are not equal
      * returns String - leaves data typing to the caller
      * @param varName -
      * @param txt -
      * @return value -
      */
    def GetVariableFromText(varName: String, txt: String): String = {
        val pattern_grab_line = ("""(?<=""" + varName + """).*(?=\s)""").r //gets line w/ "varName"
        val pattern_grab_num = """[^\s]+""".r             //grabs number from that line
        val varLine: String = pattern_grab_line.findFirstIn(txt).getOrElse("")
        val value: String = pattern_grab_num.findFirstIn(varLine).getOrElse("")
        return value
    }


}
