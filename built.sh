#!/bin/bash
java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular2.mesh
java -jar island/island.jar -lakes 6 -rivers 3 -i img/irregular2.mesh -l
java -jar visualizer/visualizer.jar -i img/irregular2.mesh -o img/irregular.svg