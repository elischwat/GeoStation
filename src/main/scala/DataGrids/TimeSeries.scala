package DataGrids

/**
  * Created by elischwat on 7/19/17.
  */
class TimeSeries {

    protected var name: String = ""
    protected var detail: String = ""
    protected var timeUnit: String = ""
    protected var valueUnit: String = ""
    protected var step: Int = 0//seconds
    protected var time: Seq[DataGrid] = Seq.empty


}
