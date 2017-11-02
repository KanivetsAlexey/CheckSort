package CheckSort;

import CheckSort.excel.LineChart;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args ){
        LineChart lineChart = new LineChart();
        try {
            lineChart.startAnalyzis();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
