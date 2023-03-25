# A3 Island Generation Simulator

  - Author: "" <EMAIL>
  - Author: "Jeremy Orr" <orrj4@mcmaster.ca>
  - Author: "" <EMAIL>

## How to Run the Program

```
Once the proper instalation of 'mvn instll' & 'mvn clean', a user can simply run the 'island.sh' script. If open the script, you see parameters with flags such as width and heights. Alter these paramerters to change the generation around. See Generating  a mesh below for help. This will result in a SVG file which can be open by your default internet browser. This image will be located in your img folder.
```

It creates two jars:

  1. `generator/generator.jar` to generate meshes
  2. `visualizer/visualizer.jar` to visualize such meshes as SVG files

## Examples of execution

### Generating a mesh, grid or irregular

```
mosser@azrael A2 % java -jar generator/generator.jar -k grid -h 1080 -w 1920 -p 1000 -s 20 -o img/grid.mesh
mosser@azrael A2 % java -jar generator/generator.jar -k grid -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh
```
TO RUN THROUGH ISLAND GENERATOR DO THIS
java -jar Island/island.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh

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
|:-:  |---       |       |   :-:   | :-:       | :-:       |
| F1  | Generate different tiles to differentiate between land and water  | • Create methods for land and water tiles which pass in a tile and give it the proper attributes
  • Create classes for different tile types or just for general tile assignment? | S  |  Week 1  | Week 1 |
| F2  | Create a circular island, integrating both types of tiles  | • Produce an algorithm to choose the correct tiles to be made land tiles
  • Create tile assignment class | S  |  Week 1  | Week 1 |
| F3  | Create a third lagoon tile to represent water within the island  | • Add new properties for water inside an island | S  |  Week 1  | Week 1 |
| F4  | Identify land tiles touching water and give them a different colour (Beach)  | • Create new tile type with beach properties
  • Create algorithm to recognize when land tiles touch water tiles | S  |  Week 1  | Week 1 |
| F5  | Create abstract notion of shape to be extended into different realizations |   • Create shape superclass with shape subclasses, circle is trivial case | S  |  Week 1  | Almost Done |
| F6  | Implement altimetric profiles  | • Add property of altitude (integer value), use this to adjust other properties • Create algorithm for assigning altitudes (or randomly) | S  |  Week 1  | Week 1 |
| F7  | Implenting Elevation with Polygons  | • Add new properties for water inside an island | S  |  Week 1  | Week 1 |
| F8  | Command line option for number of lakes  | • CLI command line option • Create water bodies within the land mass | S  |  Week 2  | Week 2 |
| F9  | Command line option for number of rivers | • CLI command option • Give segments river properties | S  |  Week 2  | Week 2 |
| F10  | Implement river behaviour  | • Create algorithm so rivers traverse to lower elevations | S  |  Week 2  | Week 2 |
| F11  | Implement river combination  | • Change thickness based on river intersections | S  |  Week 2  | Week 2 |
| F12  | Implement soil absorption  | • Compute soil absorption based on humidity and proximity to water• Implement soil profile builder (how much is absorbs soil) • Change properties based on absorption | S  |  Week 2  | Week 2 |
| F13  | Implement aquifer and soil command line option  | • CLI command option • Make specific tiles aquifers and adjust moisture levels of neighbours | S  |  Week 3  | Week 3 |
| F14  | Implement biomes using humidity and temperature (elevation) |  • Use humidity and temperature properties of tiles to determine biome  | S  |  Week 3  | Week 3 |
| F15  |Implement whittaker diagram command line |  • CLI command line option  | S  |  Week 3  | Week 3 |
| F16  |Implement seeds |    • CLI command option • Use given seed to determine properties of the island  | S  |  Week 3  | Week 3 |
| F17  |Create JUnit testing |  • Create the proper classes  | S  |  Week 3  | Week 3 |
| F18  | Do our A3 report and Update Read Me|  • Finish all questions asked of us in the report • Fix up ReadMe |  S  |  Week 3  | Week 3 |
