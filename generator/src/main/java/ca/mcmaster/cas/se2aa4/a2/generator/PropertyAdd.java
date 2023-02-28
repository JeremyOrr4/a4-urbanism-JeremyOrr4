package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.Random;

import java.util.*;


import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;


public class PropertyAdd {
    public static Vertex AddVertexProperties(Vertex v, String thicknessString){
        //produce random RGBA values
        Random bag = new Random();
        int red = bag.nextInt(255);
        int green = bag.nextInt(255);
        int blue = bag.nextInt(255);
        //put into a color code string and add color property
        String colorCode = red + "," + green + "," + blue;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        //add thickness property
        Property thickness = Property.newBuilder().setKey("thickness").setValue(thicknessString).build();

        Vertex thickened = Vertex.newBuilder(v).addProperties(thickness).build();
        Vertex colored = Vertex.newBuilder(thickened).addProperties(color).build();
        return colored;
    }

    public static Segment AddSegmentProperties(Segment s){
        //Sets segment color to black
        Property color = Property.newBuilder().setKey("rgb_color").setValue("0,0,0").build();;
        Segment colored = Segment.newBuilder(s).addProperties(color).build();
        return colored;

    }

    public static Segment RandomSegmentColor(Segment s){
        //gives segment random color
        Segment colored = Segment.newBuilder(s).addProperties(randomColor()).build();
        return colored;

    }

    public static Property randomColor(){
        //produce RGBA values randomly
        Random bag = new Random();
        int red = bag.nextInt(255);
        int green = bag.nextInt(255);
        int blue = bag.nextInt(255);
        int alpha = bag.nextInt(255);


        //put values into a string and return the built property
        String colorCode = String.format("%d,%d,%d,%d",red,green,blue,alpha);
        return Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();

    }

    public static Polygon polyColor(Polygon p, Property color){
        //add a color to a polygon
        return Polygon.newBuilder(p).addProperties(color).build();
    }

    //set vertex color to be equal to property element passed into method
    public static Vertex setToPolygon(Vertex v, Property p){return Vertex.newBuilder(v).addProperties(p).build();}
    public static Vertex debugRed(Vertex v){
        //set vertex color to red for debugging purposed
        Property color = Property.newBuilder().setKey("rgb_color").setValue("255,0,0").build();
        return Vertex.newBuilder(v).addProperties(color).build();
    }
    public static Polygon debugBlack(Polygon p){
        //set polygon color to black for debugging purposes
        Property color = Property.newBuilder().setKey("rgb_color").setValue("0,0,0").build();
        return Polygon.newBuilder(p).addProperties(color).build();
    }

    public static Segment debugGrey(Segment s){
        //set segment color to gray for debugging purposes
        Property color = Property.newBuilder().setKey("rgb_color").setValue("211,211,211").build();
        return Segment.newBuilder(s).addProperties(color).build();
    }
    public static Segment debugRed(Segment s){
        //set segment color to red for debugging purposes
       Property color = Property.newBuilder().setKey("rgb_color").setValue("255,0,0").build();
        return Segment.newBuilder(s).addProperties(color).build();
    }
    public static Segment setTransparent(Segment s){
        //set segment color to transparent
        Property color = Property.newBuilder().setKey("rgb_color").setValue("0,0,0,0").build();
        return Segment.newBuilder(s).addProperties(color).build();
    }

    public static Property averageColor(List<Segment> segments ){



        int rgba[] = new int[4];

        for(Segment s : segments){
            String val = "0,0,0,0";
            //get RGBA values of each segment
            for(Property p : s.getPropertiesList()){
                if (p.getKey().equals("rgb_color"))  val = p.getValue();

            }
            //add them to rgba array
            String[] sColor=val.split(",");
            for(int i=0;i<3;i++) rgba[i] += Integer.parseInt(sColor[i]);
            rgba[3] += (sColor.length==4 ? Integer.parseInt(sColor[3]) : 255 );



        }
        //average each color value
        for(int i=0; i<4;i++){
            rgba[i] = rgba[i]/segments.size();



        }

        rgba[3]  = rgba[3]/2;

        //rebuild string with new average
        String colorCode = rgba[0]+","+rgba[1]+","+rgba[2] +","+rgba[3] ;


        return Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
    }

}
