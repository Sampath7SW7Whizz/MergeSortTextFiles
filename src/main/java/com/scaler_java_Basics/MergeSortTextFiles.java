package com.scaler_java_Basics;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class MergeSortTextFiles {

    public static void main(String[] args) {
        String inputFile1 = "input_file1.txt";
        String inputFile2 = "input_file2.txt";
        String outputFile = "output_file.txt";

        List<Integer> numbersList1 = readNumbersFromFile(inputFile1);
        List<Integer> numbersList2 = readNumbersFromFile(inputFile2);

        Collections.sort(numbersList1);
        Collections.sort(numbersList2);

        List<Integer> mergedSortedNumbers = mergeSortedLists(numbersList1, numbersList2);

        writeNumbersToFile(outputFile, mergedSortedNumbers);

        System.out.println("Merged and sorted numbers have been written to " + outputFile);
    }

    private static List<Integer> readNumbersFromFile(String fileName) {
        // Step 1: Create a new ArrayList to store the numbers read from the file
        List<Integer> numbersList = new ArrayList<>();

        try (
                // Step 2: Create an InputStream to read data from the file
                //         using the ClassLoader to load the file as a resource
                InputStream inputStream = MergeSortTextFiles.class.getClassLoader().getResourceAsStream(fileName);

                // Step 3: Create a Scanner to parse the data from the InputStream
                Scanner scanner = new Scanner(inputStream)
        ) {
            // Step 4: Read data from the file using the Scanner until there are no more integers
            while (scanner.hasNextInt()) {
                // Step 5: Read the next integer from the Scanner and add it to the numbersList
                numbersList.add(scanner.nextInt());
            }
        } catch (IOException e) {
            // Step 6: If an IOException occurs (e.g., file not found or error reading the file),
            //         print the exception stack trace to the console
            e.printStackTrace();
        }

        // Step 7: Return the list containing the numbers read from the file
        return numbersList;
    }



    private static List<Integer> mergeSortedLists(List<Integer> list1, List<Integer> list2) {
        List<Integer> mergedList = new ArrayList<>();
        int i = 0, j = 0;

        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) < list2.get(j)) {
                mergedList.add(list1.get(i));
                i++;
            } else {
                mergedList.add(list2.get(j));
                j++;
            }
        }

        while (i < list1.size()) {
            mergedList.add(list1.get(i));
            i++;
        }

        while (j < list2.size()) {
            mergedList.add(list2.get(j));
            j++;
        }

        return mergedList;
    }

    private static void writeNumbersToFile(String fileName, List<Integer> numbers) {
        try (
                // Step 1: Create a PrintWriter to write data to the file
                //         using the specified fileName
                PrintWriter writer = new PrintWriter(new FileWriter(fileName))
        ) {
            // Step 2: Write each number from the list to the file
            for (Integer num : numbers) {
                // Step 3: Write the number to the file followed by a newline character
                writer.println(num);
            }
        } catch (IOException e) {
            // Step 4: If an IOException occurs (e.g., error writing to the file),
            //         print the exception stack trace to the console
            e.printStackTrace();
        }
    }

}
