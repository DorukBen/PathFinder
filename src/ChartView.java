import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ShapeUtilities;
import util.factory.MapFactory;

import javax.swing.*;

public class ChartView extends JFrame {

    private static JFreeChart sJChart;
    private static XYPlot sPlot;
    private static JPanel sJPanel;

    public ChartView(String title, double[][] blockData, double[][][] pathData, double[] start, double[] target) {
        super(title);
        newChart(blockData, pathData, start, target);
    }

    private XYDataset createNewDataSet(double[][] blockData, double[][][] pathData, double[] start, double[] target) {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries blockSeries = new XYSeries("Block");
        for (double[] block : blockData) {
            if (block[0] != 0 || block[1] != 0) {
                blockSeries.add(block[0], block[1]);
            }
        }
        xySeriesCollection.addSeries(blockSeries);

        XYSeries startSeries = new XYSeries("Start");
        startSeries.add(start[0], start[1]);

        xySeriesCollection.addSeries(startSeries);

        XYSeries targetSeries = new XYSeries("Target");
        targetSeries.add(target[0], target[1]);

        xySeriesCollection.addSeries(targetSeries);

        for (double[][] paths : pathData) {
            XYSeries pathSeries = new XYSeries("Path");
            for (double[] path : paths) {
                pathSeries.add(path[0], path[1]);
            }
            xySeriesCollection.addSeries(pathSeries);
        }

        return xySeriesCollection;
    }

    private void adjustRendererOptions(XYItemRenderer renderer) {
        Shape block = ShapeUtilities.createDiagonalCross(3, 1);
        renderer.setSeriesShape(0, block);
        renderer.setSeriesPaint(0, Color.black);

        Shape cross = ShapeUtilities.createDownTriangle(5);

        renderer.setSeriesShape(1, cross);
        renderer.setSeriesPaint(1, Color.green);

        renderer.setSeriesShape(2, cross);
        renderer.setSeriesPaint(2, Color.yellow);


        Shape line = ShapeUtilities.createDiamond(2);

        float colorValue = 200;
        for (int i = 3; i < renderer.getPlot().getSeriesCount(); i++) {
            renderer.setSeriesShape(i, line);
            renderer.setSeriesPaint(i, Color.getHSBColor(colorValue, 100, 0.5f));
            colorValue += 15;
        }
    }

    public void newData(double[][] blockData, double[][][] pathData, double[] start, double[] target) {
        sPlot.setDataset(createNewDataSet(blockData, pathData, start, target));
        adjustRendererOptions(sPlot.getRenderer());
    }

    public void newChart(double[][] blockData, double[][][] pathData, double[] start, double[] target) {
        sJChart = ChartFactory.createScatterPlot(
                "Genetic Path Algorithm", null, null, createNewDataSet(blockData, pathData, start, target),
                PlotOrientation.VERTICAL, true, true, false);
        sJChart.setPadding(new RectangleInsets(20, 0, 0, 0));
        sPlot = (XYPlot) sJChart.getPlot();
        sPlot.setDomainCrosshairVisible(false);
        sPlot.setRangeCrosshairVisible(false);
        adjustRendererOptions(sPlot.getRenderer());


        sPlot.getDomainAxis().setRange(new Range(-1, MapFactory.getDefaultRowLength()));
        sPlot.getRangeAxis().setRange(new Range(-1, MapFactory.getDefaultColumnLength()));


        sJPanel = new ChartPanel(sJChart, true);
        sJPanel.setPreferredSize(new Dimension(640, 480));

        setContentPane(sJPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


}
