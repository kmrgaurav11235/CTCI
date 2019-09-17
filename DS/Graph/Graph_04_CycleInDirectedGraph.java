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