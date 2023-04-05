#!/bin/bash

java -jar island/island.jar -lakes 8 -rivers 3 -i img/irregular2.mesh -o img/irregular.mesh -h 1080 -w 1920 #add seed
java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular.svg 