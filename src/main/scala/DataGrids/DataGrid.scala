/**
 * Created by elischwat on 7/6/17.
 */
package DataGrids

import Utilities.Point

trait DataGrid {

    // data fields for all DataGrids
    protected var dataType: String
    protected var unit: String
    protected var ulP: Point
    protected var brP: Point
    protected var nLatRows: Int
    protected var nLonCols: Int
    protected var lonDim: Double
    protected var latDim: Double
    protected var noData: Int
    protected var data_2DArray: Array[Array[Float]]

    // methods for all DataGrids (unimplemented methods will be automatically abstract)
    def pointData(local: Point): Option[Double] = {
        val rowNum = Math.floor((ulP.lat - local.lat) / latDim).toInt
        val colNum = Math.floor((local.lon - ulP.lon) / lonDim).toInt
        if (this.data_2DArray(rowNum)(colNum) == -9999) {
            return None
        } else {
            return Some(data_2DArray(rowNum)(colNum).toDouble)
        }
    }

    def averageData(local: Point, local2: Point): Option[Double] = {
        //if 2 points are same, reuse other function
        if(local.lat == local2.lat && local.lon == local2.lon) {
            return pointData(local)
        } else {
            var dataPtCntr = 0  //keeps track of valid data points used
            var tempSum = 0.0     //sums valid data points
            val startRow =  Math.floor((ulP.lat - local.lat) / latDim).toInt
            val endRow =    Math.floor((ulP.lat - local2.lat) / latDim).toInt
            val startCol =  Math.floor((local.lon - ulP.lon) / lonDim).toInt
            val endCol =    Math.floor((local2.lon - ulP.lon) / lonDim).toInt
            for (i <- startRow to endRow) { //sum valid data points
                for (j <- startCol to endCol) {
                    if (data_2DArray(i)(j) != noData) {
                        tempSum += this.data_2DArray(i)(j)
                        dataPtCntr += 1
                    }
                }
            }
            if (dataPtCntr > 0) {
                return Some((tempSum / dataPtCntr))
            } else {
                return None
            }
        }
    }

}
