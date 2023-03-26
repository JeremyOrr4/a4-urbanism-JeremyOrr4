#!/bin/bash
java -jar island/island.jar -lakes 8 -rivers 6 -i img/irregular2.mesh -p Volcano -o img/irregular.mesh -l 
java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular.svg 