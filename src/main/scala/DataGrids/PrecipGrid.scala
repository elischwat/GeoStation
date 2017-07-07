/**
  * Created by elischwat on 7/6/17.
  */
package DataGrids

import Utilities.Point

class PrecipGrid extends DataGrid {

    protected var dataType = "Precipitation"
    protected var unit     = ""
    protected var ulP      = new Point(0.0, 0.0)
    protected var brP      = new Point(0.0, 0.0)
    protected var nLatRows = 0
    protected var nLonCols = 0
    protected var lonDim   = 0
    protected var latDim   = 0
    protected var noData  = -9999
    protected var data_2DArray = Array.ofDim[Float](0,0)

}
