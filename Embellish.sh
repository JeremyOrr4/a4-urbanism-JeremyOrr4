#!/bin/bash

java -jar island/island.jar -i img/irregular2.mesh -o img/irregular.mesh -lakes 8 -rivers 3 -cities 16 -h 1080 -w 1920  #add seed
java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular.svg 

