/******************************************************************
 *
 *   BEN FLOWERS / COMP 272/400C 002
 *
 *   This java file contains the problem solutions of canFinish and
 *   numGroups methods.
 *
 ********************************************************************/

import java.util.*;

class ProblemSolutions {

    /**
     * Method canFinish
     *
     * You are building a course curriculum along with required intermediate
     * exams certifications that must be taken by programmers in order to obtain
     * a new certification called 'master programmer'. In doing so, you are placing
     * prerequisites on intermediate exam certifications that must be taken first.
     *
     * Your method expects a 2-dimensional array of exam prerequisites, where
     * prerequisites[i] = [ai, bi] indicating that you must take exam 'bi' first
     * if you want to take exam 'ai'.
     *
     * The method will return true if you can finish all certification exams.
     * Otherwise, return false (e.g., meaning it is a cyclic or cycle graph).
     *
     * @param numExams      - number of exams, which will produce a graph of n nodes
     * @param prerequisites - 2-dim array of directed edges.
     * @return boolean      - True if all exams can be taken, else false.
     */
    public boolean canFinish(int numExams, int[][] prerequisites) {

        // build graphand arrau
        ArrayList<Integer>[] adj = new ArrayList[numExams];
        int[] preReqCount = new int[numExams];

        for (int i = 0; i < numExams; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] prereq : prerequisites) {
            int to = prereq[0];
            int from = prereq[1];
            adj[from].add(to);
            preReqCount[to]++;
        }

        // add all nodes with prereq count 0 to queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numExams; i++) {
            if (preReqCount[i] == 0) {
                queue.offer(i);
            }
        }

        // processing nodes with bfs
        int count = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;

            for (int neighbor : adj[curr]) {
                preReqCount[neighbor]--;
                if (preReqCount[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // valid schedule if all exams are processed
        return count == numExams;
    }



    /**
     * Method getAdjList
     *
     * Building an Adjacency List for the directed graph based on number of nodes
     * and passed in directed edges.
     *
     * @param numNodes - number of nodes in graph (labeled 0 through n-1)
     * @param edges    - 2-dim array of directed edges
     * @return ArrayList<Integer>[] - An adjacency list representing the graph
     */
    private ArrayList<Integer>[] getAdjList(int numNodes, int[][] edges) {
        ArrayList<Integer>[] adj = new ArrayList[numNodes];
        for (int node = 0; node < numNodes; node++) {
            adj[node] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
        }
        return adj;
    }

    /**
     * Method numGroups
     *
     * Given an undirected adjacency matrix representing connections between people,
     * return the number of disconnected groups (connected components) in the graph.
     *
     * @param adjMatrix - adjacency matrix representing graph connections
     * @return int - number of groups (connected components)
     */
    public int numGroups(int[][] adjMatrix) {
        int numNodes = adjMatrix.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (adjMatrix[i][j] == 1 && i != j) {
                    graph.putIfAbsent(i, new ArrayList<>());
                    graph.putIfAbsent(j, new ArrayList<>());
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        boolean[] visited = new boolean[numNodes];
        int groups = 0;

        for (int i = 0; i < numNodes; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited);
                groups++;
            }
        }

        return groups;
    }

    /**
     * Helper method for DFS traversal
     *
     * @param node    - current node
     * @param graph   - adjacency list of graph
     * @param visited - boolean array tracking visited nodes
     */
    private void dfs(int node, Map<Integer, List<Integer>> graph, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        visited[node] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(neighbor);
                }
            }
        }
    }
}
