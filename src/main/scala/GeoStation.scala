/**
  * Created by elischwat on 7/6/17.
  */
package GeoStation

import DataGrids.{DataGrid, ElevGrid, PrecipGrid, TempGrid}
import Utilities.Point

object GeoStation {


}


class GeoStation() {

    //make data fields private
    //make climate station
    private val ulP: Point = new Point(0.0, 0.0)//upper left point
    private val brP: Point = new Point(0.0, 0.0)//bottom right point

    //initialize all dataGrids to None
    private var temperatures:   Option[TempGrid] = None
    private var precipitation:  Option[PrecipGrid] = None
    private var elevation:      Option[ElevGrid] = None

    //???? CONSTRUCTOR 1: create ClimateStation using .bil/.hdr file pair
    //???? CONSTRUCTOR 2: create ClimateStation from ULYmap, ULYmap, pixel size, length, width


    //addDataGrid
    //mutator function
    //Param: newDataGrid - is added to appropriate private var based on type
    def addDataGrid(newDataGrid: DataGrid) {

        if (newDataGrid.getClass == Class[TempGrid])
            temperatures = newDataGrid.asInstanceOf[Option[TempGrid]]
        else if (newDataGrid.getClass == Class[PrecipGrid])
            precipitation = newDataGrid.asInstanceOf[Option[PrecipGrid]]
        else if (newDataGrid.getClass == Class[ElevGrid])
            elevation = newDataGrid.asInstanceOf[Option[ElevGrid]]

    }


}







