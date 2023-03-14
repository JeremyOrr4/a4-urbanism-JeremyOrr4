package ca.mcmaster.cas.se2aa4.a3.island.Shapes;


import java.util.Random;

import de.articdive.jnoise.generators.noise_parameters.fade_functions.FadeFunction;
import de.articdive.jnoise.generators.noise_parameters.interpolation.Interpolation;

import de.articdive.jnoise.pipeline.JNoise;

public class Irregular implements BoundedShape{


    //public PerlinNoiseGenerator perlinCosine=PerlinNoiseGenerator.newBuilder().setSeed(3301).setInterpolation(Interpolation.COSINE).build();


    double centerX; 
    double centerY; 
    double width; 
    double height; 

    int seed;  

    public Irregular(double centerX, double centerY,  double width, double height){
        
        seed = ((int)Math.random()*10000); 
        this.centerX = centerX-width/2; 
        this.centerY = centerY-height/2; 
        this.width = width; 
        this.height = height; 
    }


    

    public JNoise perlin = JNoise.newBuilder().perlin(seed, Interpolation.QUADRATIC, FadeFunction.IMPROVED_PERLIN_NOISE)
    .scale(0.5)
    .addModifier(v -> (v + 1) / 2.0)
    .clamp(0.0, 1.0)
    .build();
    
    public boolean contains(double x, double y){

     
       // double testX = (2*x-centerX)/(width/2+centerX) -1  ; 

       double testX = (2*(x-centerX))/(width) -1  ; 
       double testY = (2*(y-centerY))/(height) -1 ; 

       // double testY = (2*y-centerY)/(height/2+centerY) -1 ; 


       double dist = Math.min(1, (testX*testX + testY*testY) / Math.sqrt(2)); 

        double noiseValue = perlin.evaluateNoise(x,y); 

        noiseValue = (noiseValue + (1-dist))/2; 
       

       boolean isLand =  (noiseValue)>0.45; 

        
        return isLand  ; 
    }


    public void setSeed(int seed){
        this.seed = seed; 
    }

    public Irregular scale(double factor){
        Irregular i = new Irregular(centerX+width/2, centerY+height/2, width*factor, height*factor); 
        i.setSeed(seed);
        return i; 
    }


    public Irregular scale(double xScale, double yScale){
        Irregular i = new Irregular(centerX+width/2, centerY+height/2, width*xScale, height*yScale); 
        i.setSeed(seed);
        return i; 
    }

    
}
