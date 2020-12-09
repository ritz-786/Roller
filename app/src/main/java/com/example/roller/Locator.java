package com.example.roller;

import android.util.Log;

import com.example.roller.domain.House;
import com.example.roller.domain.LocatedAt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Locator {

    public void initiator(House start) {
        List<House> houses = Data.getHouses();
        int V = houses.size();

        List<List<Node>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<>();
            adj.add(item);
        }

        for (House house : houses) {
            List<Node> nodes = new ArrayList<>();
            for (House otherHouse : houses) {
                if (otherHouse.getId() != house.getId()) {
                    float distance = Util.findDistance(house.getLocation(), otherHouse.getLocation());
                    nodes.add(new Node(otherHouse.getId(), distance));
                }
            }
            adj.set(house.getId(), nodes);
        }

        Djikstra djikstra = new Djikstra(V);
        djikstra.dijkstra(adj, start.getId());

        System.out.println("The Shortest Path from node : ");
        Log.d("MyString", "The Shortest Path from node : ");
        djikstra.printSolution(start.getId(), djikstra.dist, djikstra.parent);
    }

}

class Djikstra {
    float[] dist;
    int[] parent;
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V;
    List<List<Node>> adj;

    public Djikstra(int V) {
        this.V = V;
        dist = new float[V];
        parent = new int[V];
        settled = new HashSet<>();
        pq = new PriorityQueue<>(V, new Node());
    }

    public void dijkstra(List<List<Node>> adj, int src) {
        this.adj = adj;
        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;
        pq.add(new Node(src, 0));
        parent[src] = -1;

        dist[src] = 0;
        while (settled.size() != V) {
            int u = pq.remove().node;
            settled.add(u);
            settle_neighbors(u);
        }
    }

    private void settle_neighbors(int u) {
        float edgeDistance = -1;
        float newDistance = -1;

        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                if (newDistance < dist[v.node]) {
                    dist[v.node] = newDistance;
                    parent[v.node] = u;
                }

                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }

    void printSolution(int startVertex, float[] distances, int[] parents) {
        int nVertices = distances.length;
        System.out.print("Vertex\t Distance\tPath");

        for (int vertexIndex = 0;
             vertexIndex < nVertices;
             vertexIndex++) {
            if (vertexIndex != startVertex) {
                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                System.out.print(distances[vertexIndex] + "\t\t");
                printPath(vertexIndex, parents);
            }
        }
    }

    void printPath(int currentVertex,
                   int[] parents) {

        // Base case : Source node has
        // been processed
        if (currentVertex == -1) {
            return;
        }
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + " ");
    }
}

class Node implements Comparator<Node> {

    public int node;
    public float cost;

    public Node() {
    }

    public Node(int node, float cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2) {
        return Float.compare(node1.cost, node2.cost);
    }
}

