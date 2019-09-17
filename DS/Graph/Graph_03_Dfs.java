import java.util.Iterator;
import java.util.LinkedList;

class Graph_03_Dfs {
    static class Graph {
        private int numVertices;
        private LinkedList <Integer> adjacencyListArray[];

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

        private void dfsUtil(int vertex, boolean [] isVisited) {
            System.out.print(" -> " + vertex);
            isVisited[vertex] = true;

            Iterator <Integer> iterator = adjacencyListArray[vertex].iterator();
            while(iterator.hasNext()) {
                int newVertex = iterator.next();
                if (!isVisited[newVertex]) {
                    dfsUtil(newVertex, isVisited);
                }
            }
        }

        void dfs(int vertex) {
            boolean isVisited[] = new boolean[numVertices];

            System.out.println("DFS: ");
            dfsUtil(vertex, isVisited);
            for (int i = 0; i < numVertices; i++) {
                if (!isVisited[i]) {
                    dfsUtil(i, isVisited);
                }
            }
            System.out.println();
        }
    }

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

        g.dfs(4);
        g.dfs(5);
    }
}