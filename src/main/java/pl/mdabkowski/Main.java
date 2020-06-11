package pl.mdabkowski;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static pl.mdabkowski.Poisson.calculate;



public class Main {

    public static void main(String[] args) throws IOException {
        Cloud c1 = new Cloud(0,10,Constants.TIME_SIZE,0.8);
        Cloud c2 = new Cloud(1,10,Constants.TIME_SIZE,0.4);
        Federation f1 = new Federation();

        f1.addNewCloud(c1);
        f1.addNewCloud(c2);
        f1.setup("najwiekszaMozliwaWartosc");
        //System.out.println(f1.getCloudFederationList().get(0).getResourcesNumber());
        f1.simulate();
        c1.showResult();
        c2.showResult();
        c1.simulate();
        c2.simulate();
        c1.showResult();
        c2.showResult();
        showArray(c1.getPoisson());
        showArray(c2.getPoisson());
        /*Cloud c1 = new Cloud(10,TIME_SIZE,0.2);
        showArray(c1.getPoisson());
        Cloud c2 = new Cloud(8,TIME_SIZE,40);
        showArray(c2.getPoisson());
        c2.serviceCheck();
        c2.showResult();*/
    }

    public static void showArray(int[] test) {
        System.out.println("tablica");
        for(int i =0;i<test.length;i++){
            System.out.print(test[i]+"|");
        }

    }

}
