package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class ColorProperty implements PropertyAccess<Color> {
    @Override
    public Optional<Color> extract(List<Property> props) {
        String value = new Reader(props).get("rgb_color");
        if (value == null)
            return Optional.empty();
        String[] raw = value.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return Optional.of(new Color(red, green, blue));
    }

    public Color extractSegment(List<Property> props) {
        String value = new Reader(props).get("rgb_color");
        String[] raw = value.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return (new Color(red, green, blue));
    }

    public boolean CheckRiver(Structs.Segment s){
        List<Structs.Property> Props = s.getPropertiesList();
        for (Structs.Property prop: Props){
            if(prop.getKey().equals("rgb_color")){
                return true;
            }
        }
        return false;

    }

    public boolean CheckVertex(Structs.Vertex v){
        List<Structs.Property> Props = v.getPropertiesList();
        for (Structs.Property prop: Props){
            if(prop.getKey().equals("rgb_color")){
                return true;
            }
        }
        return false;

    }
}
