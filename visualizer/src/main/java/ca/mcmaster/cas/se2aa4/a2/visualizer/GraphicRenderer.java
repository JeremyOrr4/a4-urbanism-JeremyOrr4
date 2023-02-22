package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.geom.Ellipse2D;
import java.awt.geom.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GraphicRenderer {

   
    
    public void render(Mesh aMesh, Graphics2D canvas) {
       
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);

    
        ArrayList<Segment> segmentswithcolour = new ArrayList<>();
        for (Segment s:aMesh.getSegmentsList()){
            segmentswithcolour.add(s);
        }


      
        for (Polygon p : aMesh.getPolygonsList()){
       
 
            canvas.setColor(extractColor(p.getPropertiesList()));
           
        int[] vertX = new int[4]; 
        int[] vertY = new int[4]; 


        vertX[0]= (int)aMesh.getVertices(segmentswithcolour.get(p.getSegmentIdxs(0)).getV1Idx()).getX();
        vertX[1]= (int)aMesh.getVertices(segmentswithcolour.get(p.getSegmentIdxs(0)).getV2Idx()).getX();
        vertX[3]= (int)aMesh.getVertices(segmentswithcolour.get(p.getSegmentIdxs(p.getSegmentIdxsCount()-1)).getV1Idx()).getX();
        vertX[2]= (int)aMesh.getVertices(segmentswithcolour.get(p.getSegmentIdxs(p.getSegmentIdxsCount()-1)).getV2Idx()).getX();


        vertY[0]= (int)aMesh.getVertices(segmentswithcolour.get(p.getSegmentIdxs(0)).getV1Idx()).getY();
        vertY[1]= (int)aMesh.getVertices(segmentswithcolour.get(p.getSegmentIdxs(0)).getV2Idx()).getY();
        vertY[3]= (int)aMesh.getVertices(segmentswithcolour.get(p.getSegmentIdxs(p.getSegmentIdxsCount()-1)).getV1Idx()).getY();
        vertY[2]= (int)aMesh.getVertices(segmentswithcolour.get(p.getSegmentIdxs(p.getSegmentIdxsCount()-1)).getV2Idx()).getY();

          
            canvas.fillPolygon(vertX, vertY, vertX.length);
                
            
           for(int id_s : p.getSegmentIdxsList()){
            
            Segment s = aMesh.getSegments(id_s); 

            canvas.setColor(extractColor(s.getPropertiesList()));
            int[] point1 = {(int)aMesh.getVertices(s.getV1Idx()).getX(),(int)aMesh.getVertices(s.getV1Idx()).getY()};
            int[] point2 = {(int)aMesh.getVertices(s.getV2Idx()).getX(),(int)aMesh.getVertices(s.getV2Idx()).getY()};
            canvas.setStroke(new BasicStroke(extractThickness(s.getPropertiesList())));
            canvas.drawLine(point1[0],point1[1],point2[0],point2[1]);
           }
        

          
            
          
        
        
        }

        for (Segment s : aMesh.getSegmentsList() ){

            
            // render segment on canvas
             canvas.setColor(extractColor(s.getPropertiesList()));
             int[] point1 = {(int)aMesh.getVertices(s.getV1Idx()).getX(),(int)aMesh.getVertices(s.getV1Idx()).getY()};
             int[] point2 = {(int)aMesh.getVertices(s.getV2Idx()).getX(),(int)aMesh.getVertices(s.getV2Idx()).getY()};
             canvas.setStroke(new BasicStroke(extractThickness(s.getPropertiesList())));
             canvas.drawLine(point1[0],point1[1],point2[0],point2[1]);
         }

        for(Vertex v : aMesh.getVerticesList()){ 

            int thickness = extractThickness(v.getPropertiesList()); 
            //render vertex on canvas
            double centre_x = v.getX() - (thickness/2.0d);
            double centre_y = v.getY() - (thickness/2.0d);
            canvas.setColor(extractColor(v.getPropertiesList()));

            
          
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, thickness, thickness);
            canvas.fill(point);
        }

    }

 

    private Color extractColor(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
                val = p.getValue();
            }
        }
        if (val == null)
            return Color.BLACK;
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);


        //extract alpha value if present in color string 
        if(raw.length>3){
            int alpha = Integer.parseInt(raw[3]); 
            return new Color(red, green, blue, alpha);
        }
       
        return new Color(red, green, blue);
    }


    private int extractThickness(List<Property> properties){

        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("thickness")) {
                val = p.getValue();
            }
        }
        if (val == null)
            return 3;

            return Integer.parseInt(val); 
    }


    
    



}
