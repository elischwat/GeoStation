## GeoStation ##

### A geographic information library for spatial data analysis in Scala. ###

**Current state of project:**
- Database (and DatabaseFactory) object functional (tests passed).
- DataGrid (and DataGridFactory) objects functional (tests passed)
- TimeSeries, TimeSeriesFactory, DAO, and Geostation objects not written.

The GeoStation library contains many components, some completed, some in progress, and some not written at all.
Main components include:

- The **GeoStation** class is utilized by the user to interact with all other components. It accesses
    the Database through the DAO and will (eventually) interact with analyses modules (IE Hydrograph Prediction module).

 - The **DAO** (Data Accessor) object adds a layer of abstraction between GeoStation and the Database class.
    It allows the underlying database structure to change while maintaining the same accessor functions
    for GeoStation and analyses modules to use.

- The **Database** class holds DataGrid and TimeSeries objects. It builds lists of Datagrids when
    given a proper path to a directory. That directory must have 4 subdirectories, called "elevations",
    "precipitations", "temperatures", "timeseries". Data files within these folders will be used to create
     DataGrids which are then accessed through the Database object.

Minor components include:
- The **DataGrid** trait is inherited by the **ElevGrid**, **TempGrid**, **PrecipGrid** classes. All these
    classes are abstract data types responsible for holding data contained with files such as .flt,
    .bil, and more to come. All grid classes function the same, holding data from various geospatial
    datafiles in a 2-dimensional array of Floats. Built by the **DataGridFactory** object, DataGrids are
    hidden from the user and held within a Database.

- The **TimeSeries** class is a Sequence of 2-dimensional arrays. Built by the **TimeSeriesFactory** object,
    TimeSeries are hidden from the user and held within a Database.

- Utilities include: **JavaUtilities** (utilized for interaction with byte streams), **ScalaUtilities**
    (for various uses), and **Point** (easy to use lat/long case class).

