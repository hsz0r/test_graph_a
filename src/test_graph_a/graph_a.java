/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_graph_a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import javafx.util.Pair;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hsz0r
 */
public class graph_a {

    int vertexes;
    boolean visit[] = new boolean[vertexes];
    int matrix[][];
    int numOfEdges = 0;

    public graph_a(int vertex) {
        this.vertexes = vertex;
        matrix = new int[vertex][vertex];
    }

    public class Edge {

        public int from;
        public int to;
        public int weight;

        Edge(int u, int v, int w) {
            this.from = u;
            this.to = v;
            this.weight = w;
        }

    }

    public void addEdge(int source, int destination, int weight) {
        matrix[source][destination] = weight;
        matrix[destination][source] = weight;
        numOfEdges++;
    }

    @Override
    public String toString() {
        for (int i = 0; i < vertexes; i++) {
            for (int j = 0; j < vertexes; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        return null;
    }

    public void bfs(int source) {
        boolean[] visited = new boolean[matrix.length];
        visited[source] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            System.out.print(queue.peek() + " ");
            int x = queue.poll();
            int i;
            for (i = 0; i < matrix.length; i++) {
                if (matrix[x][i] == 1 && visited[i] == false) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    public void dfs(int source) {
        int[][] Array = CopyMatrix();
        int v = matrix.length;
        for (int i = 0; i < vertexes; i++) {
            visit[i] = false;
        }
        dfs_help(source);

    }

    private void dfs_help(int source) {
        visit[source] = true;
        System.out.print(source + " ");
        int j = 0;

        while ((j < vertexes - 1) && ((matrix[j][source] == 0) || (visit[j]
                == true))) {
            j++;
            if ((matrix[j][source] != 0) && (visit[j] == false)) {

                dfs(j);
            }

        }
    }

    public void dijkstra(int vertex) {
        int[][] Array = CopyMatrix();
        int D[] = new int[vertexes];

        for (int i = 0; i < vertexes; i++) {
            D[i] = 1000000;
        }

        for (int i = 0; i < vertexes; i++) {
            if ((Array[vertex][i] != 0) && (visit[i] == false)) {
                D[i] = Array[vertex][i];
                visit[i] = false;
            }
        }
        D[vertex] = 0;
        int index = 0, k = 0;

        for (int i = 0; i < vertexes; i++) {
            int min = 1000000;
            for (int j = 0; j < vertexes; j++) {
                if ((D[j] < min) && (visit[j] == false)) {
                    min = D[j];
                    index = j;
                }
            }
            k = index;
            visit[k] = true;
            for (int j = 0; j < vertexes; j++) {
                if ((!visit[j]) && (Array[k][j] != 0) && (D[k] != 1000000)
                        && (D[k] + Array[k][j] < D[j])) {
                    D[j] = D[k] + Array[k][j];
                }
            }

        }

        for (int i = 0; i < vertexes; i++) {
            System.out.print(D[i] + "  ");
        }
    }

    private ArrayList<Edge> get_edges() {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < vertexes; i++) {
            for (int j = 0; j < vertexes; j++) {
                if (matrix[i][j] != 0 && j > i) {
                    edges.add(new Edge(i, j, matrix[i][j]));
                }
            }
        }
        return edges;
    }

    class SortByCost implements Comparator<Edge> {

        public int compare(Edge a, Edge b) {
            if (a.weight < b.weight) {
                return -1;
            } else if (a.weight == b.weight) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public ArrayList<Pair<Integer, Integer>> kruskal() {
        ArrayList<Edge> edges = get_edges();
        Collections.sort(edges, new SortByCost());
        int size = vertexes;
        ArrayList<Pair<Integer, Integer>> result = new ArrayList<>();
        ArrayList<Integer> tree_id = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            tree_id.add(i);
        }
        for (int i = 0; i < size; i++) {
            int from = edges.get(i).from;
            int to = edges.get(i).to;
            int weight = edges.get(i).weight;
            if (tree_id.get(from) != tree_id.get(to)) {
                result.add(new Pair<Integer, Integer>(from, to));
                int old_id = tree_id.get(to), new_id = tree_id.get(from);
                for (int j = 0; j < size; j++) {
                    if (tree_id.get(j) == old_id) {
                        tree_id.set(j, new_id);
                    }
                }
            }
        }

        return result;
    }

    public void prima() {
        int[][] Array = CopyMatrix();
        int D[] = new int[vertexes];
        int Parent[] = new int[vertexes];
        for (int i = 0; i < vertexes; i++) {
            D[i] = 1000000;
            Parent[i] = -1;
            visit[i] = false;
        }

        for (int i = 0; i < vertexes; i++) {
            if ((Array[vertexes - 1][i] != 0) && (visit[i] == false)) {
                D[i] = Array[vertexes - 1][i];
                Parent[i] = vertexes - 1;
            }
        }

        D[vertexes - 1] = 0;
        int index = 0, k = 0;

        for (int i = 0; i < vertexes; i++) {
            int min = 1000000;
            for (int j = 0; j < vertexes; j++) {
                if ((D[j] < min) && (visit[j] == false)) {
                    min = D[j];
                    index = j;
                }
            }
            k = index;
            visit[k] = true;
            for (int j = 0; j < vertexes; j++) {
                if ((!visit[j]) && (Array[k][j] != 0) && (D[k] != 1000000)
                        && (Array[k][j] < D[j])) {
                    D[j] = Array[k][j];
                    Parent[j] = k;
                }
            }

        }

        System.out.print("\n");
        for (int i = 0; i < vertexes; i++) {
            System.out.print("(" + (i + 1) + ", " + (Parent[i] + 1) + ") ");
        }

    }

    private int[][] CopyMatrix() {
        int vertexes = matrix.length;
        int[][] Array = new int[vertexes][vertexes];
        visit = new boolean[vertexes];
        for (int i = 0; i < vertexes; i++) {
            for (int j = 0; j < vertexes; j++) {
                Array[i][j] = matrix[i][j];
            }
        }
        return Array;
    }

    public void floydWarshall() {
        int temp[][] = new int[vertexes][vertexes];
        for (int i = 0; i < vertexes; i++) {
            for (int j = 0; j < vertexes; j++) {
                if (matrix[i][j] == 0 && i != j) {
                    temp[i][j] = 1000000;
                } else {
                    temp[i][j] = matrix[i][j];
                }
            }
        }
        for (int i = 0; i < vertexes; i++) {
            for (int j = 0; j < vertexes; j++) {
                for (int k = 0; k < vertexes; k++) {
                    if (temp[i][j] <= temp[i][k] + temp[k][j]) {
                        temp[i][j] = temp[i][j];
                    }
                    if (temp[i][j] > temp[i][k] + temp[k][j]) {
                        temp[i][j] = temp[i][k] + temp[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < vertexes; i++) {
            for (int j = 0; j < vertexes; j++) {
                System.out.print(temp[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
