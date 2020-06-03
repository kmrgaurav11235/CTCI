/*
https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/

- Breadth First Traversal (or Search) for a graph is similar to Breadth First Traversal of a 
    tree. The only catch here is, unlike trees, graphs may contain cycles, so we may come to 
    the same node again. 
- To avoid processing a node more than once, we use a boolean visited array.
- Time Complexity: O(V+E) 
    where V is number of vertices in the graph and E is number of edges in the graph.
*/
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

class Graph_02_Bfs {
    static class Graph {
        private int numVertices;
        private LinkedList<Integer> adjacencyListArray[];

        Graph (int numVertices) {
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

        private void bfsUtil(boolean [] isVisited, Queue<Integer> queue) {
            while (!queue.isEmpty()) {
                int vertex = queue.poll();

                if (isVisited[vertex]) {
                    continue;
                } else {
                    // Visit the vertex
                    System.out.print(" -> " + vertex);
                    isVisited[vertex] = true;
                    // Put the adjacent vertices in queue 
                    Iterator<Integer> iterator = adjacencyListArray[vertex].iterator();
                    while (iterator.hasNext()) {
                        int newVertex = iterator.next();
                        if (!isVisited[newVertex]) {
                            queue.offer(newVertex);
                        }
                    }
                }
            }
        }

        void bfs(int vertex) {
            boolean isVisited[] = new boolean[numVertices]; // This array is initilized to false automatically

            Queue<Integer> queue = new LinkedList<Integer>();
            queue.offer(vertex);

            System.out.println("Dfs: ");
            bfsUtil(isVisited, queue);

            for (int i = 0; i < numVertices; i++) {
                if (!isVisited[i]) {
                    queue.offer(i);
                    bfsUtil(isVisited, queue);
                }
            }
            System.out.println("");
        }
    } // end of Graph

    public static void main(String[] args) {
        Graph g = new Graph(7);

        /*
        0 -> 4 -> 1
        |    /\    |
        \/   |    |
        3 -> 2 <--

        5 -> 6
        */
        g.addEdge(0, 3);
        g.addEdge(0, 4);
        g.addEdge(4, 1);
        g.addEdge(3, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 4);
        g.addEdge(5, 6);

        g.display();

        g.bfs(0);
        g.bfs(6);
    }
}