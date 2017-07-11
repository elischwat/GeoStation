/**
 * Created by elischwat on 7/6/17.
 */
package DataGrids

import Utilities.Point

trait DataGrid {

    // data fields for all DataGrids
    protected var unit: String
    protected var detail: String
    protected var ulP: Point
    protected var brP: Point
    protected var nLatRows: Int
    protected var nLonCols: Int
    protected var lonDim: Double
    protected var latDim: Double
    protected var noData: Int
    protected var data_2DArray: Array[Array[Float]]

    //Object Accessor Methods
    def getunit: String = this.unit
    def getdetail: String = this.detail
    def getulP: Point = this.ulP
    def getbrP: Point = this.brP
    def getnLatRows: Int = this.nLatRows
    def getnLonCols: Int = this.nLonCols
    def getlonDim: Double = this.lonDim
    def getlatDim: Double = this.latDim
    def getnoData: Int = this.noData

    //Object Mutator Methods
    def setunit(unitP: String) { this.unit = unitP}
    def setdetail(detailP: String) { this.detail = detailP}

    //Data Accessor Methods
    def pointData(point: Point): Option[Float] = {
        val colNum = Math.floor((point.lon - ulP.lon) / lonDim).toInt
        val rowNum = Math.floor((ulP.lat - point.lat) / latDim).toInt
        if (this.data_2DArray(rowNum)(colNum) == -9999) {
            return None
        } else {
            return Some(data_2DArray(rowNum)(colNum).toFloat)
        }
    }

    def averageData(local: Point, local2: Point): Option[Float] = {
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
                return Some((tempSum / dataPtCntr).toFloat)
            } else {
                return None
            }
        }
    }

    //TODO: RETURN TUPLE2 of Point and Float - point must be adjusted from (i)(j) to (lat)(long)
    def maxData(): Option[Tuple2[Point, Float]] = {
        var max = this.data_2DArray(0)(0)
        var maxPt = new Point(0.0, 0.0)
        //outerloop iterates through rows
        for (i <- 0 until this.nLatRows) {
            //innerloop iterates through columns
            for (j <- 0 until nLonCols) {
                if (max < this.data_2DArray(i)(j)) {
                    max = this.data_2DArray(i)(j)
                    maxPt.lat = i
                    maxPt.lon = j
                }
            }
        }
        if (max == this.noData) {
            return None
        }
        else {
            return Some((maxPt, max))
        }



    }

    //TODO: RETURN TUPLE2 of Point and Float - point must be adjusted from (i)(j) to (lat)(long)
    def minData(): Option[Tuple2[Point, Float]] = {
        var min = this.data_2DArray(0)(0)
        var minPt = new Point(0.0, 0.0)

        //outerloop iterates through rows
        for (i <- 0 until this.nLatRows) {
            //innerloop iterates through columns
            for (j <- 0 until nLonCols) {
                if (min > this.data_2DArray(i)(j) && this.data_2DArray(i)(j) != this.noData) {
                    min = this.data_2DArray(i)(j)
                    minPt.lat = i
                    minPt.lon = j
                }
            }
        }
        if (min == -9999) {
            return None
        }
        else {
            return Some((minPt, min))
        }
    }

}
