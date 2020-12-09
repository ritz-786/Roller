package com.example.roller;

import com.example.roller.domain.House;
import com.example.roller.domain.LocatedAt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Locator {

    List<House> getHouses() {
        List<House> houses = new ArrayList<>();
        houses.add(new House(0, new LocatedAt(25.566666666666666, 84.53333333333333), "Arrah", "Bihar"));
        houses.add(new House(1, new LocatedAt(24.75, 84.41666666666667), "Aurangabad", "Bihar"));
        houses.add(new House(2, new LocatedAt(25.666666666666668, 85.2), "Bankipore", "Bihar"));
        houses.add(new House(3, new LocatedAt(23.166666666666668, 84.21666666666667), "Barwa", "Bihar"));
        houses.add(new House(4, new LocatedAt(26.8, 84.55), "Bettiah", "Bihar"));
        houses.add(new House(5, new LocatedAt(25.25, 87.01666666666667), "Bhagalpur", "Bihar"));
        houses.add(new House(6, new LocatedAt(24.683333333333334, 85.03333333333333), "Buddh Gaya", "Bihar"));
        houses.add(new House(7, new LocatedAt(25.566666666666666, 84.01666666666667), "Buxar", "Bihar"));
        houses.add(new House(8, new LocatedAt(25.783333333333335, 84.78333333333333), "Chhapra", "Bihar"));
        houses.add(new House(9, new LocatedAt(25.266666666666666, 87.28333333333333), "Colgong", "Bihar"));
        houses.add(new House(10, new LocatedAt(26.166666666666668, 85.95), "Darbhanga (Laheriasarai)", "Bihar"));
        houses.add(new House(11, new LocatedAt(25.233333333333334, 84.28333333333333), "Dhankkgain", "Bihar"));
        houses.add(new House(12, new LocatedAt(25.633333333333333, 85.08333333333333), "Dinapore", "Bihar"));
        houses.add(new House(13, new LocatedAt(25.55, 84.2), "Dumraon", "Bihar"));
        houses.add(new House(14, new LocatedAt(26.5, 84.5), "Gandak R.", "Bihar"));
        houses.add(new House(15, new LocatedAt(42.81666666666667, 85.01666666666667), "Gaya", "Bihar"));
        houses.add(new House(16, new LocatedAt(25.466666666666665, 86.63333333333334), "Gogri", "Bihar"));
        houses.add(new House(17, new LocatedAt(26.466666666666665, 84.43333333333334), "Gopalganj", "Bihar"));
        houses.add(new House(18, new LocatedAt(25.683333333333334, 85.95), "Hajipur", "Bihar"));
        houses.add(new House(19, new LocatedAt(25.316666666666666, 85.53333333333333), "Jamalpur", "Bihar"));
        houses.add(new House(20, new LocatedAt(26.583333333333332, 86.15), "Jyanagar", "Bihar"));
        houses.add(new House(21, new LocatedAt(26.516666666666666, 85.35), "Kantai", "Bihar"));
        houses.add(new House(22, new LocatedAt(25.5, 87.66666666666667), "Kathihar", "Bihar"));
        houses.add(new House(23, new LocatedAt(26.166666666666668, 87.03333333333333), "Kishanganj", "Bihar"));
        houses.add(new House(24, new LocatedAt(23.666666666666668, 84.55), "Mankheri", "Bihar"));
//        houses.add(new House(25, new LocatedAt(25.4, 85.91666666666667), "Mokameh", "Bihar"));
//        houses.add(new House(26, new LocatedAt(26.666666666666668, 85.23333333333333), "Motihari", "Bihar"));
//        houses.add(new House(27, new LocatedAt(25.383333333333333, 86.5), "Munger", "Bihar"));
//        houses.add(new House(28, new LocatedAt(26.116666666666667, 85.45), "Muzaffarpur", "Bihar"));
//        houses.add(new House(29, new LocatedAt(24.883333333333333, 85.58333333333333), "Nawada", "Bihar"));
//        houses.add(new House(30, new LocatedAt(24.5, 87.33333333333333), "Naya Dumka", "Bihar"));
//        houses.add(new House(31, new LocatedAt(25.616666666666667, 85.21666666666667), "Patna", "Bihar"));
//        houses.add(new House(32, new LocatedAt(25.816666666666666, 87.51666666666667), "Purnia", "Bihar"));
//        houses.add(new House(33, new LocatedAt(22.266666666666666, 85.25), "Safanda", "Bihar"));
//        houses.add(new House(34, new LocatedAt(26.783333333333335, 84.8), "Sagaull", "Bihar"));
//        houses.add(new House(35, new LocatedAt(25.916666666666668, 86.58333333333333), "Saharsa", "Bihar"));
//        houses.add(new House(36, new LocatedAt(25.916666666666668, 85.08333333333333), "Samastipur", "Bihar"));
//        houses.add(new House(37, new LocatedAt(24.95, 84.05), "Sasaram", "Bihar"));
//        houses.add(new House(38, new LocatedAt(25.15, 85.88333333333334), "Shelkhupura", "Bihar"));
//        houses.add(new House(39, new LocatedAt(24.95, 84.88333333333334), "Tekari", "Bihar"));

        return houses;
    }

    public void initiator(House start) {
        List<House> houses = getHouses();
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
//        for (int i = 0; i < djikstra.dist.length; i++) {
//            System.out.println(start.getId() + " to " + i + " is "
//                    + djikstra.dist[i]);
//        }
        djikstra.printSolution(start.getId(),djikstra.dist,djikstra.parent);
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

