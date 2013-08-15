#!/bin/bash
# Run a WorldWind Demo
# $Id: run-demo.bash 919 2012-11-29 23:26:27Z dcollins $

echo Running $1
java -Xmx512m -Dsun.java2d.noddraw=true -classpath ./src:./classes:./worldwind.jar:./worldwindx.jar:./jogl.jar:./gluegen-rt.jar:./gdal.jar $*
