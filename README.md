![Java CI with Maven](https://github.com/kpouer/WKTParser/workflows/Java%20CI%20with%20Maven/badge.svg)
![CodeQL](https://github.com/kpouer/WKTParser/workflows/CodeQL/badge.svg)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.kpouer/wktparser/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.kpouer/wktparser)
# WKTParser
A simple WKT (Well Know Text) parser grammar written for JavaCC.

The goal was to use it in a jEdit plugin, but you can use it for any other purpose

It does not support all WKT syntax but might be extended in the future.

## Dependency

Available through Maven central

```xml
<dependency>
  <groupId>com.kpouer</groupId>
  <artifactId>wktparser</artifactId>
  <version>1.1.0</version>
</dependency>
```

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
By default the parser will use JJT tree classes. But you could also use the shapes from
com.kpouer.wkt.shape package that implements shapes using double values.

```java
Polygon polygon = WKT.parseShape("POLYGON((40 40, 20 45, 45 30, 40 40))");
```

## Licence

WKT Parser is open source and licensed under the MIT License.