package pl.mdabkowski;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

import java.io.File;
import java.io.IOException;
public class Chart  {
    String fileName;
    JFreeChart chartResult;

    /*void createChart(double[] values,String fileName,String title,String xAxisName,String yAxisName) throws IOException{
        var dataset = new HistogramDataset();
        dataset.addSeries("",values,20);

        JFreeChart chartResult = ChartFactory.createHistogram(title,xAxisName,yAxisName,dataset);
        ChartUtils.saveChartAsPNG(new File(fileName+".png"), histogram, 450, 400);
    }*/

    /*void createChart(double[] values,String fileName,String title,String xAxisName,String yAxisName){
    int size = values.length;
        chartResult =
    }*/
}
