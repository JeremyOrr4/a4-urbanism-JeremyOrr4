#!/bin/bash

java -jar island/island.jar -lakes 7 -rivers 5 -i img/irregular2.mesh
java -jar visualizer/visualizer.jar -i img/irregular2.mesh -o img/irregular.svg