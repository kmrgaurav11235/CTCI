/*
Shortest Path Algorithms: 
Single Source shortest path for any Graph (with or without negative weights) -> Bellman-Ford
Single Source shortest path for Graph without negative weights -> Dijkstra
Single Source shortest path for DAG (with or without negative weights) -> Using Topological Sort
*/

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

class Graph_07_ShortestPathDAG_TopologicalSorting {
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
        To find the single-source shortest distances for this Graph, we will do the following:
        1. Topologically sort the Graph.
        2. Set dis[node] = INFINITY for all nodes.
        3. Set dis[source] = 0
        4. Go through the topologically sorted list and set the shortest distance for each node:
            if dis[node] != INF && dis[adjacent node] > dis[node] + weight[adjacent node]
                dis[adjacent node] = dis[node] + weight[adjacent node]
        */
        void shortestPath(int source) {
            // First do topological sorting
            boolean isVisited[] = new boolean[v];
            Deque<Integer> stack = new LinkedList<>();

            for (int i = 0; i < v; i++) {
                if (!isVisited[i]) {
                    topologicalSortUtil(i, isVisited, stack);
                }
            }
            // stack is now topologically sorted

            // Initialize the distance array
            int distances[] = new int[v];
            for (int i = 0; i < v; i++) {
                distances[i] = Integer.MAX_VALUE;                
            }
            distances[source] = 0;

            // Update distances in a loop
            while (!stack.isEmpty()) {
                int vertex = stack.pop();

                if (distances[vertex] != Integer.MAX_VALUE) {
                    Iterator<AdjacencyListNode> iterator = adjacencyList[vertex].iterator();
                    while (iterator.hasNext()) {
                        AdjacencyListNode newNode = iterator.next();
                        if (distances[newNode.getIndex()] > distances[vertex] + newNode.getWeight()) {
                            distances[newNode.getIndex()] = distances[vertex] + newNode.getWeight();
                        }
                    }
                }
            }

            // Print Distances
            StringBuilder result = new StringBuilder();
            for (int distance : distances) {
                if (distance == Integer.MAX_VALUE) {
                    result.append("INFINITY ");
                } else {
                    result.append(distance + " ");
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
        g.addEdge(3, 4, -1); 
        g.addEdge(4, 5, -2); 

        System.out.println("Graph Display: ");
        g.display();

        int source = 1; 
        System.out.println("Shortest Path Distances from Source " + source + ": ");
        g.shortestPath(source); 
    }
}