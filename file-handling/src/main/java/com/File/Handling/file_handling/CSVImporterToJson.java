package com.File.Handling.file_handling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVImporterToJson {
    public static void main(String[] args) {
        File csvFilePath = new File("/Users/heena/Downloads/file-handling/file-handling/People.csv");
        String jsonFilePath = "people.json";


        List<Person> people = importFromCSV("C:\\Users\\heena\\Downloads\\file-handling\\file-handling\\src\\main\\java\\com\\File\\Handling\\file_handling\\People.csv");


        convertToJSONAndSave(jsonFilePath, people);

        System.out.println("CSV data successfully converted to JSON and saved to " + jsonFilePath);
    }


    public static List<Person> importFromCSV(String fileName) {
        List<Person> people = new ArrayList<>();
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String email = data[2];
                people.add(new Person(id, name, email));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("CSV file not found: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + e.getMessage(), e);
        }

        return people;
    }


    public static void convertToJSONAndSave(String fileName, List<Person> people) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            String jsonContent = objectMapper.writeValueAsString(people);
            fileWriter.write(jsonContent);
        } catch (IOException e) {
            throw new RuntimeException("Error writing JSON to file: " + e.getMessage(), e);
        }
    }
}
