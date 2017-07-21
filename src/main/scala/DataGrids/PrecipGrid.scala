/**
  * Created by elischwat on 7/6/17.
  */
package DataGrids

import Utilities.Point

class PrecipGrid(ulPP: Point, brPP: Point, nLatRowsP: Int, nLonColsP: Int,
                 lonDimP: Double, latDimP: Double, noDataP: Int, data_2DArrayP:
                 Array[Array[Float]]) extends DataGrid {

    protected var unit     = ""
    protected var detail   = ""
    protected var ulP      = new Point(0,0)
    protected var brP      = new Point(0, 0)
    protected var nLatRows = 0
    protected var nLonCols = 0
    protected var lonDim   = 0
    protected var latDim   = 0
    noData  = noDataP
    data_2DArray = Array.ofDim[Float](0,0)

}