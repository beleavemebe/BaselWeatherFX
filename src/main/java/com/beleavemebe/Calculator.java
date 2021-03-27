package com.beleavemebe;

import java.util.*;

public class Calculator {

    List<Data> data;

    public Calculator(List<Data> data) {
        this.data = data;
    }

    public String formResult() {
        StringBuilder sb = new StringBuilder();

        sb.append("Результаты анализа погоды \nс ")
                .append(getEarliestDate())
                .append(" по ")
                .append(getLatestDate())
                .append("\n\n");
        sb.append("Средние значения:\n");
        sb.append("     Средняя температура воздуха    ").append(String.format("%.2f", getAverageTemperature())).append(" °C\n");
        sb.append("     Средняя влажность воздуха      ").append(String.format("%.2f", getAverageHumidity())).append(" %\n");
        sb.append("     Средняя скороcть ветра         ").append(String.format("%.2f",getAverageWindSpeed())).append(" км/ч\n\n");
        sb.append("Час наибольшей температуры          ").append(getDateWithHighestTemperature()).append("\n");
        sb.append("Самая низкая влажность              ").append(getLowestHumidity()).append(" %\n");
        sb.append("Самый сильный ветер                 ").append(String.format("%.2f",getGreatestWindSpeed())).append(" км/ч\n");
        sb.append("Самое частое направление ветра      ").append(getMostCommonWindDirection()).append("\n");
        return sb.toString();
    }

    private Double getAverageTemperature() {
        return data.stream()
                .mapToDouble(data -> data.temperature)
                .average()
                .getAsDouble();
    }

    private Double getAverageHumidity() {
        return data.stream()
                .mapToDouble(data -> data.relativeHumidity)
                .average()
                .getAsDouble();
    }

    private Double getAverageWindSpeed() {
        return data.stream()
                .mapToDouble(data -> data.windSpeed)
                .average()
                .getAsDouble();
    }

    private Date getDateWithHighestTemperature() {
        return data.stream()
                .max((Data d1, Data d2) -> Float.compare(d1.temperature, d2.temperature))
                .get()
                .timestamp;
    }

    private Double getLowestHumidity() {
        return data.stream()
                .min((Data d1, Data d2) -> Double.compare(d1.relativeHumidity, d2.relativeHumidity))
                .get()
                .relativeHumidity;
    }

    private Float getGreatestWindSpeed() {
        return data.stream()
                .max((Data d1, Data d2) -> Float.compare(d1.windSpeed, d2.windSpeed))
                .get()
                .windSpeed;
    }

    private String getMostCommonWindDirection() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Восток", 0);
        map.put("Северо-Восток", 0);
        map.put("Север", 0);
        map.put("Северо-Запад", 0);
        map.put("Запад", 0);
        map.put("Юго-Запад", 0);
        map.put("Юг", 0);
        map.put("Юго-Восток", 0);
        for (Data d : data) {
            float angle = d.windDirection;
            String direction = "";
            if (angle > 337 || angle < 22) {
                direction = "Восток";
            } else if (angle > 292 && angle < 337) {
                direction = "Юго-Восток";
            } else if (angle > 247 && angle < 292) {
                direction = "Юг";
            } else if (angle > 202 && angle < 247) {
                direction = "Юго-Запад";
            } else if (angle > 157 && angle < 202) {
                direction = "Запад";
            } else if (angle > 112 && angle < 157) {
                direction = "Северо-Запад";
            } else if (angle > 67 && angle < 112) {
                direction = "Север";
            } else if (angle > 22 && angle < 67) {
                direction = "Северо-Восток";
            }
            map.put(direction, map.get(direction) + 1);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        return list.get(0).getKey();
    }

    private Date getEarliestDate() {
        return data.stream()
                .min((Data d1, Data d2) -> d1.timestamp.compareTo(d2.timestamp))
                .get()
                .timestamp;
    }

    private Date getLatestDate() {
        return data.stream()
                .max((Data d1, Data d2) -> d1.timestamp.compareTo(d2.timestamp))
                .get()
                .timestamp;
    }

}
