/*
https://www.geeksforgeeks.org/graph-and-its-representations/

- Graph is a data structure that consists of following two components:
    1. A finite set of vertices also called as nodes.
    2. A finite set of ordered pair of the form (u, v) called as edge. 
- The pair is ordered because (u, v) is not same as (v, u) in case of a directed graph(di-graph). 
    The pair of the form (u, v) indicates that there is an edge from vertex u to vertex v. The 
    edges may contain weight/value/cost.
- Two of the most commonly used representations of a graph:
    1. Adjacency Matrix
    2. Adjacency List

- Adjacency Matrix: It is a 2D array of size V x V where V is the number of vertices in a graph. 
    Let the 2D array be adj[][], a slot adj[i][j] = 1 indicates that there is an edge from vertex 
    i to vertex j. 
- Adjacency matrix for undirected graph is always symmetric. 
- Adjacency Matrix is also used to represent weighted graphs. If adj[i][j] = w, then there is an 
    edge from vertex i to vertex j with weight w.
- Pros: 
    * Representation is easier to implement and follow. 
    * Removing an edge takes O(1) time. 
    * Queries like whether there is an edge from vertex ‘u’ to vertex ‘v’ are efficient and can be 
        done in O(1) time.
- Cons: 
    * Consumes more space O(V^2). Even if the graph is sparse(contains less number of edges), it 
        consumes the same space. 
    * Adding a vertex is O(V^2) time as a new row and column needs to be added.

- Adjacency List: An array of lists is used. Size of the array is equal to the number of vertices. 
- Let the array be array[]. An entry array[i] represents the list of vertices adjacent to the i-th 
    vertex. 
- This representation can also be used to represent a weighted graph. The weights of edges can be 
    represented as lists of pairs. 
- Pros: 
    * Saves space O(|V|+|E|) . In the worst case, there can be C(V, 2) number of edges in a graph 
    thus consuming O(V^2) space. 
    * Adding a vertex is easier.

- Cons: 
    * Queries like whether there is an edge from vertex u to vertex v are not efficient and can be 
        done O(V).
*/
import java.util.LinkedList;

public class Graph_01_AdjacencyList {
    static class Graph {
        private int numVertices;
        private LinkedList <Integer> adjacencyLinkedListArray[];

        Graph(int numVertices) {
            this.numVertices = numVertices;
            adjacencyLinkedListArray = new LinkedList[numVertices];
            for(int i = 0; i < numVertices; i++) {
                adjacencyLinkedListArray[i] = new LinkedList<>();
            }
        }

        boolean addEdge(int u, int v) {
            if (0 <= u && 0 <= v && u < numVertices && v < numVertices) {
                adjacencyLinkedListArray[u].add(v);
                adjacencyLinkedListArray[v].add(u);
                return true;
            }
            return false;
        }

        void displayGraph() {
            for (int i = 0; i < numVertices; i++) {
                System.out.print("Adjacency List at Vertex " + i + "\nHEAD -> ");
                for (int vertex : adjacencyLinkedListArray[i]) {
                    System.out.print(vertex + " -> ");                    
                }
                System.out.print("null\n");
            }
        }
    }// end of Graph

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1); 
        g.addEdge(0, 4); 
        g.addEdge(1, 2); 
        g.addEdge(1, 3); 
        g.addEdge(1, 4); 
        g.addEdge(2, 3); 
        g.addEdge(3, 4); 

        g.displayGraph();
    }
}