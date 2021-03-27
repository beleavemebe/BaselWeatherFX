package com.beleavemebe;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Parser {

    private List<Data> output;

    public Parser(File source) {
        output = parse(source);
    }

    private static List<Data> parse(File file) {
        ArrayList<Data> data = new ArrayList<>();
        try {
            Scanner parser = new Scanner(new FileReader(file));
            while (parser.hasNextLine()) {
                try {
                    String[] args = parser.nextLine().split(",");
                    int year = Integer.parseInt(args[0].substring(0, 4)) - 1900;
                    int month = Integer.parseInt(args[0].substring(4, 6)) - 1;
                    int day = Integer.parseInt(args[0].substring(6, 8));
                    int hour = Integer.parseInt(args[0].substring(9, 11));
                    int minute = Integer.parseInt(args[0].substring(11));
                    Date date =
                            new Date(year,
                                    month,
                                    day,
                                    hour,
                                    minute);
                    Float temperature = Float.parseFloat(args[1]);
                    Double relativeHumididty = Double.parseDouble(args[2]);
                    Float windSpeed = Float.parseFloat(args[3]);
                    Float windDirection = Float.parseFloat(args[4]);
                    data.add(new Data(date, temperature, relativeHumididty, windSpeed, windDirection));
                } catch (Exception e) {
                    if (parser.hasNextLine()) parser.nextLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<Data> getOutput() {
        return output;
    }

}
