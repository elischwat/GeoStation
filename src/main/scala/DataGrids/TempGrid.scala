/**
  * Created by elischwat on 7/6/17.
  */
package DataGrids

import Utilities.Point

class TempGrid(ulPP: Point, brPP: Point, nLatRowsP: Int, nLonColsP: Int,
               lonDimP: Double, latDimP: Double, noDataP: Int, data_2DArrayP:
               Array[Array[Float]]) extends DataGrid {

    protected var unit     = "Celcius"      //default value
    protected var detail   = ""            //default value
    protected var ulP      = ulPP
    protected var brP      = brPP
    protected var nLatRows = nLatRowsP
    protected var nLonCols = nLonColsP
    protected var lonDim   = lonDimP
    protected var latDim   = latDimP
    protected var noData = noDataP
    data_2DArray = data_2DArrayP


}
