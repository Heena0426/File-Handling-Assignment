package com.File.Handling.file_handling;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public class JSONToCSVConverter {
    public static void main(String[] args) {
        File jsonFilePath =new File("C:\\Users\\heena\\Downloads\\file-handling\\file-handling\\src\\main\\java\\com\\File\\Handling\\file_handling/people.json");
        String csvFilePath = "People.csv";


        List<Person> people = importFromJSON("C:/Users/heena/Downloads/file-handling/file-handling/src/main/java/com/File/Handling/file_handling/people.json");


        convertToCSVAndSave(csvFilePath, people);

        System.out.println("JSON data successfully converted to CSV and saved to " + csvFilePath);
    }


    public static List<Person> importFromJSON(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Person> people = null;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            people = objectMapper.readValue(br, objectMapper.getTypeFactory().constructCollectionType(List.class, Person.class));

        } catch (FileNotFoundException e) {
            throw new RuntimeException("JSON file not found: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file: " + e.getMessage(), e);
        }

        return people;
    }

    public static void convertToCSVAndSave(String fileName, List<Person> people) {
        try (FileWriter writer = new FileWriter(fileName)) {

            writer.append("ID,Name,Email\n");


            for (Person person : people) {
                writer.append(String.valueOf(person.getId()));
                writer.append(",");
                writer.append(person.getName());
                writer.append(",");
                writer.append(person.getEmail());
                writer.append("\n");
            }

            System.out.println("CSV file created successfully!");

        } catch (IOException e) {
            throw new RuntimeException("Error writing CSV file: " + e.getMessage(), e);
        }
    }
}
