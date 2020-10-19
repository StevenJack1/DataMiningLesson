package KMeans;

import java.io.IOException;

public class Main {
    //主函数，程序入口。不用关注throws IOException
    public static void main(String[] args) throws IOException {
        double[][] center = {
                {5,5},
                {1,1},
                {2,2}
        };
        KMeans kmeans = new KMeans(3, "testSet.txt", center);
        JfreeChartScatter scatter = new JfreeChartScatter();
        kmeans.ExecuteMethod();
        scatter.Scatter(kmeans.getCluster());
    }
}
