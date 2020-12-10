package com.example.roller;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.roller.domain.House;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

public class Locator {

    public void initiator(House start,House end) {
        List<House> houses = Data.getHouses();
        int V = houses.size();

//        HashMap<Integer, List<Node>> adj = new HashMap<>();

//        for (House house : houses) {
//            List<Node> nodes = new ArrayList<>();
//            for (House otherHouse : houses) {
//                if (otherHouse.getId() != house.getId()) {
//                    float distance = Util.findDistance(house.getLocation(), otherHouse.getLocation());
//                    nodes.add(new Node(otherHouse.getId(), distance));
//                }
//            }
//            adj.put(house.getId(), nodes);
//        }
        Data.connecting();
        HashMap<Integer, List<Node>> adj = Data.adj;

        // Graph
        System.out.println("Graph of houses are as follows");
        System.out.println(adj);

        Djikstra djikstra = new Djikstra(V);
        djikstra.dijkstra(adj, start.getId());

//        System.out.println("The Shortest Path from node : ");
//        Log.d("MyString", "The Shortest Path from node : " + start);
//        for (int x : djikstra.dist.keySet()) {
//            Log.d("Djikstra", x + "-> " + djikstra.dist.get(x));
//        }

//        djikstra.printSolution(start.getId(), djikstra.dist, djikstra.parent);
        // Required Path
        djikstra.printPath(end.getId(),djikstra.parent);
        Log.d("Path:= ", djikstra.path);
    }

}

class Djikstra {
    HashMap<Integer, Double> dist;
    HashMap<Integer,Integer> parent;

    private final Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V;
    HashMap<Integer, List<Node>> adj;

    String path;

    public Djikstra(int V) {
        this.V = V;
        dist = new HashMap<>();
        parent = new HashMap<>();
        settled = new HashSet<>();
        path = "";
        pq = new PriorityQueue<>(V, new Node());
    }

    public void dijkstra(HashMap<Integer, List<Node>> adj, int src) {
        this.adj = adj;
        pq.add(new Node(src, 0));
        parent.put(src,-1);

        dist.put(src, 0.0);
        while (settled.size() != V) {
            int u = pq.remove().node;
            settled.add(u);
            settle_neighbors(u);
        }
    }

    private void settle_neighbors(int u) {
        float edgeDistance = -1;
        float newDistance = -1;

        for (int i = 0; i < Objects.requireNonNull(adj.get(u)).size(); i++) {
            Node v = Objects.requireNonNull(adj.get(u)).get(i);

            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                Double dis = dist.get(u);
                if (dis == null)
                    dis = (double) Integer.MAX_VALUE;

                newDistance = (float) (dis + edgeDistance);

                dis = dist.get(v.node);
                if (dis == null)
                    dis = (double) Integer.MAX_VALUE;

                if (newDistance < dis) {
                    dist.put(v.node, (double) newDistance);
                    parent.put(v.node,u);
                }

                pq.add(new Node(v.node, Float.parseFloat(String.valueOf(dist.get(v.node)))));
            }
        }
    }

    void printSolution(int startVertex, HashMap<Integer,Double> distances, HashMap<Integer,Integer> parents) {
        System.out.print("Vertex\t Distance\tPath");

        for (int vertexIndex: distances.keySet()){
            if(distances.get(vertexIndex) != startVertex){
                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                System.out.print(distances.get(vertexIndex) + "\t\t");
                printPath(vertexIndex, parents);
            }
        }
    }

    void printPath(int currentVertex,
                   HashMap<Integer,Integer> parents) {

        // Base case : Source node has
        // been processed
        if (currentVertex == -1) {
            return;
        }
        printPath(parents.get(currentVertex), parents);
        System.out.print(currentVertex + " ");
        path = currentVertex + "-> " + path;
    }
}

class Node implements Comparator<Node> {

    public  int node;
    public  float cost;

    @NonNull
    @Override
    public String toString() {
        return "" + node;
    }

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

