# A4 Island Generation Simulator

  - Author: "Jeremy Orr" <orrj4@mcmaster.ca>

## How to Run the Program

```
### DECORATING AN EXISTING MESH ###
###################################
###################################
(in main directory)
------(to install)------
mvn clean
mvn compile
mvn package
mvn install
------------------------
(in main directory)
java -jar island/island.jar -i (mesh to be read in) -o (mesh to be outputted and visualized) -h (height of input mesh) -w (width of input mesh)

If you do not see the tests run in the terminal try to do the command 'mvn clean install'
-----Other Args -----
Please see original Readme for the other commands
-cities (arg) -> speficy the amount of main cities you want
-citysize (arg) -> speficiy big or small city (big/small)

Island.sh will install program and generate a new mesh and an island. In order for seeds to work properly, the same mesh must be used so the built.sh script decorates and island but does not produce a new mesh.
```

It creates two jars:

  1. `generator/generator.jar` to generate meshes
  2. `visualizer/visualizer.jar` to visualize such meshes as SVG files

## Extending the Library (City)
I have added my own created Graph Abstract data type. This comes with the implentation of a shorest path algorithm and city system added to the mesh that you create. The args can be seen in the Decorating existing mesh section. You create one hub city where multiple roads lead into it. This means you can have multiple hub cities to enrich your mesh.

### Generating a mesh, grid or irregular

```
mosser@azrael A2 % java -jar generator/generator.jar -k grid -h 1080 -w 1920 -p 1000 -s 20 -o img/grid.mesh
mosser@azrael A2 % java -jar generator/generator.jar -k grid -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh
```

One can run the generator with `-help` as option to see the different command line arguments that are available

### Visualizing a mesh, (regular or debug mode)

```
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid.svg          
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid_debug.svg -x
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular.svg   
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular_debug.svg -x
```

Note: PDF versions of the SVG files were created with `rsvg-convert`.

| Id  | Feature  | Notes | Status  |  Started  | Delivered |
|:---:| :-----:  | :---: | :----:  |  :-----:  | :-------: |
| F1  | Create GraphADT  | • Implement Nodes and Edges • Create a graph and have the needed testing for it | D  |  Week 1  | Week 1 |
| F2  | Create interface for finding path in nodes  | • Create an interface with methods to find the shortest path | D  |  Week 1  | Week 1 |
| F3  | Create a shortest path algorithm  | • Use Dijkstra's algorithm to find the shorest path • Use Inteface | D  |  Week 1  | Week 1 |
| F4  | Testing Coverage for ADT  | • Create a testing suite for our ADT • Use proper methods and Junit | D  |  Week 1  | Week 1 |
| F5  | Update the ReadMe  | • Document	your project author, rationale, and explanations for extending the	library	by implementing a new algorithm | D |  Week 1  | Week 1 |
| F6 | Add pathfinder as a dependency  | • Add Pathfinder folder to maven depencies using POM.xml file | D |  Week 1  | Week 1 |
| F7 | Add cities at vertexs  | • Randomly pick a vertex that will be the base city | D |  Week 2  | Week 2 |
| F8 | Make a path for the base city to other cities  | • Randomly pick a vertex that will be the base city for the star network| D |  Week 2  | Week 2 |
| F9 | Add amount of cities to Command Line | • Length of Star network and amount of cities to be add | D |  Week 2  | Week 2 |
| F10 | Do questions in doc | • Answer all questions for report| D |  Week 2  | Week 2 |

Backlog:Maybe fix the length to be the length of a segment.

