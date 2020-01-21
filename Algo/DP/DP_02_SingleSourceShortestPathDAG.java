/*
DEBUG THIS: Not Working Right Now.
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
Lecture: https://youtu.be/OQ5jsbhAv_M?t=1862
Single source shortest Path in Directed Acyclic Graphs:
    - We need to find shortest path between Source S and a vertex V - SP(V).
    - Take a vertex U so that there is an edge from U to V.
    - Now we can try to solve the sub-problem, i.e. shortest paths from S to U. Once we find this
        for all possible U's (all vertices that have edge to V), we can solve the bigger problem:
        SP(V) = Min( Dist(U, V) + SP(U) )
*/
class DP_02_SingleSourceShortestPathDAG {
    static class AdjacencyListNode {
        private int index;
        private int weight;
        AdjacencyListNode(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
        int getIndex() {
            return index;
        }
        int getWeight() {
            return weight;
        }
    }
    static class Graph {
        int v;
        LinkedList<AdjacencyListNode> adjacencyList[];

        Graph (int v) {
            this.v = v;
            adjacencyList = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }

        void addEdge(int source, int destination, int weight) {
            adjacencyList[source].add(new AdjacencyListNode(destination, weight));
        }

        void display() {
            for (int i = 0; i < v; i++) {
                System.out.print("Vertex " + i + " -> ");
                Iterator<AdjacencyListNode> iterator = adjacencyList[i].iterator();
                while (iterator.hasNext()) {
                    AdjacencyListNode adjacencyListNode = iterator.next();
                    System.out.print(adjacencyListNode.getIndex() + " [ Weight = " + adjacencyListNode.getWeight() + " ] -> ");
                }
                System.out.println(" null");
            }
        }

        private String getPath(int source, int destination, int [] parent) {
            String path = String.valueOf(destination);
            int vertex = destination;
            while (vertex != source) {
                vertex = parent[vertex];
                if (vertex == -1) {
                    // No path possible
                    return "No Path.";
                }
                path = vertex + " " + path;
            }

            return path;
        } 

        private Map<Integer, Integer> getNeighbourAndWeights(int destination) {
            Map<Integer, Integer> neighbourAndWeights = new HashMap<>();
            for (int i = 0; i < v; i++) {
                if (i == destination) {
                    continue;
                }
                Iterator<AdjacencyListNode> iterator = adjacencyList[i].iterator();
                while(iterator.hasNext()) {
                    AdjacencyListNode adjacencyListNode = iterator.next();
                    if (adjacencyListNode.getIndex() == destination) {
                        // There is an edge from this node to destination
                        neighbourAndWeights.put(i, adjacencyListNode.getWeight());
                    }
                }
            }
            return neighbourAndWeights;
        }

        int shortestPathUtil(int source, int destination, int [] shortestPathMemo, int [] parent) {
            if (source == destination || shortestPathMemo[destination] != Integer.MAX_VALUE) {
                // Solution has already been memoized
                return shortestPathMemo[destination];
            }
            // Else find the solution recursively and memoize it

            // Get nodes with edge to destination node and corresponding weights
            Map<Integer, Integer> neighbourAndWeights = getNeighbourAndWeights(destination);

            int shortestPath = Integer.MAX_VALUE;
            for (int node : neighbourAndWeights.keySet()) {
                int pathLength = shortestPathUtil(source, node, shortestPathMemo, parent);
                if (pathLength == Integer.MAX_VALUE) {
                    // No path: very important to put this here or 'totalPathLength' below will 
                    // wrap around and become negative.
                    continue;
                }
                int totalPathLength = pathLength + neighbourAndWeights.get(node);
                if (totalPathLength < shortestPath) {
                    shortestPath = totalPathLength;
                    parent[destination] = node;
                }
            }
            shortestPathMemo[destination] = shortestPath;
            return shortestPath;
        }

        void shortestPath(int source) {
            // Create and initialize the memo 
            int shortestPathMemo[] = new int [v];

            int parent[] = new int [v];
            for (int i = 0; i < v; i++) {
                parent[i] = -1;
            }
            /*
            Parent pointers are used to store the solution to actual problem in DP. Not using them
            just returns the cost of solution. In this case, traversing parent pointers backwards  
            will return the actual path. The solution without them will just return the shortest path 
            to a node.
            Since DP problems have optimal sub-structure property, we generally need to store just one
            array. 
            */

            for (int i = 0; i < v; i++) {
                shortestPathMemo[i] = Integer.MAX_VALUE;
            }
            shortestPathMemo[source] = 0;

            for (int i = 0; i < v; i++) {
                if (i != source && shortestPathMemo[i] == Integer.MAX_VALUE) {
                    // For all vertices where solution has not been found yet
                    shortestPathUtil(source, i, shortestPathMemo, parent);
                }
            }

            for (int i = 0; i < v; i++) {
                if (shortestPathMemo[i] == Integer.MAX_VALUE) {
                    System.out.println("Node " + i + " : Unreachable");
                } else {
                    System.out.println("Node " + i + " : " + shortestPathMemo[i] + " - Path: " + getPath(source, i, parent));
                }
            }
        }
    }
    // Bottom-up version involves topological sorting and is in the Graph section.
    public static void main(String[] args) {
        Graph g = new Graph(6); 
        g.addEdge(0, 1, 5); 
        g.addEdge(0, 2, 3); 
        g.addEdge(1, 3, 6); 
        g.addEdge(1, 2, 2); 
        g.addEdge(2, 4, 4); 
        g.addEdge(2, 5, 2); 
        g.addEdge(2, 3, 7); 
        g.addEdge(3, 4, -1); 
        g.addEdge(4, 5, -2); 

        System.out.println("Graph Display: ");
        g.display();

        int source = 1; 
        System.out.println("Shortest Path Distances from Source " + source + ": ");
        g.shortestPath(source); 
    }
}