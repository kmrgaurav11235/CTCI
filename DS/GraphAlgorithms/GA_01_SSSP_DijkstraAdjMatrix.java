/* 
https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
https://www.programiz.com/dsa/dijkstra-algorithm

- Single Source Shortest Path (SSSP): Given a graph and a source vertex, find shortest paths from 
    source to all vertices in the given graph.
- Dijkstra’s algorithm is very similar to Prim’s algorithm for minimum spanning tree. Like Prim’s 
    MST, we generate a SPT (shortest path tree) with given source as root. We maintain two sets, 
    one set contains vertices included in shortest path tree, other set includes vertices not yet 
    included in shortest path tree. 
- At every step of the algorithm, we find a vertex which is in the un-included set and has a minimum 
    distance from the source.
- Algorithm
    1) Create a set sptSet (shortest path tree set) that keeps track of vertices included in shortest 
        path tree, i.e., whose minimum distance from source is calculated and finalized. Initially, 
        this set is empty.
    2) Assign a distance value to all vertices in the input graph. Initialize all distance values as 
        INFINITE. Assign distance value as 0 for the source vertex so that it is picked first.
    3) While sptSet doesn’t include all vertices
        a) Pick a vertex u which is not there in sptSet and has minimum distance value.
        b) Include u to sptSet.
        c) Update distance value of all adjacent vertices of u. To update the distance values, iterate 
            through all adjacent vertices. For every adjacent vertex v, if sum of distance value of u 
            (from source) and weight of edge u-v, is less than the distance value of v, then update the 
            distance value of v.
- The algorithm calculates shortest distance, but doesn’t calculate the path information. We can create 
    a parent array, update the parent array when distance is updated (like Prim's implementation) and use 
    it show the shortest path from source to different vertices.
- Time Complexity of the implementation is O(V^2). If the input graph is represented using adjacency list, 
    it can be reduced to O(E log V) with the help of binary heap. 
- Dijkstra’s algorithm doesn’t work for graphs with negative weight edges. For graphs with negative weight 
    edges, Bellman–Ford algorithm can be used.
*/
class GA_01_SSSP_DijkstraAdjMatrix {
    static class Graph {
        int numVertices;
        int[][] adjMatrix;

        Graph(int numVertices, int[][] adjMatrix) {
            this.numVertices = numVertices;
            this.adjMatrix = adjMatrix;
        }

        void addEdge(int source, int destination, int weight) {
            adjMatrix[source][destination] = weight;
            // adjMatrix[destination][source] = weight;
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
                for (int i = 0; i < numVertices; i++) {
                    if (adjMatrix[vertex][i] != 0) {
                        if (!isPermanent[i]) {
                            // Only consider temporary vertices
                            if (pathLength[i] > pathLength[vertex] + adjMatrix[vertex][i]) {
                                // Found a better path
                                pathLength[i] = pathLength[vertex] + adjMatrix[vertex][i];
                                parent[i] = vertex;
                            }
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
        int adjMatrix[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

        Graph g = new Graph(9, adjMatrix);
        g.dijkstra(0);
    }
}