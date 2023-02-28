#!/bin/bash

mvn install
cd generator
java -jar generator.jar sample.mesh  -width "1000" -square_size "20"
ls -lh sample.mesh

cd ..

cd visualizer 
java -jar visualizer.jar ../generator/sample.mesh sample.svg
ls -lh sample.svg