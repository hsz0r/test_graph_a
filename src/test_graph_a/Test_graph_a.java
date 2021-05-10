/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_graph_a;


/**
 *
 * @author hsz0r
 */
public class Test_graph_a {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(0, 3, 3);
        graph.addEdge(0, 4, 6);
        graph.addEdge(2, 0, 9);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 0, 1);
        graph.addEdge(3, 1, 1);
        graph.addEdge(3, 4, 7);
        graph.addEdge(4, 0, 7);
        graph.addEdge(4, 1, 7);
        graph.toString();

        System.out.println("\n");
        System.out.println("=======================floydWarshall=======================");
        graph.floydWarshall();
        System.out.println("=======================prima=======================");
        graph.prima();
        System.out.println("\n=======================dfs=======================");
        graph.dfs(0);
        System.out.println("\n=======================bfs=======================");
        graph.bfs(0);
        System.out.println("\n=======================dijkstra=======================");
        graph.dijkstra(0);
        System.out.println("\n=======================kruskal=======================");
        System.out.println(graph.kruskal().toString());
    }
}
