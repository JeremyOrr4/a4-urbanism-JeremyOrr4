#!/bin/bash

mvn install
cd generator
java -jar generator.jar sample.mesh
ls -lh sample.mesh

cd ..

cd visualizer 
java -jar visualizer.jar ../generator/sample.mesh sample.svg
ls -lh sample.svg