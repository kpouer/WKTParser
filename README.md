![Java CI with Maven](https://github.com/kpouer/WKTParser/workflows/Java%20CI%20with%20Maven/badge.svg)
![CodeQL](https://github.com/kpouer/WKTParser/workflows/CodeQL/badge.svg)

# WKTParser
A simple WKT (Well Know Text) parser grammar written for JavaCC.

The goal was to use it in a jEdit plugin, but you can use it for any other purpose

It doesn't not support all WKT syntax but might be extended in the future.

## Supported structures

* POINT
* LINESTRING
* POLYGON
* MULTIPOINT
* MULTIPOLYGON
* MULTILINESTRING
* GEOMETRYCOLLECTION

## Example

```java
Start start = new WKT(new StringReader("LINESTRING(30 10,-3.2011243453   -101.12124240)"))
    .Start();
LineString linestring = (LineString) start.jjtGetChild(0);
Point point1 = (Point) linestring.jjtGetChild(0);
Point point2 = (Point) linestring.jjtGetChild(1);
```

## Licence

WKT Parser is open source and licensed under the MIT License.