package ca.mcmaster.cas.se2aa4.a3.island.Shapes; 


/**
 * A Bounded shape is simply a closed region a point can be inside or outside of 
 */
public interface BoundedShape {

    
    
    public boolean contains(double x, double y); 

    public BoundedShape scale(double factor); 
    
}
