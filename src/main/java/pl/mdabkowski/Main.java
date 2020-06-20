package pl.mdabkowski;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static pl.mdabkowski.Poisson.calculate;



public class Main {

    public static void main(String[] args) throws IOException {
        /*Cloud c1 = new Cloud(0,10,Constants.TIME_SIZE,1);
        Cloud c2 = new Cloud(1,14,Constants.TIME_SIZE,5);
        Federation f1 = new Federation();

        f1.addNewCloud(c1);
        f1.addNewCloud(c2);
        f1.setup("najwiekszaMozliwaWartosc");
        f1.simulate();
        c1.showResult();
        c2.showResult();
        c1.simulate();
        c2.simulate();
        c1.showResult();
        c2.showResult();
        showArray(c1.getPoisson());
        showArray(c2.getPoisson());
        System.out.println("");

        List<Cloud> cloudList = new ArrayList<Cloud>();
        Federation f2 = new Federation();
        int max = 5;
        int min = 1;
        int range = max - min + 1;
        for(int i=0;i<10;i++){
            cloudList.add(new Cloud(i,6+(int)(Math.random() * range) + min,Constants.TIME_SIZE,i+1));
        }
        for(int i=0;i<cloudList.size();i++){
            f2.addNewCloud(cloudList.get(i));
        }
        f2.setup("FC");
        f2.simulate();
        System.out.println("Federation: ");
        for(int i=0;i<cloudList.size();i++){
            cloudList.get(i).showResult();
        }
        System.out.println("Without federation: ");
        for(int i=0;i<cloudList.size();i++){
            cloudList.get(i).simulate();
            cloudList.get(i).showResult();
        }
        System.out.println("Packets in each cloud: ");
        for(int i=0;i<cloudList.size();i++){
            showArray(cloudList.get(i).getPoisson());
        }*/
        UI ui = new UI();
        ui.start();

    }

    public static void showArray(int[] test) {
        System.out.println("tablica");
        for(int i =0;i<test.length;i++){
            System.out.print(test[i]+"|");
        }

    }

}
