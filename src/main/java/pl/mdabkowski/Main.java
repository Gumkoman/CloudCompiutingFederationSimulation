package pl.mdabkowski;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

import java.io.File;
import java.io.IOException;

import static pl.mdabkowski.Poisson.calculate;



public class Main {
    public static int TIME_SIZE = 20;
    public static void main(String[] args) throws IOException {

        Cloud c1 = new Cloud(10,TIME_SIZE,0.2);
        showArray(c1.getPoisson());
        Cloud c2 = new Cloud(8,TIME_SIZE,40);
        showArray(c2.getPoisson());
        c2.serviceCheck();
        c2.showResult();
    }

    public static void showArray(double[] test) {

        for(int i =0;i<test.length;i++){
            System.out.println("dla i:" + i +"wynik to: "+test[i]);
        }

    }

}
