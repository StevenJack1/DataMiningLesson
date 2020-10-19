package KMeans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


//是否可以优化？定义一个point数据结构(或者说bean对象)
public class KMeans {
    private int ClusterNum; //聚成几类
    private ArrayList<ArrayList<double[]>> cluster; //每一行代表一类的数据，每一个double[]数组代表 是否可以优化？存储index
    private double[][] center; //每一行代表一个聚类中心
    private double[][] lastCenter = new double[ClusterNum][2];
    private ArrayList<double[]> dataSet = new ArrayList<double[]>();
    private String fileName;

    //构造函数
    public KMeans(int clusterNum, String fileName, double[][] center) {
        this.ClusterNum = clusterNum;
        this.fileName = fileName;
        this.center = center;
    }

    //迭代过程
    public void ExecuteMethod() throws IOException {
        LoadDataSet();
        do {
            initCluster();
            AllocateCluster();
            lastCenter = center;
            setNewCenter();
        }
        while(this.IsCenterChanged(center));
        System.out.println(Arrays.deepToString(this.center));
    }

    public ArrayList<ArrayList<double[]>> getCluster() {
        return cluster;
    }

    // 载入数据集
    private void LoadDataSet() throws IOException {
        String fileName = this.fileName;
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferReader = new BufferedReader(fileReader);
        String line = bufferReader.readLine();
        while(!line.isEmpty()) {
            double[] data  = new double[2];
            String[] input = line.split("\t");
            data[0] = Double.parseDouble(input[0]);
            data[1] = Double.parseDouble(input[1]);
            line = bufferReader.readLine();
            dataSet.add(data);
        }
        fileReader.close();
        bufferReader.close();
    }

    //判读上次迭代是否更改了聚类结果
    private boolean IsCenterChanged(double[][] center) {
        for(int i = 0; i < center.length; i++) {
            if(center[i][0] != lastCenter[i][0] || center[i][1] != lastCenter[i][1]) {
                return true;
            }
        }
        return false;
    }

    //将Cluster清空
    private void initCluster() {
        if(this.cluster != null) {
            this.cluster.clear();
        }
        ArrayList<ArrayList<double[]>> tempCluster = new ArrayList<ArrayList<double[]>>();
        for(int i = 0; i < ClusterNum; i++) {
            tempCluster.add(new ArrayList<double[]>());
        }
        this.cluster = tempCluster;
    }
    //计算欧氏距离
    private double CalcDistance(double[] element, double[] center) {
        double x = element[0] - center[0];
        double y = element[1] - center[1];
        double z = x*x + y*y;
        return (double)Math.sqrt(z);
    }

    private int getClusterIndex(double[] distance) {
        double minDistance = distance[0];
        int clusterIndex = 0;
        for(int i = 0; i < distance.length; i++) {
            if(distance[i] < minDistance) {
                minDistance = distance[i];
                clusterIndex = i;
            }
        }
        return clusterIndex;
    }

    //给点分类
    private void AllocateCluster() {
        double[] distance = new double[ClusterNum];
        for(double[] data : dataSet) {
            for(int j = 0; j < ClusterNum; j++) {
                distance[j] = this.CalcDistance(data, center[j]);
            }
            int clusterIndex = this.getClusterIndex(distance);
            cluster.get(clusterIndex).add(data);
        }
    }

    //计算新聚类中心
    private void setNewCenter() {
        center = new double[ClusterNum][2];
        for(int i = 0; i < center.length; i++) {
            if(cluster.get(i).size() != 0) {
                double[] newCenter = new double[2];
                for(int j = 0; j < cluster.get(i).size(); j++) {
                    newCenter[0] += cluster.get(i).get(j)[0];
                    newCenter[1] += cluster.get(i).get(j)[1];
                }
                center[i][0] = newCenter[0]/cluster.get(i).size();
                center[i][1] = newCenter[1]/cluster.get(i).size();
            }
        }
    }
}