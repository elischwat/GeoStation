package Utilities


import java.nio.ByteBuffer
import java.io.{File, FileInputStream}

/**
  * Created by elischwat on 7/6/17.
  */
object ScalaUtilities {

    def getListOfFiles(dir: String):List[File] = {
        val d = new File(dir)
        if (d.exists && d.isDirectory) {
            d.listFiles.filter(_.isFile).toList
        } else {
            List[File]()
        }
    }

    def getFloatArrayFromDataStream(dataStream: FileInputStream): Array[Float] = {
        val byteCount = dataStream.available //get # bytes in file
        val byteArray = Array.ofDim[Byte](byteCount) //create array of Bytes
        dataStream.read(byteArray) // read data into byte array
        dataStream.close // close connection to file
        val intArray = JavaUtilities.intArrayFromByteArray(byteArray)
        var floatArray = Array.ofDim[Float](intArray.length)
        for (i <- 0 until floatArray.length) {
            floatArray(i) = parseAsLittleEndianFloat(intArray(i))
        }
        return floatArray
    }

    def getMatrixFromArray(floatArr: Array[Float], nrowsP: Int, ncolsP: Int): Array[Array[Float]] = {
        var dataMatrix = Array.ofDim[Float](nrowsP, ncolsP)
        var cntr = 0
        //outerloop iterates through rows
        for (i <- 0 until nrowsP) {
            //innerloop iterates through columns
            for (j <- 0 until ncolsP) {
                dataMatrix(i)(j) = floatArr(cntr)
                cntr += 1
            }
        }
        return dataMatrix
    }

    private def parseAsLittleEndianFloat(i: Int): Float = {
        val bb = ByteBuffer.allocate(4).putInt(
            (i >>> 24) |
              ((i >> 8) & 0xFF00) |
              ((i << 8) & 0xFF0000) |
              (i << 24))
        bb.rewind()
        bb.getFloat()
    }

}
