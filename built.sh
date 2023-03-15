#!/bin/bash

java -jar island/island.jar -k irregular -h 1080 -w 1920 -p 3800 -s 20 -o img/irregular2.mesh
java -jar visualizer/visualizer.jar -i img/irregular2.mesh -o img/irregular.svg