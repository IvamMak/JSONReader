package ru.makarovie.inputOutput;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class ConsoleHelper {

    public static String getTypeOfOperation() {
        String operationType = "";
        System.out.println("Введите тип операции");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            operationType = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return operationType;
    }

    public static String getInputFile() {
        String inputFile = "";
        System.out.println("Введите адрес файла с входными данными");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            try {
                inputFile = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Files.exists(Paths.get(inputFile)))
                break;
            else {
                System.out.println("Файл не существует");
                System.out.println("Введите адрес файла с входными данными");
            }
        }
        return inputFile;
    }
}
