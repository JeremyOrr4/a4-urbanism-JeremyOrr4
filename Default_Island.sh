#!/bin/bash

# mvn clean 
# mvn compile
# mvn install 
# mvn package

#mvn clean install

java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 2000 -s 20 -o img/irregular2.mesh
java -jar island/island.jar -lakes 8 -rivers 4 -i img/irregular2.mesh -p Volcano -o img/irregular.mesh -h 1080 -w 1920 #-seed MOSSERGIVEUSA90
java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular.svg 


