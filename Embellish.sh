#!/bin/bash

java -jar island/island.jar -i img/irregular2.mesh -o img/irregular.mesh -v Himidity -lakes 8 -rivers 3 -cities 7 -h 1080 -w 1920 -seed 1034
java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular.svg 

