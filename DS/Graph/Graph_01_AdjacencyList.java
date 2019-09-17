import java.util.LinkedList;

public class Graph_01_AdjacencyList {
    static class Graph {
        private int numVertices;
        private LinkedList <Integer> adjacencyLinkedListArray[];

        Graph(int numVertices) {
            this.numVertices = numVertices;
            adjacencyLinkedListArray = new LinkedList[numVertices];
            for(int i = 0; i < numVertices; i++) {
                adjacencyLinkedListArray[i] = new LinkedList<>();
            }
        }

        boolean addEdge(int u, int v) {
            if (0 <= u && 0 <= v && u < numVertices && v < numVertices) {
                adjacencyLinkedListArray[u].add(v);
                adjacencyLinkedListArray[v].add(u);
                return true;
            }
            return false;
        }

        void displayGraph() {
            for (int i = 0; i < numVertices; i++) {
                System.out.print("Adjacency List at Vertex " + i + "\nHEAD -> ");
                for (int vertex : adjacencyLinkedListArray[i]) {
                    System.out.print(vertex + " -> ");                    
                }
                System.out.print("null\n");
            }
        }
    }// end of Graph

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1); 
        g.addEdge(0, 4); 
        g.addEdge(1, 2); 
        g.addEdge(1, 3); 
        g.addEdge(1, 4); 
        g.addEdge(2, 3); 
        g.addEdge(3, 4); 

        g.displayGraph();
    }
}