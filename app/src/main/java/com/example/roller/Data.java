package com.example.roller;

import android.util.Log;

import com.example.roller.domain.House;
import com.example.roller.domain.LocatedAt;
import com.example.roller.domain.Product;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {

    public static HashMap<Integer, List<Node>> adj = new HashMap<>();
    public static HashMap<String, House> directory = new HashMap<>();
    public static HashMap<Integer,House> idDirectory = new HashMap<>();

    static List<Order_info> orders = new ArrayList<>();
    public static long speed = 60; // km/hr

    public static List<Order_info> getOrders() {
        return orders;
    }

    public static void addOrder(Order_info order) {
        orders.add(order);
    }

    public static Order_info lastOrder(){
        int size = orders.size();
        if(size>0)
            return orders.get(size-1);
        else
            return null;
    }

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String uri = "@drawable/x";
        Product shoes = new Product(uri, "Shoes", 8);
        Product watch = new Product(uri, "Watch", 10);
        Product shirt = new Product(uri, "Shirt", 7);
        Product belt = new Product(uri, "Belt", 3);
        Product pant = new Product(uri, "Trousers", 7);
        Product socks = new Product(uri, "Socks", 5);
        Product laptop = new Product(uri, "Laptop", 20);

        products.add(shoes);
        products.add(watch);
        products.add(shirt);

        products.add(belt);
        products.add(pant);
        products.add(socks);
        products.add(laptop);

        return products;
    }

    public static List<House> getHouses() {
        List<House> houses = new ArrayList<>();
        List<Product> products = getProducts();

        HashMap<Product, Integer> productsMap = new HashMap<>();
        productsMap.put(products.get(0), 10);
        productsMap.put(products.get(1), 8);

        houses.add(new House(0, new LocatedAt(25.566666666666666, 84.53333333333333), "Arrah", "Bihar", productsMap));

        productsMap = new HashMap<>();
        productsMap.put(products.get(2), 7);
        productsMap.put(products.get(3), 5);

        houses.add(new House(1, new LocatedAt(24.75, 84.41666666666667), "Aurangabad", "Bihar", productsMap));

        productsMap = new HashMap<>();
        productsMap.put(products.get(4), 2);
        productsMap.put(products.get(5), 4);
        houses.add(new House(2, new LocatedAt(25.666666666666668, 85.2), "Bankipore", "Bihar", productsMap));

        productsMap = new HashMap<>();
        productsMap.put(products.get(3), 10);
        productsMap.put(products.get(4), 6);
        houses.add(new House(31, new LocatedAt(25.616666666666667, 85.21666666666667), "Patna", "Bihar", productsMap));

        productsMap = new HashMap<>();
        productsMap.put(products.get(0), 4);
        productsMap.put(products.get(2), 5);
        houses.add(new House(41, new LocatedAt(28.7041, 77.1025), "Delhi", "Delhi", productsMap));

        productsMap = new HashMap<>();
        productsMap.put(products.get(4), 8);
        houses.add(new House(51, new LocatedAt(19.0760, 72.8777), "mumbai", "Maharashtra", productsMap));

        productsMap = new HashMap<>();
        productsMap.put(products.get(6), 5);
        houses.add(new House(61, new LocatedAt(22.5726, 88.3639), "Kolkata", "West Bengal", productsMap));

        productsMap = new HashMap<>();
        productsMap.put(products.get(6), 10);
        houses.add(new House(71, new LocatedAt(12.9716, 77.5946), "Bengaluru", "Karnataka", productsMap));


//        houses.add(new House(3, new LocatedAt(23.166666666666668, 84.21666666666667), "Barwa", "Bihar"));
//        houses.add(new House(4, new LocatedAt(26.8, 84.55), "Bettiah", "Bihar"));
//        houses.add(new House(5, new LocatedAt(25.25, 87.01666666666667), "Bhagalpur", "Bihar"));
//        houses.add(new House(6, new LocatedAt(24.683333333333334, 85.03333333333333), "Buddh Gaya", "Bihar"));
//        houses.add(new House(7, new LocatedAt(25.566666666666666, 84.01666666666667), "Buxar", "Bihar"));
//        houses.add(new House(8, new LocatedAt(25.783333333333335, 84.78333333333333), "Chhapra", "Bihar"));
//        houses.add(new House(9, new LocatedAt(25.266666666666666, 87.28333333333333), "Colgong", "Bihar"));
//        houses.add(new House(10, new LocatedAt(26.166666666666668, 85.95), "Darbhanga (Laheriasarai)", "Bihar"));
//        houses.add(new House(11, new LocatedAt(25.233333333333334, 84.28333333333333), "Dhankkgain", "Bihar"));
//        houses.add(new House(12, new LocatedAt(25.633333333333333, 85.08333333333333), "Dinapore", "Bihar"));
//        houses.add(new House(13, new LocatedAt(25.55, 84.2), "Dumraon", "Bihar"));
//        houses.add(new House(14, new LocatedAt(26.5, 84.5), "Gandak R.", "Bihar"));
//        houses.add(new House(15, new LocatedAt(42.81666666666667, 85.01666666666667), "Gaya", "Bihar"));
//        houses.add(new House(16, new LocatedAt(25.466666666666665, 86.63333333333334), "Gogri", "Bihar"));
//        houses.add(new House(17, new LocatedAt(26.466666666666665, 84.43333333333334), "Gopalganj", "Bihar"));
//        houses.add(new House(18, new LocatedAt(25.683333333333334, 85.95), "Hajipur", "Bihar"));
//        houses.add(new House(19, new LocatedAt(25.316666666666666, 85.53333333333333), "Jamalpur", "Bihar"));
//        houses.add(new House(20, new LocatedAt(26.583333333333332, 86.15), "Jyanagar", "Bihar"));
//        houses.add(new House(21, new LocatedAt(26.516666666666666, 85.35), "Kantai", "Bihar"));
//        houses.add(new House(22, new LocatedAt(25.5, 87.66666666666667), "Kathihar", "Bihar"));
//        houses.add(new House(23, new LocatedAt(26.166666666666668, 87.03333333333333), "Kishanganj", "Bihar"));
//        houses.add(new House(24, new LocatedAt(23.666666666666668, 84.55), "Mankheri", "Bihar"));

//        houses.add(new House(25, new LocatedAt(25.4, 85.91666666666667), "Mokameh", "Bihar"));
//        houses.add(new House(26, new LocatedAt(26.666666666666668, 85.23333333333333), "Motihari", "Bihar"));
//        houses.add(new House(27, new LocatedAt(25.383333333333333, 86.5), "Munger", "Bihar"));
//        houses.add(new House(28, new LocatedAt(26.116666666666667, 85.45), "Muzaffarpur", "Bihar"));
//        houses.add(new House(29, new LocatedAt(24.883333333333333, 85.58333333333333), "Nawada", "Bihar"));
//        houses.add(new House(30, new LocatedAt(24.5, 87.33333333333333), "Naya Dumka", "Bihar"));
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

    private static void getIdByCity() {
        for (House house : getHouses()) {
            directory.put(house.getCity().toLowerCase(), house);
            idDirectory.put(house.getId(),house);
        }
    }

    private static void connectCities(String parent, String child) {
        try {
            House h = directory.get(parent);
            House h1 = directory.get(child);
            assert h != null;
            assert h1 != null;
            Node newNode = new Node(h1.getId(), Util.findDistance(h.getLocation(), h1.getLocation()));
            Node newNode1 = new Node(h.getId(), Util.findDistance(h.getLocation(), h1.getLocation()));
            List<Node> n = adj.get(h.getId());
            List<Node> m = adj.get(h1.getId());
            if (n == null) {
                n = new ArrayList<>();
            }
            if (m == null)
                m = new ArrayList<>();
            n.add(newNode);
            m.add(newNode1);
            adj.put(h.getId(), n);
            adj.put(h1.getId(), m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void connecting() {
        getIdByCity();
        connectCities("arrah", "patna");
        connectCities("aurangabad", "patna");
        connectCities("bankipore", "patna");


        connectCities("delhi", "patna");
        connectCities("mumbai", "patna");
        connectCities("kolkata", "patna");
        connectCities("bengaluru", "patna");

        connectCities("mumbai", "delhi");
        connectCities("kolkata", "delhi");
        connectCities("bengaluru", "delhi");

        connectCities("kolkata", "mumbai");
        connectCities("bengaluru", "mumbai");

        connectCities("bengaluru", "kolkata");
    }

    public static House findWareHouse(@NotNull HashMap<Product, Integer> orderedProducts, LocatedAt userLocation) {
        int totalProductsSize = orderedProducts.size();
        House nearestWareHouse = null;
        float earlierDistance = Float.MAX_VALUE;

        for (House house : getHouses()) {
            int cnt = 0;
            HashMap<Product, Integer> products = house.getProducts();

            for (Product key : orderedProducts.keySet()) {
                if (products.get(key) != null && products.get(key) >= orderedProducts.get(key)) {
                    cnt++;
                }
            }

            if (cnt == totalProductsSize) {
                if (nearestWareHouse == null) {
                    nearestWareHouse = house;
                    earlierDistance = Util.findDistance(nearestWareHouse.getLocation(), userLocation);
                } else {
                    float newDistance = Util.findDistance(house.getLocation(), userLocation);
                    if (newDistance < earlierDistance) {
                        nearestWareHouse = house;
                        earlierDistance = newDistance;
                    }
                }
            }
        }

        return nearestWareHouse;
    }

    public static House findNearestWareHouse(LocatedAt userLocation) {
        House nearestHouse = null;
        float earlierDistance = Float.MAX_VALUE;

        for (House house : getHouses()) {
            float newDistance = Util.findDistance(house.getLocation(), userLocation);
            if (newDistance < earlierDistance) {
                nearestHouse = house;
                earlierDistance = newDistance;
            }
        }

        return nearestHouse;
    }

    public static House CancelOrder(Order_info order,int position) {
        Log.d("Order", order.toString());
        Timestamp initialTime = Timestamp.valueOf(order.getOrder_time());
        Timestamp finalTime = new Timestamp(System.currentTimeMillis());

        long difference = finalTime.getTime() - initialTime.getTime();
        difference /= 1000; // In Seconds

        difference /= 3600; // In Hours

        long distanceCovered = speed * difference;
        String path = order.getOrder_path().trim();
        Log.d("Path", path);

        String[] pathArray = path.split("->");

        for(int i=0;i<pathArray.length;){
            int start = Integer.parseInt(pathArray[i]);
            int end = -1;
            if(i+1 < pathArray.length)
                end = Integer.parseInt(pathArray[i+1]);

            House one = idDirectory.get(start);
            assert one != null;

            if(end == -1){
                orders.remove(position);
                Log.d("After Cancel Go To ", "" + one);
                return  one;
            }

            House other = idDirectory.get(end);
            assert other != null;

            float distance = Util.findDistance(one.getLocation(),other.getLocation());
            if(distanceCovered > distance){
                distanceCovered -= distance;
                i++;
            }else{
                orders.remove(position);
                Log.d("After Cancel Go To ", "" + other);
                return other;
            }
        }
        return null;
    }
}
