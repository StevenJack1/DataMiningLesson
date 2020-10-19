package AGNES;

import java.util.ArrayList;

public class Cluster {
    private ArrayList<Data> items = new ArrayList<Data>();

    public Cluster(Data[] data) {
        for (Data item : data) {
            items.add(item);
        }
    }

    public Cluster(Data data) {
        items.add(data);
    }

    public ArrayList<Data> getArrayList() {
        return items;
    }

    //计算两个簇的平均距离
    public static double averageDistance(Cluster c1, Cluster c2) {
        double number = allDistance(c1, c2);
        return 1.0 * number / (c1.items.size() * c2.items.size());
    }

    //计算两个簇的全部距离总和
    public static double allDistance(Cluster c1, Cluster c2) {
        double number = 0;
        for (Data t : c1.items) {
            for (Data u : c2.items) {
                number += Math.sqrt((t.x - u.x) * (t.x - u.x) + (t.y - u.y) * (t.y - u.y));
            }
        }
        return number;
    }

    public void addAll(Cluster cluster) {
        this.items.addAll(cluster.items);
    }

    public void add(Data data) {
        this.items.add(data);
    }

    @Override
    public String toString() {
        return "Cluster{" +
                "items=" + items.toString() +
                '}';
    }
}
