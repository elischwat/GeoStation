GeoStation

A geographic information library for spatial data analysis in Scala.

The GeoStation library is built on 2 main components:
- GeoStation objects
- DataGrid objects

The GeoStation class is instantiated to hold related data sets of various types. It can be thought of as a single "map" project, where analysis will be performed on multiple data sets for a single location or purpose. Each data set is contained in a DataGrid object

The DataGrid class is a container for a singular data set. DataGrid contains many subclasses (TempGrid, PrecipGrid, ElevGrid, more to come) and each one is able to be created from a variety of a file types. DataGrids can be thought of as the layers of the GeoStation contributing to the overall "map" project.