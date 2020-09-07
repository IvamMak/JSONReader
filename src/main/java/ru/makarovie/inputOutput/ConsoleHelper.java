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
        System.out.println("Enter type of operation");
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
        System.out.println("Enter file address with input data or exit for closing program");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            try {
                inputFile = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Files.exists(Paths.get(inputFile))) {
                break;
            }
            if (inputFile.equals("exit")) System.exit(0);
            else {
                System.out.println("File not exist");
                System.out.println("Enter file address with input data or exit for closing program");
            }
        }
        return inputFile;
    }
}
