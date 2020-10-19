package AGNES;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        //数据集链表
        ArrayList<Data> dataList = new ArrayList<Data>();

/*        String fileName = "testSet.txt";
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferReader = new BufferedReader(fileReader);
        String line = bufferReader.readLine();
        while (!line.isEmpty()) {
            double[] data = new double[2];
            String[] input = line.split("\t");
            data[0] = Double.parseDouble(input[0]);
            data[1] = Double.parseDouble(input[1]);
            line = bufferReader.readLine();
            dataList.add(new Data(data[0], data[1]));
        }
        fileReader.close();
        bufferReader.close();*/

        dataList.add(new Data(1, 1));
        dataList.add(new Data(1, 2));
        dataList.add(new Data(2, 1));
        dataList.add(new Data(2, 2));
        dataList.add(new Data(3, 4));
        dataList.add(new Data(3, 5));
        dataList.add(new Data(4, 4));
        dataList.add(new Data(4, 5));

        Agnes agnes = new Agnes(dataList);
        ArrayList<Cluster> list = agnes.clustering(3);

        //图形化展示
        JfreeChartScatter scatter = new JfreeChartScatter();
        scatter.Scatter(list);

        System.out.println(list);
    }
}
