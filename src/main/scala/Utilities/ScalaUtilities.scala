package Utilities

import java.nio.file.Path
import java.nio.file.Path
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.ByteBuffer
import java.io.FileInputStream
import scala.io.{BufferedSource, Source}
import scala.collection.mutable.Buffer
import scala.collection.mutable.ArrayBuffer

/**
  * Created by elischwat on 7/6/17.
  */
object ScalaUtilities {

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
