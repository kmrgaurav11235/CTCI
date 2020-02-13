import java.util.Iterator;
import java.util.LinkedList;

class Graph_09_SSSP_DijkstraAdjList {
    static class AdjacencyListNode {
        int destination;
        int weight;

        AdjacencyListNode(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
    static class Graph {
        private int numVertices;
        private LinkedList<AdjacencyListNode> adjList[];

        Graph(int numVertices) {
            this.numVertices = numVertices;
            adjList = new LinkedList[numVertices];

            for (int i = 0; i < numVertices; i++) {
                adjList[i] = new LinkedList<>();
            }
        }

        void addEdge(int source, int destination, int weight) {
            adjList[source].add(new AdjacencyListNode(destination, weight));
            // adjList[destination].add(new AdjacencyListNode(source, weight));
        }

        void display() {
            for (int i = 0; i < numVertices; i++) {
                System.out.print("Vertex " + i + " -> ");
                Iterator<AdjacencyListNode> iterator = adjList[i].iterator();
                while (iterator.hasNext()) {
                    AdjacencyListNode adjacencyListNode = iterator.next();
                    System.out.print(adjacencyListNode.destination + " [ Weight = " + adjacencyListNode.weight + " ] -> ");
                }
                System.out.println(" null");
            }
        }

        private int getClosestTemporaryVertex(int[] pathLength, boolean[] isPermanent) {
            int minIndex = -1, minLen = Integer.MAX_VALUE;
            for (int i = 0; i < numVertices; i++) {
                if (!isPermanent[i]) {
                    if (minLen > pathLength[i]) {
                        minLen = pathLength[i];
                        minIndex = i;
                    }
                }
            }
            return minIndex;
            // This will return -1 when only unreachable vertices are left, i.e. when pathLength
            // for all temporary vertices is Integer.MAX_VALUE
        }

        void dijkstra(int source) {
            boolean [] isPermanent = new boolean[numVertices];
            int [] pathLength = new int[numVertices];
            int [] parent = new int[numVertices];

            // Initialization
            for (int i = 0; i < numVertices; i++) {
                // isPermanent[i] = false;
                pathLength[i] = Integer.MAX_VALUE;
                parent[i] = -1;
            }
            pathLength[source] = 0;

            while(true) {
                // Select a vertex
                int vertex = getClosestTemporaryVertex(pathLength, isPermanent);
                if (vertex == -1) {
                    // All vertices processed or Only unreachable vertices are left
                    break;
                }

                isPermanent[vertex] = true;
                // Visit all neighboring vertices
                Iterator<AdjacencyListNode> it = adjList[vertex].iterator();
                while (it.hasNext()) {
                    AdjacencyListNode node = it.next();
                    if (!isPermanent[node.destination]) {
                        // Only consider temporary vertices
                        if (pathLength[node.destination] > pathLength[vertex] + node.weight) {
                            // Found a better path
                            pathLength[node.destination] = pathLength[vertex] + node.weight;
                            parent[node.destination] = vertex;
                        }
                    }
                }
            }

            // Printing
            for (int i = 0; i < numVertices; i++) {
                System.out.println("Node: " + i + ", Distance: " + pathLength[i] + ", Parent: " + parent[i]);
            }
        }
    }
    public static void main(String[] args) {
        int V = 9; 
        Graph graph = new Graph(V); 
        graph.addEdge(0, 1, 4); 
        graph.addEdge(0, 7, 8); 
        graph.addEdge(1, 2, 8); 
        graph.addEdge(1, 7, 11); 
        graph.addEdge(2, 3, 7); 
        graph.addEdge(2, 8, 2); 
        graph.addEdge(2, 5, 4); 
        graph.addEdge(3, 4, 9); 
        graph.addEdge(3, 5, 14); 
        graph.addEdge(4, 5, 10); 
        graph.addEdge(5, 6, 2); 
        graph.addEdge(6, 7, 1); 
        graph.addEdge(6, 8, 6); 
        graph.addEdge(7, 8, 7); 

        System.out.println("Graph Display: ");
        graph.display();
    
        graph.dijkstra(0); 
    }
}