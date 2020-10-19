package AGNES;

import java.util.ArrayList;

public class Agnes {
    ArrayList<Cluster> clusterList;

    public Agnes(ArrayList<Data> dataList) {
        clusterList = createClusterList(dataList);

    }

    //进行AGNES聚类
    public ArrayList<Cluster> clustering(int num) {
        while (num > 0 && clusterList.size() != num) {
            ArrayList<Cluster> list = nearestCluster(clusterList);
            Cluster tmp1 = list.get(0);
            Cluster tmp2 = list.get(1);
            tmp1.addAll(tmp2);
            clusterList.remove(tmp2);
        }
        return clusterList;
    }

    //将数据包装成簇
    public ArrayList<Cluster> createClusterList(ArrayList<Data> list) {
        ArrayList<Cluster> clusterList = new ArrayList<Cluster>();
        for (Data item : list) {
            clusterList.add(new Cluster(item));
        }
        return clusterList;
    }

    //计算最近簇
    public ArrayList<Cluster> nearestCluster(ArrayList<Cluster> clusterList) {
        if (clusterList.size() < 2)
            return null;
        Cluster tmp1 = null;
        Cluster tmp2 = null;
        double whole = Double.MAX_VALUE;
        for (int index = 0; index < clusterList.size() - 1; index++) {
            for (int pos = index + 1; pos < clusterList.size(); pos++) {
                double value = Cluster.averageDistance(clusterList.get(index), clusterList.get(index));
                if (value < whole) {
                    whole = value;
                    tmp1 = clusterList.get(index);
                    tmp2 = clusterList.get(pos);
                }
            }
        }
        ArrayList<Cluster> list = new ArrayList<Cluster>();
        list.add(tmp1);
        list.add(tmp2);
        return list;
    }
}
