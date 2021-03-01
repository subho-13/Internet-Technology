package server.file;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class StringMapString {
    public static Map<String, String> read(String filename) {
        Map<String, String> keyValue = new TreeMap<>();
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            String[] strings;

            while (reader.hasNextLine()) {
                strings = reader.nextLine().split(" ");
                keyValue.put(strings[0], strings[1]);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
            e.printStackTrace();
        }
        return keyValue;
    }

    public static void write(String filename, Map<String, String> keyValue) {
        if (keyValue == null || keyValue.isEmpty()) {
            return;
        }

        try {
            FileWriter fr = new FileWriter(filename, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter writer = new PrintWriter(br);

            for (Map.Entry<String, String> entry : keyValue.entrySet()) {
                writer.println(entry.getKey() + " " + entry.getValue());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Cannot create or append to file");
            e.printStackTrace();
        }
    }
}
