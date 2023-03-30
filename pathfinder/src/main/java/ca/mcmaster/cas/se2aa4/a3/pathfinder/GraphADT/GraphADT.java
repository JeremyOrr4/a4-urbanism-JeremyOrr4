package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;

import ca.mcmaster.cas.se2aa4.a2.io.*;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.lang.Math.*;

public class GraphADT {
    int NumberOfNodes;
    public int[][] AdjacencyMatrix;
    HashMap<Integer, Point> NodeId;

    public GraphADT(int NumberOfNodes) {
        this.NumberOfNodes = NumberOfNodes;
        this.AdjacencyMatrix = new int[NumberOfNodes][NumberOfNodes];
        NodeId = new HashMap<Integer, Point>();
    }

    public void CreateWholeGraph(Structs.Mesh Mesh){
        // ArrayList<Structs.Vertex> Verticies = new ArrayList<Structs.Vertex>();
        // ArrayList<Integer> SegmentIDLists = new ArrayList<Integer>();
        System.out.println("TEST");
        int Vertex1;
        int Vertex2;

        double Vertex1XCoordinate;
        double Vertex1YCoordinate;
        double Vertex2XCoordinate;
        double Vertex2YCoordinate;

        for (Structs.Polygon Polygons : Mesh.getPolygonsList()){
            if (!(Tiles.getTileType(Polygons).equalsIgnoreCase("WATER"))){
                for (int IndidualSegmentsID : Polygons.getSegmentIdxsList()) {
                    Structs.Segment IndidualSegments = Mesh.getSegmentsList().get(IndidualSegmentsID);

                    Vertex1 = IndidualSegments.getV1Idx();
                    Vertex2 = IndidualSegments.getV2Idx();
                    
                    Structs.Vertex V1 = Mesh.getVertices(IndidualSegments.getV1Idx());
                    Structs.Vertex V2 = Mesh.getVertices(IndidualSegments.getV2Idx());

                    Vertex1XCoordinate = V1.getX();
                    Vertex1YCoordinate = V1.getY();
                    Vertex2XCoordinate = V2.getY();
                    Vertex2YCoordinate = V2.getY();

                    int weight = (int)Math.sqrt(Math.pow(Vertex2XCoordinate - Vertex1XCoordinate,2)+Math.pow(Vertex2YCoordinate - Vertex1YCoordinate,2));

                    AddEdge(Vertex1,Vertex2,weight);
                    
                }
                               
            }

        }


    }

    public void AddEdge(int FirstNode, int SecondNode, int weight) {
        this.AdjacencyMatrix[FirstNode][SecondNode] = weight;
    }

    public void RemovdeEdge (int FirstNode, int SecondNode){
        this.AdjacencyMatrix[FirstNode][SecondNode] = 100000;
    }
}
