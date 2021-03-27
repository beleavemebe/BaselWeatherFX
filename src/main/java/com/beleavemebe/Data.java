package com.beleavemebe;

import java.util.Date;

public class Data {

    public Date timestamp;

    public Float temperature;

    public Double relativeHumidity;

    public Float windSpeed;

    public Float windDirection;

    @Override
    public String toString() {
        return  timestamp.toString() +
                ", temperature=" + temperature +
                ", relativeHumidity=" + relativeHumidity +
                ", windSpeed=" + windSpeed +
                ", windDirection=" + windDirection;
    }

    public Data(Date timestamp, Float temperature, Double relativeHumidity, Float windSpeed, Float windDirection) {
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.relativeHumidity = relativeHumidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

}
