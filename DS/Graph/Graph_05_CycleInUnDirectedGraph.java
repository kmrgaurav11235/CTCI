import java.util.Iterator;
import java.util.LinkedList;

class Graph_05_CycleInUnDirectedGraph{
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
                adjacencyListArray[destination].add(source);
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

        boolean isCyclePresentUtil(int vertex, boolean [] isVisited, int parent) {
            if (isVisited[vertex]) {
                // This vertex has already been evaluated.
                return false;
            }
            isVisited[vertex] = true;
            // Check adjacent nodes
            Iterator<Integer> iterator = adjacencyListArray[vertex].iterator();
            while(iterator.hasNext()) {
                int newVertex = iterator.next();
                if (!isVisited[newVertex]) {
                    if (isCyclePresentUtil(newVertex, isVisited, vertex)) {
                        return true;
                    }
                } // isVisited[vertex] == true is implicit when flow reaches else.
                else if (newVertex != parent) {
                    // We havr already reached newVertex. Also, newVertex is not the parent.
                    // So, loop is present.
                    return true;
                }
            }
                
            return false;
        }

        boolean isCyclePresent() {
            boolean isVisited[] = new boolean[numVertices];

            for (int i = 0; i < numVertices; i++) {
                if (!isVisited[i]) {
                    if (isCyclePresentUtil(i, isVisited, -1)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        Graph g1 = new Graph(5); 
        g1.addEdge(1, 0); 
        g1.addEdge(0, 2); 
        g1.addEdge(2, 1); 
        g1.addEdge(0, 3); 
        g1.addEdge(3, 4); 

        System.out.println("G1 Graph:");
        g1.display();

        if (g1.isCyclePresent()) {
            System.out.println("G1 Graph contains cycle"); 
        } else {
            System.out.println("G1 Graph doesn't contains cycle"); 
        }
  
        Graph g2 = new Graph(3); 
        g2.addEdge(0, 1); 
        g2.addEdge(1, 2); 

        System.out.println("------------------------------");
        System.out.println("G2 Graph:");
        g2.display();

        if (g2.isCyclePresent()) {
            System.out.println("G2 Graph contains cycle"); 
        } else {
            System.out.println("G2 Graph doesn't contains cycle"); 
        }
    }

}