package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.geom.Ellipse2D;
import java.util.Collections;
import java.util.List;

public class GraphicRenderer {

   
    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas) {
        System.out.println("changes3");
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        //for (Vertex v: aMesh.getVerticesList())
        int center_x = 0;
        int center_y = 0;
        int centerx_next = 20;
        int centery_next = 0;
        System.out.println(aMesh.getVerticesCount());
        System.out.println(aMesh.getSegmentsCount());
        for(int i=0; i<aMesh.getVerticesCount()-1;i++){


            Vertex v = aMesh.getVertices(i);
            System.out.println(v.getX() +", " +v.getY() );
            Vertex next =  (i+1<aMesh.getVerticesCount()) ? aMesh.getVertices(i+1) : aMesh.getVertices(0);

            double centre_x = v.getX() - (THICKNESS/2.0d);
            double centre_y = v.getY() - (THICKNESS/2.0d);
            Color old = canvas.getColor();
            canvas.setColor(extractColor(v.getPropertiesList()));
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS, THICKNESS);
            canvas.fill(point);
        }
        for (int i=0;i< aMesh.getSegmentsCount();i++){
            Segment s = aMesh.getSegments(i);
            Vertex v = aMesh.getVertices(i);
            canvas.setColor(extractColor(v.getPropertiesList()));
            int[] point1 = {(int)aMesh.getVertices(s.getV1Idx()).getX(),(int)aMesh.getVertices(s.getV1Idx()).getY()}; //get coordinates of segment start point
            int[] point2 = {(int)aMesh.getVertices(s.getV2Idx()).getX(),(int)aMesh.getVertices(s.getV2Idx()).getY()}; //get coordinates of segment end point
            canvas.drawLine(point1[0],point1[1],point2[0],point2[1]);

            // Vertex next = aMesh.getVertices(aMesh.getVerticesList().indexOf(v)+1);
        }
    }

    private Color extractColor(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
                //System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        if (val == null)
            return Color.BLACK;
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return new Color(red, green, blue);
    }

}
