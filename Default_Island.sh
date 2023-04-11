#!/bin/bash

# mvn clean 
# mvn compile
# mvn install 
# mvn package

#mvn clean install

java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 2000 -s 20 -o img/irregular2.mesh
java -jar island/island.jar -i img/irregular2.mesh -o img/irregular.mesh -h 1080 -w 1920
java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular.svg 


