package GeoStation
import java.io.File
import java.nio.file.{Path, Paths}
import DataGrids.DataGridFactory
import Utilities.ScalaUtilities

/** DatabaseFactory
  * Implements the factory pattern to create Database object.
  * Builds database from file structure that holds temperature, precipitation,
  * and elevation data.
  */
object DatabaseFactory {

    /** buildDataBase
      * Grabs files from the data path and adds all datasets to the DAO. Data
      * is added to the proper list (temperatures, precipitations, elevations,
      * etc) based on the sub-directory within the dataPath.
      * @param dataPath - path to the data directory. this directory should
      *                 contain sub directories, one for each map data member of
      *                 the DAO class (temps, precips, elevs, etc)
      */
    def getDatabase(dataPath: Path): Database = {
        val toReturn = new Database
        buildTemperatures(dataPath, toReturn)
        buildPrecipitations(dataPath, toReturn)
        buildElevations(dataPath, toReturn)
        return toReturn
    }

    def getDatabase(): Unit = {
        getDatabase(Paths.get("data/"))
    }

    def buildTemperatures(dataPath: Path, database: Database): Unit = {
        var fileList = List[File]()
        dataPath.toString takeRight 1 match {
            case "/" => { fileList = ScalaUtilities.getListOfFiles(dataPath.toString + "temperatures")}
            case _   => { fileList = ScalaUtilities.getListOfFiles(dataPath.toString + "/temperatures")}
        }
        for (file <- fileList) {
            val name = file.toString.substring(0, file.toString.length - 4)
            val hdrFile = Paths.get(name + ".hdr").toFile
            if (file.toString.endsWith(".bil") && hdrFile.exists()) {
                val str = hdrFile.toString
                val name = str.substring(str.lastIndexOf("/")+1, str.lastIndexOf("."))
                val tmp = DataGridFactory.getTempGrid(hdrFile.toPath, file.toPath)
                database.temperatures += tmp
                database.tempGrids += tmp.getDetail
            }
        }
    }

    def buildPrecipitations(dataPath: Path, database: Database): Unit = {
        var fileList = List[File]()
        dataPath.toString takeRight 1 match {
            case "/" => { fileList = ScalaUtilities.getListOfFiles(dataPath.toString + "precipitations") }
            case _   => { fileList = ScalaUtilities.getListOfFiles(dataPath.toString + "/precipitations") }
        }
        for (file <- fileList) {
            val name = file.toString.substring(0, file.toString.length - 4)
            val hdrFile = Paths.get(name + ".hdr").toFile
            if (file.toString.endsWith(".bil") && hdrFile.exists()) {
                val str = hdrFile.toString
                val name = str.substring(str.lastIndexOf("/")+1, str.lastIndexOf("."))
                val tmp = DataGridFactory.getPrecipGrid(hdrFile.toPath, file.toPath)
                database.precipitations += tmp
                database.precipGrids += tmp.getDetail
            }
        }
    }

    def buildElevations(dataPath: Path, database: Database): Unit = {
        var fileList = List[File]()
        dataPath.toString takeRight 1 match {
            case "/" => { fileList = ScalaUtilities.getListOfFiles(dataPath.toString + "elevations") }
            case _   => { fileList = ScalaUtilities.getListOfFiles(dataPath.toString + "/elevations") }
        }
        for (file <- fileList) {
            val name = file.toString.substring(0, file.toString.length - 4)
            val hdrFile = Paths.get(name + ".hdr").toFile
            if (file.toString.endsWith(".flt") && hdrFile.exists()) {
                val str = hdrFile.toString
                val name = str.substring(str.lastIndexOf("/")+1, str.lastIndexOf("."))
                val tmp = DataGridFactory.getElevGrid(hdrFile.toPath, file.toPath)
                database.elevations += tmp
                database.elevGrids += tmp.getDetail
            }
        }
    }

}
