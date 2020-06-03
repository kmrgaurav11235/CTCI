/*
https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
https://www.geeksforgeeks.org/applications-of-depth-first-search/

- Depth First Traversal (or Search) for a graph is similar to Depth First Traversal of a tree. 
    The only catch here is, unlike trees, graphs may contain cycles, a node may be visited twice. 
    To avoid processing a node more than once, use a boolean visited array. 
- The algorithm starts at the root node (selecting some arbitrary node as the root node) and 
    explores as far as possible along each branch before backtracking. So the basic idea is to 
    start from the root or any arbitrary node and mark the node and move to the adjacent unmarked 
    node and continue this loop until there is no unmarked adjacent node. Then backtrack and check 
    for other unmarked nodes and traverse them. Finally print the nodes in the path.
- Algorithm:
    1) Create a recursive function that takes the index of node and a visited array.
    2) Mark the current node as visited and print the node.
    3) Traverse all the adjacent and unmarked nodes and call the recursive function with index of 
        adjacent node.
-  Complexity Analysis:
    * Time complexity: O(V + E), 
        where V is the number of vertices and E is the number of edges in the graph.
    * Space Complexity: O(V).
        Since, an extra visited array is needed of size V.

- Applications of Depth First Search:
    1) For a weighted graph, DFS traversal of the graph produces the minimum spanning tree and all 
        pair shortest path tree.
    2) Detecting cycle in a graph: A graph has cycle if and only if we see a back edge during DFS. 
    3) Path Finding: We can specialize the DFS algorithm to find a path between two given vertices 
        u and z.
        * Call DFS(G, u) with u as the start vertex.
        * Use a stack S to keep track of the path between the start vertex and the current vertex.
        * As soon as destination vertex z is encountered, return the path as the contents of the stack.
    4) Topological Sorting
    5) To test if a graph is bipartite: We can augment either BFS or DFS when we first discover a new 
        vertex, color it opposite of its parents, and for each other edge, check it doesn’t link two 
        vertices of the same color. 
    6) Finding Strongly Connected Components of a graph: A directed graph is called strongly connected 
        if there is a path from each vertex in the graph to every other vertex.
    7) Solving puzzles with only one solution, such as mazes: DFS can be adapted to find all solutions 
        to a maze by only including nodes on the current path in the visited set.
*/
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