package CheckSort.excel;

import CheckSort.analyzer.GenerateAnalyz;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Alexey on 23.10.2017
 */
public class LineChart {
    private Integer counter = 0;

    public ArrayList<Long> startAnalyzis() throws IOException {
        GenerateAnalyz generateAnalyz = new GenerateAnalyz();
        generateAnalyz.generateData();
        ArrayList<Class> classesWithSort = generateAnalyz.getClassesWithSort();
        ArrayList<Method> annotatedMethods = generateAnalyz.getAnnotatedMethods();
        Class classWithAnnotMethods = generateAnalyz.getClassWithAnnotMethods();
        ArrayList<Long> listNanoTime = generateAnalyz.getListNanoTime();
        final int NUM_OF_ROWS = 7;
        final int NUM_OF_COLUMNS = generateAnalyz.getSIZE();

        LineChart lineChart = new LineChart();
        try (Workbook wb = new XSSFWorkbook()){
            for(Method mth : annotatedMethods){
                Sheet sheet = wb.createSheet(mth.getName().toString());
                try{
                    sheet = drowChart(fillSheet(sheet, classesWithSort, NUM_OF_ROWS,
                            NUM_OF_COLUMNS, listNanoTime), NUM_OF_COLUMNS);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            lineChart.writeWB(wb);
        }
        return listNanoTime;
    }
    /**
     *
     * @param sheet
     * @param classesWithSort
     * @param NUM_OF_ROWS
     * @param NUM_OF_COLUMNS
     * @param timeOfSort
     * @return
     */
    public Sheet fillSheet(Sheet sheet, ArrayList<Class> classesWithSort, int NUM_OF_ROWS, int NUM_OF_COLUMNS, ArrayList<Long> timeOfSort){
        Row row;
        Cell cell;
        for (int rowIndex = 0; rowIndex < NUM_OF_ROWS; rowIndex++) {
            row = sheet.createRow((short) rowIndex);
            for (int colIndex = 0; colIndex <= NUM_OF_COLUMNS; colIndex++) {
                if(colIndex == 0){
                    if(rowIndex == 0){
                        for (int colIndexx = 1; colIndexx <= NUM_OF_COLUMNS; colIndexx++) {
                            cell = row.createCell((short) colIndexx);
                            cell.setCellValue(colIndexx + 9);
                        }
                        cell = row.createCell((short) colIndex);
                        cell.setCellValue("Sort\\Size");
                        break;
                    }
                    cell = row.createCell((short) colIndex);
                    cell.setCellValue(classesWithSort.get(rowIndex-1).getName());
                    colIndex++;
                }
                cell = row.createCell((short) colIndex);
                cell.setCellValue(timeOfSort.get(counter));
                counter++;
            }
        }
        return sheet;
    }

    /**
     *
     * @see org.apache.poi.ss.usermodel.Chart
     * @param sheet - sheet for chart
     * @param NUM_OF_COLUMNS
     * @return
     */
    public Sheet drowChart(Sheet sheet, int NUM_OF_COLUMNS){
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 8, 20, 25);

        Chart chart = drawing.createChart(anchor);
        ChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);

        LineChartData data = chart.getChartDataFactory().createLineChartData();

        ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 0, NUM_OF_COLUMNS - 1));
        ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 0, NUM_OF_COLUMNS - 1));
        ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, NUM_OF_COLUMNS - 1));
        ChartDataSource<Number> ys3 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(3, 3, 0, NUM_OF_COLUMNS - 1));
        ChartDataSource<Number> ys4 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(4, 4, 0, NUM_OF_COLUMNS - 1));
        ChartDataSource<Number> ys5 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(5, 5, 0, NUM_OF_COLUMNS - 1));
        ChartDataSource<Number> ys6 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(6, 6, 0, NUM_OF_COLUMNS - 1));

        LineChartSeries chartSeries1 = data.addSeries(xs, ys1);
        chartSeries1.setTitle("BoubleSortDown");
        LineChartSeries chartSeries2 = data.addSeries(xs, ys2);
        chartSeries2.setTitle("BoubleSortUp");
        LineChartSeries chartSeries3 = data.addSeries(xs, ys3);
        chartSeries3.setTitle("HalfSort");
        LineChartSeries chartSeries4 = data.addSeries(xs, ys4);
        chartSeries4.setTitle("MargeSort");
        LineChartSeries chartSeries5 = data.addSeries(xs, ys5);
        chartSeries5.setTitle("SimpleSort");
        LineChartSeries chartSeries6 = data.addSeries(xs, ys6);
        chartSeries6.setTitle("SwapSort");

        chart.plot(data, bottomAxis, leftAxis);
        return sheet;
    }

    /**
     *
     * @param wb - current wb for file
     * @throws IOException
     */
    public void writeWB(Workbook wb) throws IOException{
        try (FileOutputStream fileOut = new FileOutputStream("D:/Work/Netcracker/lab01/lab01_Kanivets/SortAnalyz.xlsx")) {
            wb.write(fileOut);
        }
    }
}
