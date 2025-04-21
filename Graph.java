/******************************************************************
 *
 *   BEN FLOWERS / COMP 272/400C 002
 *
 *   Note, additional comments provided throughout this source code
 *   is for educational purposes
 *
 ********************************************************************/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 *  Graph traversal exercise
 *
 *  The Graph class is a representing an oversimplified Directed Graph of vertices
 *  (nodes) and edges. The graph is stored in an adjacency list
 */

public class Graph {
    int numVertices;                  // vertices in graph
    LinkedList<Integer>[] adjListArr; // Adjacency list
    List<Integer> vertexValues;       // vertex values

    // Constructor
    public Graph(int numV) {
        numVertices = numV;
        adjListArr = new LinkedList[numVertices];
        vertexValues = new ArrayList<>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            adjListArr[i] = new LinkedList<>();
            vertexValues.add(0);
        }
    }

    /*
     * method setValue
     *
     * Sets a vertex's (node's) value.
     */

    public void setValue(int vertexIndex, int value) {
        if (vertexIndex >= 0 && vertexIndex < numVertices) {
            vertexValues.set(vertexIndex, value);
        } else {
            throw new IllegalArgumentException(
                    "Invalid vertex index: " + vertexIndex);
        }
    }


    public void addEdge(int src, int dest) {
        adjListArr[src].add(dest);
    }

    /*
     * method printGraph
     *
     * Prints the graph as an adjacency matrix
     */

    public void printGraph() {
        System.out.println(
                "\nAdjacency Matrix Representation:\n");
        int[][] matrix = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (Integer dest : adjListArr[i]) {
                matrix[i][dest] = 1;
            }
        }

        System.out.print("  ");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < numVertices; j++) {
                if (matrix[i][j] == 1) {
                    System.out.print("| ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }


    /**
     * method findRoot
     *
     * This method returns the value of the root vertex, where root is defined in
     * this case as a node that has no incoming edges. If no root vertex is found
     * and/or more than one root vertex, then return -1.
     *
     */

    public int findRoot() {
        
        // count incoming edges for each vertex
        int[] incomingEdges = new int[numVertices];

        // traverse adjacency lists to count incoming edges for each vertex
        for (int i = 0; i < numVertices; i++) {
            for (Integer dest : adjListArr[i]) {
                incomingEdges[dest]++;
            }
        }

        // find vertices without incoming edges
        int rootCount = 0;
        int rootVertex = -1;

        for (int i = 0; i < numVertices; i++) {
            if (incomingEdges[i] == 0) {
                rootCount++;
                rootVertex = i;
            }
        }

        // return root value if exactly one root exists, otherwise return -1
        if (rootCount == 1) {
            return vertexValues.get(rootVertex);
        } else {

        }
        return -1;
    }
}
