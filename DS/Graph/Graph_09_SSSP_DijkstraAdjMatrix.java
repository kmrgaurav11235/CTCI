// https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
class Graph_09_SSSP_DijkstraAdjMatrix {
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