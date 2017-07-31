/**
 * Created by Eli Schwat on 7/6/17.
 */
package DataGrids

import Utilities.Point

trait DataGrid {

    //TODO: all data accessors must check if request is within bounds

    // data fields for all DataGrids
    protected var unit: String
    protected var detail: String
    protected var ulP: Point
    protected var brP: Point
    protected var nLatRows: Int
    protected var nLonCols: Int
    protected var lonDim: Double
    protected var latDim: Double
    protected var noData: Int = -9999
    protected var data_2DArray = Array.ofDim[Float](0,0)


    //Object Accessor Methods
    def getUnit: String = this.unit
    def getDetail: String = this.detail
    def getUlp: Point = this.ulP
    def getBrp: Point = this.brP
    def getNLatRows: Int = this.nLatRows
    def getNLonCols: Int = this.nLonCols
    def getLonDim: Double = this.lonDim
    def getLatDim: Double = this.latDim
    def getNoData: Int = this.noData

    //Object Mutator Methods
    def setunit(unitP: String) { this.unit = unitP}
    def setdetail(detailP: String) { this.detail = detailP}

    //Data Accessor Methods

    def apply(lat: Double, lon: Double): Option[Float] = {
        if (!pointIsInBounds(Point(lat, lon))) return None
        val colNum = Math.floor((lon - ulP.lon) / lonDim).toInt
        val rowNum = Math.floor((ulP.lat - lat) / latDim).toInt
        Some(data_2DArray(rowNum)(colNum))
    }

    def apply(pnt: Point): Option[Float]  = {
        if (!pointIsInBounds(pnt)) return None
        val colNum = Math.floor((pnt.lon - ulP.lon) / lonDim).toInt
        val rowNum = Math.floor((ulP.lat - pnt.lat) / latDim).toInt
        Some(data_2DArray(rowNum)(colNum))
    }

    //deprecated -- access data directly and may throw exception
    def pointData(point: Point): Option[Float] = {
        //TODO:remove this and make the functionality better
        val colNum = Math.floor((point.lon - ulP.lon) / lonDim).toInt
        val rowNum = Math.floor((ulP.lat - point.lat) / latDim).toInt
        val noDataMatch = this.noData
        this.data_2DArray(rowNum)(colNum) match {
            case noDataMatch => None
            case _ => Some(this.data_2DArray(rowNum)(colNum))
        }
    }


    //TODO: fix to handle edge cases better:
    //TODO: how to handle if some pts are out of range?
    def averageData(local: Point, local2: Point): Option[Float] = {
        //if 2 points are same, reuse other function
        if(local.lat == local2.lat && local.lon == local2.lon)
            return pointData(local)

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
        if (dataPtCntr > 0) Some((tempSum / dataPtCntr).toFloat)
        else None
    }

    //TODO: RETURN TUPLE2 of Point and Float - point must be adjusted from (i)(j) to (lat)(long)
    def maxData(): Option[(Point, Float)] = {
        var max = this.data_2DArray(0)(0)
        var maxPt = Point(0, 0)
        //TODO: this could become prohibitively slow - better way to find max pt?
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
        if(max == this.noData) None
        else Some((maxPt, max))
    }

    //TODO: RETURN TUPLE2 of Point and Float - point must be adjusted from (i)(j) to (lat)(long)
    def minData(): Option[(Point, Float)] = {
        var min = this.maxData()
        if (min.isEmpty) return None //if maxData is empty so is minData
        var minPt = min.get._1
        var minValue = min.get._2
        //outerloop iterates through rows
        for (i <- 0 until this.nLatRows) {
            //innerloop iterates through columns
            for (j <- 0 until nLonCols) {
                if (minValue > this.data_2DArray(i)(j) && (this.data_2DArray(i)(j) != this.noData)) {
                    minValue = this.data_2DArray(i)(j)
                    minPt.lat = i
                    minPt.lon = j
                }
            }
        }
        Some((minPt, minValue)) //if there's only one pixel of data, min==max
    }

    //HELPER FUCNTION
    def pointIsInBounds(pnt: Point): Boolean = {
        pnt.lon > this.ulP.lon && pnt.lat < ulP.lat &&
          pnt.lon < this.brP.lon && pnt.lat > brP.lat
    }

}
