/*
Topological Sorting sorts the vertices of a Directed Acyclic Graph (DAG) in such a way that for 
every edge uv in the graph, u comes before v in the sorted list.
Topololical Sorting is not possible if the Graph in not directed and acyclic.
A number of different topological sorts are generally possible for a single DAG.
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

        /*
        Topological Sorting is similar to DFS. In DFS, we visit every vertex, print it and then call DFS on its adjacent vertex.
        In Topological Sorting we use a stack. We first call Topological Sort on all adjacent vertex, then push the vertex in stack.
        This ensures that a vertex is pushed in the stack only after all the adjacent vertices are already in the stack. At the end,
        we empty the stack to get the sorted list.
        */
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