package DataGrids

/**
  * Created by elischwat on 7/19/17.
  */
class TimeSeries(nameP: String, detailP: String, timeUnitP: String,
                 valueUnitP: String, stepP: Int, timeP: Seq[DataGrid]) {

    protected var name: String = nameP
    protected var detail: String = ""
    protected var timeUnit: String = timeUnitP
    protected var valueUnit: String = valueUnitP
    protected var step: Int = stepP //seconds
    protected var time: Seq[DataGrid] = timeP

}
