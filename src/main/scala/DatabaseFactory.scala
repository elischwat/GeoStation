import java.io.File
import java.nio.file.{Path, Paths}

import DataGrids.DataGridFactory

/**
  * Created by elischwat on 7/20/17.
  */
object DatabaseFactory {

    /** buildDataBase
      * Grabs files from the data path and adds all datasets to the DAO. Data
      * is added to the proper map (temperatures, precipitations, elevations,
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
        var fileList = getListOfFiles(dataPath.toString + "temperatures")
        for (file <- fileList) {
            val name = file.toString.substring(0, file.toString.length - 4)
            val hdrFile = Paths.get(name + ".hdr").toFile
            if (file.toString.endsWith(".bil") && hdrFile.exists()) {
                val str = hdrFile.toString
                val name = str.substring(str.lastIndexOf("/")+1, str.lastIndexOf("."))
                database.temperatures += (name ->
                  DataGridFactory.getTempGrid(hdrFile.toPath, file.toPath))
            }
        }
    }

    def buildPrecipitations(dataPath: Path, database: Database): Unit = {
        //        var fileList = getListOfFiles(dataPath.toString + "precipitations")
        //        for (file <- fileList) {
        //            val name = file.toString.substring(0, file.toString.length - 4)
        //            val hdrFile = Paths.get(name + ".hdr").toFile
        //            if (file.toString.endsWith(".flt") && hdrFile.exists()) {
        //                val str = hdrFile.toString
        //                val name = str.substring(str.lastIndexOf("/")+1, str.lastIndexOf("."))
        //                database.precipitations += (name ->
        //                  DataGridFactory.getPrecipGrid(hdrFile.toPath, file.toPath))
        //            }
        //        }
    }

    def buildElevations(dataPath: Path, database: Database): Unit = {
        var fileList = getListOfFiles(dataPath.toString + "elevations")
        for (file <- fileList) {
            val name = file.toString.substring(0, file.toString.length - 4)
            val hdrFile = Paths.get(name + ".hdr").toFile
            if (file.toString.endsWith(".flt") && hdrFile.exists()) {
                val str = hdrFile.toString
                val name = str.substring(str.lastIndexOf("/")+1, str.lastIndexOf("."))
                database.elevations += (name ->
                  DataGridFactory.getElevGrid(hdrFile.toPath, file.toPath))
            }
        }
    }

    def getListOfFiles(dir: String):List[File] = {
        val d = new File(dir)
        if (d.exists && d.isDirectory) {
            d.listFiles.filter(_.isFile).toList
        } else {
            List[File]()
        }
    }

}
