package com.beleavemebe;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import java.util.List;

public class ChartsController {

    private List<Data> data;

    @FXML
    private LineChart<String, Float> temperatureChart;

    @FXML
    private LineChart<String, Double> humidityChart;

    @FXML
    private LineChart<String, Float> windSpeedChart;

    public void setData(List<Data> data) {
        this.data = data;

        XYChart.Series<String, Float> t_series = new XYChart.Series<>();
        XYChart.Series<String, Double> h_series = new XYChart.Series<>();
        XYChart.Series<String, Float> ws_series = new XYChart.Series<>();

        int step = 24 / 8;
        for (int i = 0; i < data.size(); i += step) {
            Data d = data.get(i);
            String date = d.timestamp.toString();
            t_series.getData().add(new XYChart.Data<>(date, d.temperature));
            h_series.getData().add(new XYChart.Data<>(date, d.relativeHumidity));
            ws_series.getData().add(new XYChart.Data<>(date, d.windSpeed));
        }
        temperatureChart.getData().add(t_series);
        humidityChart.getData().add(h_series);
        windSpeedChart.getData().add(ws_series);
    }
}
