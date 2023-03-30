package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;





import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;


public class GraphADT {

    Node Node;
    Edge Edge;
    HashMap<Node, Set<Node>> AdjacencyList;

    public GraphADT (Node Node, Edge Edge, HashMap<Node, Set<Node>> AdjacencyList) {
        this.Node = Node;
        this.Edge = Edge;
        this.AdjacencyList = 
    }

//     public void AddEdge(Node StartNode, Edge EndNode) {
//         if (AdjacencyList.containsKey(StartNode)) {
//             ArrayList<Edge> NodePathList = this.AdjacencyList.get(StartNode);
//             // for (Edge CurrentEdge: NodePathList){ // Removes duplicate edges
//             //     // int Edge1 = Edge.GetNodeIDFromEdge(CurrentEdge);
//             //     // int Edge2 = Edge.GetNodeIDFromEdge(EndNode);
//             //     if (Edge.GetNodeIDFromEdge(CurrentEdge) == Edge.GetNodeIDFromEdge(EndNode)){
                   
//             //         return;
//             //     }
//             // }
            
//             NodePathList.add(EndNode);
//         }

//         else {
//             this.AdjacencyList.put(StartNode, new ArrayList<Edge>());
//             this.AdjacencyList.get(StartNode).add(EndNode);
//         }
//     }

//     public void PrintPathOfNode(Node PrintedNode) {
        
//         ArrayList<Edge> NewPathList = new ArrayList<Edge>();

//         System.out.println(this.AdjacencyList.keySet().size());

//         for(Node KeyNode : this.AdjacencyList.keySet() ){
//             System.out.println(KeyNode.PrintNode());

//             for (Edge ValueEdge:this.AdjacencyList.get(PrintedNode)){
//                 System.out.println(ValueEdge.PrintEdge());
//             }
                
//         }

//         if (this.AdjacencyList.containsKey(PrintedNode)) { // WOrks
//             System.out.println("*******************");
//             System.out.println("Node Start: " + Node.GetNodeIDValue(PrintedNode));
            
//             NewPathList = this.AdjacencyList.get(PrintedNode);

//             for (int i = 0; i < NewPathList.size(); i++) {
//                 Edge PrintedEdge = NewPathList.get(i);

//                 PrintedEdge.PrintEdge();
//             }
//             System.out.println("*******************");
//         }

//         else {
//             System.out.println("This node is not in graph.");
//         }
//     }

//     public void PrintAdjacencyList(){
//         for (Node TempNode: this.AdjacencyList.keySet())
//         System.out.println("Keys: " + Node.GetNodeIDValue(TempNode));
//     }

// }
