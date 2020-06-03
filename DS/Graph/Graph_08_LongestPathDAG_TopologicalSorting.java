/*
https://www.geeksforgeeks.org/find-longest-path-directed-acyclic-graph/
- Given a Weighted Directed Acyclic Graph (DAG) and a source vertex s in it, find the longest 
    distances from s to all other vertices in the given graph.
- The longest path problem for a general graph is not as easy as the shortest path problem because 
    the longest path problem doesn’t have optimal substructure property. In fact, the Longest Path 
    problem is NP-Hard for a general graph. 
- However, the longest path problem has a linear time solution for directed acyclic graphs. The idea 
    is similar to linear time solution for shortest path in a directed acyclic graph., we use 
    Topological Sorting.
- We initialize distances to all vertices as minus infinite and distance to source as 0, then we find 
    a topological sorting of the graph. Topological Sorting of a graph represents a linear ordering of 
    the graph. 
- Once we have topological order (or linear representation), we one by one process all vertices in 
    topological order. For every vertex being processed, we update distances of its adjacent using 
    distance of current vertex.
- Algorithm:
    1. Topologically sort the Graph.
    2. Set dis[node] = NEGATIVE_INFINITY for all nodes.
    3. Set dis[source] = 0
    4. Go through the topologically sorted list and set the shortest distance for each adjacent node 
        using distance of current vertex.

        for every adjacent vertex adjacentNode of node
            if dis[node] != NEGATIVE_INFINITY && ( dis[adjacentNode] < dis[node] + weight[node, adjacentNode] )
                dis[adjacentNode] = dis[node] + weight[node, adjacentNode]
*/
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

class Graph_08_LongestPathDAG_TopologicalSorting {
    static class AdjacencyListNode {
        // For Graphs with weights
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
        // Graph with weights
        private int v;
        private LinkedList<AdjacencyListNode> adjacencyList[];

        Graph(int v) {
            this.v = v;
            adjacencyList = new LinkedList[v];

            for (int i = 0; i < v; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }

        boolean addEdge(int source, int destination, int weight) {
            if (source >= 0 && destination >= 0 && source < v && destination < v) {
                AdjacencyListNode adjacencyListNode = new AdjacencyListNode(destination, weight);
                adjacencyList[source].add(adjacencyListNode);
                return true;
            }
            return false;
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

        void topologicalSortUtil(int vertex, boolean [] isVisited, Deque<Integer> stack) {
            if (isVisited[vertex]) {
                return;
            }
            isVisited[vertex] = true;

            Iterator<AdjacencyListNode> iterator = adjacencyList[vertex].iterator();
            while (iterator.hasNext()) {
                AdjacencyListNode adjacencyListNode = iterator.next();
                if (!isVisited[adjacencyListNode.getIndex()]) {
                    topologicalSortUtil(adjacencyListNode.getIndex(), isVisited, stack);
                }
            }

            stack.push(vertex);
        }

        /*
        Same as single-source shortest path algorithm except 2 changes:
        1. Set dis[node] = NEGATIVE_INFINITY for all nodes initially.
        2. If condition reverses:
            if dis[node] != NEGATIVE_INFINITY && dis[adjacent node] < dis[node] + weight[adjacent node]
                dis[adjacent node] = dis[node] + weight[adjacent node]
        */
        void longestPath(int source) {
            // First do topological sorting
            boolean isVisited[] = new boolean[v];
            Deque<Integer> stack = new LinkedList<>();

            for (int i = 0; i < v; i++) {
                if (!isVisited[i]) {
                    topologicalSortUtil(i, isVisited, stack);
                }
            }
            // stack is now topologically sorted

            // Distance Array for storing distances and Parent Array for storing path
            int distances[] = new int[v];
            int parent[] = new int[v];

            // Initialize the distance and parent arrays
            for (int i = 0; i < v; i++) {
                distances[i] = Integer.MIN_VALUE;  
                parent[i] = -1;                
            }
            distances[source] = 0;

            // Update distances in a loop
            while (!stack.isEmpty()) {
                int vertex = stack.pop();

                if (distances[vertex] != Integer.MIN_VALUE) {
                    Iterator<AdjacencyListNode> iterator = adjacencyList[vertex].iterator();
                    while (iterator.hasNext()) {
                        AdjacencyListNode newNode = iterator.next();
                        if (distances[newNode.getIndex()] < distances[vertex] + newNode.getWeight()) {
                            // Update distance
                            distances[newNode.getIndex()] = distances[vertex] + newNode.getWeight();

                            // Update parent
                            parent[newNode.getIndex()] = vertex;
                        }
                    }
                }
            }

            // Print Distances and Path
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < v; i++) {
                if (distances[i] == Integer.MIN_VALUE) {
                    result.append(i + ": INFINITY \tPath: Not Found\n");
                } else {
                    result.append(i + ": " + distances[i] + "\tPath: ");

                    Deque<Integer> pathToThisNodeStack = new LinkedList<>();
                    int j = i;
                    while (parent[j] != -1) {
                        pathToThisNodeStack.push(j);
                        j = parent[j];
                    }
                    pathToThisNodeStack.push(source);

                    while(!pathToThisNodeStack.isEmpty()) {
                        result.append(pathToThisNodeStack.pop() + " ");
                    }

                    result.append("\n");
                }
            }

            System.out.println(result.toString());
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(6); 
        g.addEdge(0, 1, 5); 
        g.addEdge(0, 2, 3); 
        g.addEdge(1, 3, 6); 
        g.addEdge(1, 2, 2); 
        g.addEdge(2, 4, 4); 
        g.addEdge(2, 5, 2); 
        g.addEdge(2, 3, 7); 
        g.addEdge(3, 5, 1); 
        g.addEdge(3, 4, -1); 
        g.addEdge(4, 5, -2); 

        System.out.println("Graph Display: ");
        g.display();

        int source = 1; 
        System.out.println("Longest Path Distances from Source " + source + ": ");
        g.longestPath(source); 
    }
}