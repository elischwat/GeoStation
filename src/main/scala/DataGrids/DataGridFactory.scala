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

import scala.io.Source.fromFile
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
        //FIELDS REQ'D to Constructor TempGrid
        //ulP      = new Point(0.0, 0.0)
        //brP      = new Point(0.0, 0.0)
        //nLatRows = 0
        //nLonCols = 0
        //lonDim   = 0
        //latDim   = 0
        //noData   = -9999
        //ARRAY OF DATA!

        //get info from headerlines to fill TempGrid fields
        val hdrDataString = Source.fromFile(hdrPath.toString).mkString //get entire hdr file into 1 string
        //FOR EACH Variable required from the hdrDataString,
            //use pattern 1 with correct word var name inserted
            //use pattern 2 to get the number only into a variable
        val pattern_grab_variable = """(?<=NROWS).*(?=\s)""".r //gets line w/ NROWS
        val pattern_grab_number = """[^\s]+""".r             //grabs number from that line
        var nrows = pattern_grab_variable.findFirstIn(hdrDataString)
        nrows = pattern_grab_number.findFirstIn(nrows.toString)

        //TWO WAYS TO HANDLE Option[String] object from findFirstIn function
        //give replacement String value in case of None
        val x = nrows getOrElse("")
        //OR
        //match Some/None possibilities to response:
        nrows match {
            case Some(_) => println("yes")
            case None => println("poo")
        }





        val dataStream = new FileInputStream(bilPath.toFile)
        val floatArray = ScalaUtilities.getFloatArrayFromDataStream(dataStream)
        val tempMatrix = ScalaUtilities.getMatrixFromArray(floatArray, nrows, ncols )
    }



    def getPrecipGrid: PrecipGrid = {

    }

    def getElevGrid: ElevGrid = {

    }

}
