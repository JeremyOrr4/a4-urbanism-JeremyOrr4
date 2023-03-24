#!/bin/bash
java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 2000 -s 20 -o img/irregular2.mesh
java -jar island/island.jar -lakes 3 -rivers 1 -i img/irregular2.mesh
java -jar visualizer/visualizer.jar -i img/irregular2.mesh -o img/irregular.svg