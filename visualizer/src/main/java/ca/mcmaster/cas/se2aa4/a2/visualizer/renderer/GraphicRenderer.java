package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.ColorProperty;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.Iterator;
import java.util.Optional;

public class GraphicRenderer implements Renderer {

    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.2f);
        canvas.setStroke(stroke);
        drawPolygons(aMesh,canvas);
        for (Structs.Segment s: aMesh.getSegmentsList()){
            if (new ColorProperty().CheckRiver(s)){
                canvas.setColor(new ColorProperty().extractSegment(s.getPropertiesList()));
                canvas.setStroke(new BasicStroke(3));
                double[] coords = new double[4];
                coords[0] = aMesh.getVerticesList().get(s.getV1Idx()).getX();
                coords[1] = aMesh.getVerticesList().get(s.getV1Idx()).getY();
                coords[2] = aMesh.getVerticesList().get(s.getV2Idx()).getX();
                coords[3] = aMesh.getVerticesList().get(s.getV2Idx()).getY();
                canvas.drawLine((int)coords[0],(int)coords[1],(int)coords[2],(int)coords[3]);
            }
        }
        canvas.setStroke(new BasicStroke(0.2f));
        for (Structs.Vertex v: aMesh.getVerticesList()){
            if (new ColorProperty().CheckVertex(v)){
                canvas.setColor(new ColorProperty().extractSegment(v.getPropertiesList()));
                Ellipse2D point = new Ellipse2D.Double(v.getX()-2.5,v.getY()-2.5,5,5);
                canvas.fill(point);
            }
        }
    }

    private void drawPolygons(Mesh aMesh, Graphics2D canvas) {
        for(Structs.Polygon p: aMesh.getPolygonsList()){
            drawAPolygon(p, aMesh, canvas);
        }
    }

    private void drawAPolygon(Structs.Polygon p, Mesh aMesh, Graphics2D canvas) {
        Hull hull = new Hull();
        for(Integer segmentIdx: p.getSegmentIdxsList()) {
            hull.add(aMesh.getSegments(segmentIdx), aMesh);
        }
        Path2D path = new Path2D.Float();
        Iterator<Vertex> vertices = hull.iterator();
        Vertex current = vertices.next();
        path.moveTo(current.getX(), current.getY());
        while (vertices.hasNext()) {
            current = vertices.next();
            path.lineTo(current.getX(), current.getY());
        }
        path.closePath();
        canvas.draw(path);
        Optional<Color> fill = new ColorProperty().extract(p.getPropertiesList());
        if(fill.isPresent()) {
            Color old = canvas.getColor();
            canvas.setColor(fill.get());
            canvas.fill(path);
            canvas.setColor(old);
        }
    }

}
