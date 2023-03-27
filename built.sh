#!/bin/bash
java -jar generator/generator.jar -k irregular -h 2080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh
java -jar island/island.jar -lakes 8 -rivers 3 -i img/irregular.mesh -o img/irregular.mesh -h 2080 -w 1920 -s Irregular -l -seed hello
java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular.svg 