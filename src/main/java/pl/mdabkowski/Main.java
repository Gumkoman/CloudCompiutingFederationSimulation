package pl.mdabkowski;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    int[] test = new int[20];
    test = Poisson.calculate(20,4);
    double[] result = new double[test.length];
    Chart chart = new Chart();
    result = convertToDouble(test);
    chart.createChart(result,"test","testy wykresow","os x","os y");
    }

    public static double[] convertToDouble(int[] test) {
        double[] result = new double[test.length];
        for(int i =0;i<test.length;i++){
            result[i] = (double)test[i];
        }
    return result;
    }

}
