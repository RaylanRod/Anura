package com.anura.view;

import org.fusesource.jansi.Ansi;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Helper {

    public static void printSplashPage(String path) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }
    }

    public static void printHelp (String fileName, int startLine, int endLine ) {

        try (BufferedReader reader =  new BufferedReader(new FileReader(fileName))){
            String line;
            int lineNumber = 0;

            while((line = reader.readLine()) != null) {
                if (lineNumber >= startLine && lineNumber <=endLine) {
                    System.out.println(line);
                }
                lineNumber++;
                if(lineNumber > endLine){
                    break;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    // read a txt file, and print it in bright foreground color
    public static void printFile(String fileName, Ansi.Color foreColor){

        String fileContent = readFromResourceFile(fileName);

        printColor(fileContent, foreColor);
    }

    // read from a file, and print it in default color
    public static void printFile(String fileName){

        String fileContent = readFromResourceFile(fileName);

        printColor(fileContent, Ansi.Color.BLACK);
    }

    // print String in color at cursor location (x, y)
    public static void printColor(String string, Ansi.Color color, int x, int y) {
        System.out.print(Ansi.ansi().cursor(y, x).fg(color).a(string + "\n").reset());
    }

    // print String in bright color
    public static void printColor(String string, Ansi.Color color) {
        System.out.print(Ansi.ansi().fgBright(color).a(string).reset());
    }

    public static String readFromResourceFile(String fileName) {

        String fileContent = "";

        // First, try to access the resource from the classpath (e.g., JAR file)
        InputStream inputStream = Helper.class.getResourceAsStream("/" + fileName);

        if (inputStream != null) {
            // Resource is found (in JAR), process the input stream as needed
            try {
                // Read content from the resource
                byte[] resourceBytes = inputStream.readAllBytes();
                fileContent = new String(resourceBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        else {
//            // Resource not found in JAR, try accessing it from the regular file system
//            File file = new File(fileName);
//
//            if (file.exists() && file.isFile()) {
//                // File is found (in file system), process the file as needed
//                try {
//                    // Read content from the file
//                    fileContent = Files.readString(file.toPath());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                System.out.println("Resource/File not found: " + fileName);
//            }
//        }

        return fileContent + "\n";
    }

    public static void writeToExternalFile(String jsonObj, String fileName) {
        String jarFilePath = Helper.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        jarFilePath = new File(jarFilePath).getParent();

        File externalFile = new File(jarFilePath, fileName);

        try (FileWriter writer = new FileWriter(externalFile)) {
            writer.write(jsonObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getExternalFile(String fileName) throws IOException {
        String jarFilePath = Helper.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        jarFilePath = new File(jarFilePath).getParent();

        String filePath = jarFilePath + "/" + fileName;

        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);

        // Convert bytes to a String
        return new String(bytes);
    }
}