package AGNES;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.util.ArrayList;

public class JfreeChartScatter {

    /** The data. */
    private double[][] data = new double[2][100];

    /*
     * 默认构造函数
     */
    public JfreeChartScatter() { }

    /*
     * 调用JfreeChart绘制散点图
     */
    public void Scatter(ArrayList<Cluster> cluster)
    {
        DefaultXYDataset xyDataset = new DefaultXYDataset();
        for(int i = 0; i < cluster.size(); i++)
        {
            for(int j = 0; j < cluster. get(i).getArrayList().size(); j++)
            {
                this.data[0][j] = cluster.get(i).getArrayList().get(j).x;
                this.data[1][j] = cluster.get(i).getArrayList().get(j).y;
            }

            /** 数组 添加到链表或集合  引用类型  所以加入了3个数组都是最后一个数组的值  中文乱码**/
            xyDataset.addSeries("Cluster" + (i + 1), this.data);

            ///// 让指针指向另一个内纯区域   数组 添加到链表或集合  引用类型  所以加入了3个数组都是最后一个数组的值
            this.data = new double[2][100];;
        }

        JFreeChart jfree = ChartFactory.createScatterPlot("Agnes", "X", "Y", xyDataset);
        XYPlot xyPlot = (XYPlot)jfree.getPlot();
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesOutlineStroke(0, new BasicStroke(0.05f));
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesOutlineStroke(1, new BasicStroke(0.05f));
        renderer.setSeriesPaint(1, Color.RED);
        renderer.setSeriesOutlineStroke(2, new BasicStroke(0.05f));
        renderer.setSeriesPaint(2, Color.ORANGE);
        renderer.setSeriesVisible(0, true);
        renderer.setSeriesVisible(1, true);
        renderer.setSeriesVisible(2, true);
        jfree.setBackgroundPaint(Color.WHITE);
        ChartFrame frame = new ChartFrame("Agnes",jfree);
        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }
}
