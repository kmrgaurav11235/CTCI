/*
https://www.geeksforgeeks.org/topological-sorting/
- Topological Sorting: Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering 
    of vertices such that for every directed edge uv, vertex u comes before v in the ordering. 
- Topological Sorting for a graph is not possible if the graph is not a DAG.
- There can be more than one topological sorting for a graph.
- The first vertex in topological sorting is always a vertex with in-degree as 0 (a vertex with 
    no incoming edges).
- Topological Sorting vs Depth First Traversal (DFS):
    In DFS, we print a vertex and then recursively call DFS for its adjacent vertices. In 
    topological sorting, we need to print a vertex before its adjacent vertices. 
- We can modify DFS to find Topological Sorting of a graph. In topological sorting, we use a 
    temporary stack. We don’t print the vertex immediately, we first recursively call topological 
    sorting for all its adjacent vertices, then push it to a stack. Finally, print contents of 
    stack. 
- Note that a vertex is pushed to stack only when all of its adjacent vertices (and their adjacent 
    vertices and so on) are already in stack. 
- Complexity Analysis:
    * Time Complexity: O(V+E).
        The algorithm is simply DFS with an extra stack. So time complexity is the same as DFS which is.
    * Auxiliary space: O(V).
        The extra space is needed for the stack.
- Applications: Topological Sorting is mainly used for scheduling jobs from the given dependencies 
    among jobs. In computer science, applications of this type arise in instruction scheduling, determining 
    the order of compilation tasks to perform in make-files, data serialization, and resolving symbol 
    dependencies in linkers.
*/
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

class Graph_06_TopologicalSorting {
    static class Graph {
        private int v;
        private LinkedList<Integer> adjacencyList[];

        Graph(int v) {
            this.v = v;
            adjacencyList = new LinkedList[v];

            for (int i = 0; i < v; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }

        boolean addEdge(int source, int destination) {
            if (source >= 0 && destination >= 0 && source < v && destination < v) {
                adjacencyList[source].add(destination);
                return true;
            }
            return false;
        }
        
        void topologicalSortUtil(int vertex, boolean [] isVisited, Deque<Integer> stack) {
            if (isVisited[vertex]) {
                return;
            }
            isVisited[vertex] = true;

            Iterator<Integer> iterator = adjacencyList[vertex].iterator();
            while (iterator.hasNext()) {
                int newVertex = iterator.next();
                if (!isVisited[newVertex]) {
                    topologicalSortUtil(newVertex, isVisited, stack);
                }
            }

            stack.push(vertex);
        }

        void topologicalSort() {
            boolean isVisited[] = new boolean[v];
            Deque<Integer> stack = new LinkedList<>();

            for (int i = 0; i < v; i++) {
                if (!isVisited[i]) {
                    topologicalSortUtil(i, isVisited, stack);
                }
            }

            StringBuilder sort = new StringBuilder();
            while (!stack.isEmpty()) {
                sort.append(stack.pop() + " ");
            }
            System.out.println(sort.toString());
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(6); 
        g.addEdge(5, 2); 
        g.addEdge(5, 0); 
        g.addEdge(4, 0); 
        g.addEdge(4, 1); 
        g.addEdge(2, 3); 
        g.addEdge(3, 1); 
  
        System.out.println("Following is a Topological " + 
                           "sort of the given graph"); 
        g.topologicalSort();
    }
}