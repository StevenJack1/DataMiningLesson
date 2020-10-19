package AGNES;

//数据点类(二维数据)
public class Data {
    double x, y;
    public Data(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Data{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}