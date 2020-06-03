/*
https://www.geeksforgeeks.org/detect-cycle-in-a-graph/

- Detect Cycle in a Directed Graph: 
    Depth First Traversal can be used to detect a cycle in a Graph. There is a cycle in a graph only if 
    there is a back edge present in the graph. A back edge is an edge that is from a node to itself 
    (self-loop) or one of its ancestors in the tree produced by DFS. 
- To detect a back edge, keep track of vertices currently in the recursion stack of function for DFS 
    traversal. If a vertex is reached that is already in the recursion stack, then there is a cycle in the 
    graph. The edge that connects the current vertex to the vertex in the recursion stack is a back edge.
- Algorithm:
    1) Create the graph using the given number of edges and vertices.
    2) Create a recursive function that initializes the current index, visited array and recursion stack.
    3) Mark the current node as visited and also mark the index in recursion stack.
    4) Find all the vertices which are not visited and are adjacent to the current node. Recursively call 
        the function for those vertices. If the recursive function returns true, return true.
    5) If the adjacent vertices are already marked in the recursion stack then return true.
- Complexity
    * Time Complexity: O(V+E).
        Time Complexity of this method is same as time complexity of DFS traversal which is O(V+E).
    * Space Complexity: O(V). 
        To store the visited and recursion stack O(V) space is needed.

*/

import java.util.Iterator;
import java.util.LinkedList;

class Graph_04_CycleInDirectedGraph {
    static class Graph {
        private int numVertices;
        private LinkedList<Integer> adjacencyListArray[];

        Graph(int numVertices) {
            this.numVertices = numVertices;
            adjacencyListArray = new LinkedList[numVertices];

            for (int i = 0; i < numVertices; i++) {
                adjacencyListArray[i] = new LinkedList<>();
            }
        }

        boolean addEdge(int source, int destination) {
            if (0 <= source && 0 <= destination && numVertices > source && numVertices > destination) {
                adjacencyListArray[source].add(destination);
                return true;
            }
            return false;
        }

        void display() {
            for (int i = 0; i < numVertices; i++) {
                System.out.print("Adjacency list at Vertex " + i + ":\nHEAD -> ");
                for (int vertex: adjacencyListArray[i]) {
                    System.out.print(vertex + " -> ");
                }
                System.out.println("null");
            }
        }

        boolean isCyclePresentUtil(int vertex, boolean [] isVisited, boolean [] callStack) {
            // Don't check callStack[vertex] == false here. We will check before recursive call below.
            if (isVisited[vertex]) {
                // This vertex has already been evaluated.
                return false;
            }
            callStack[vertex] = true;
            isVisited[vertex] = true;

            // Check the adjacent vertices
            Iterator<Integer> iterator = adjacencyListArray[vertex].iterator();
            while (iterator.hasNext()) {
                int newVertex = iterator.next();
                if (!isVisited[newVertex]) {
                    if (isCyclePresentUtil(newVertex, isVisited, callStack)) {
                        return true;
                    }
                } // isVisited[vertex] == true is implicit when flow reaches else
                else if (callStack[newVertex]) { 
                    // Vertex is already in stack. So, loop is present.
                    return true;
                }
                
            }

            callStack[vertex] = false;
            return false;
        }

        boolean isCyclePresent() {

            boolean isVisited[] = new boolean[numVertices];
            boolean callStack[] = new boolean[numVertices];

            for (int i = 0; i < numVertices; i++) {
                if(!isVisited[i]) {
                    if (isCyclePresentUtil(i, isVisited, callStack)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(4); 
        graph.addEdge(0, 1); 
        graph.addEdge(0, 2); 
        graph.addEdge(1, 2); 
        graph.addEdge(2, 0); 
        graph.addEdge(2, 3); 

        System.out.println("Graph:");
        graph.display();

        if (graph.isCyclePresent()) {
            System.out.println("Graph contains cycle"); 
        } else {
            System.out.println("Graph doesn't contains cycle"); 
        } 

        System.out.println("------------------------");
        Graph graph2 = new Graph(4); 
        graph2.addEdge(0, 1); 
        graph2.addEdge(0, 2); 
        graph2.addEdge(1, 2); 
        graph2.addEdge(2, 3); 
        graph2.addEdge(3, 3); 

        System.out.println("Graph:");
        graph2.display();

        if (graph2.isCyclePresent()) {
            System.out.println("Graph contains cycle"); 
        } else {
            System.out.println("Graph doesn't contains cycle"); 
        }      
    }
}