package com.company.carshowroomssimulatorspring.model.utility;


import com.company.carshowroomssimulatorspring.model.ColumnsNames;
import com.company.carshowroomssimulatorspring.model.Vehicle;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class CSVFileWriter {


    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static String fileHeader;

    public CSVFileWriter(){

    }
    public static void writeCsvFile(List<Vehicle> carList, String fileName) {
        fileHeader = getHeader();
        FileWriter fileWriter = null;
        System.out.println(fileName);
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(fileHeader.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);

            for (Vehicle vehicle : carList) {
                fileWriter.append(vehicle.getBrand());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(vehicle.getModel());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(vehicle.getCondition()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(vehicle.getPrice()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(vehicle.getYearOfProduction()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(vehicle.getMileage()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(vehicle.getEngineCapacity()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(vehicle.getAmount()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(vehicle.isReserved()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public static String getHeader() {
        String prefix = "";
        StringBuilder builder = new StringBuilder();
        for (Field f : Vehicle.class.getDeclaredFields()) {
            ColumnsNames column = f.getAnnotation(ColumnsNames.class);
            if (column != null) {
                builder.append(prefix);
                prefix = ",";
                builder.append(column.value());
            }
        }
    return builder.toString();
    }
}