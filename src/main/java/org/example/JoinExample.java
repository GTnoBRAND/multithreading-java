package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * join(); means do not continue with any other thread until this one is not finished executing*/

public class JoinExample {

    private static final String FILE_PATH = "Reference.txt";

    static void main() throws InterruptedException {

        Thread writeTo = new Thread(()-> {
            System.out.println("Writing to file...");
            writeContent("Hello World!\nWelcome to Java programming.\nLine 3 data.");
        });
        Thread readFrom = new Thread(()-> {
            System.out.println("Reading from file...");
            readFile();
        });

        writeTo.start();
        readFrom.start();

        //this way threads execute concurrently
        writeTo.join();
        readFrom.join();

        System.out.println("All actions finished");

    }

    /*
     *Create or overwrite content into the file*/
    public static void writeContent(String content){
        Path path = Paths.get(FILE_PATH);
        try {
            Files.writeString(path, content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * Read and print file content*/
    public static void readFile(){
        Path path = Paths.get(FILE_PATH);
        if(!Files.exists(path)){
            System.out.println("file is not found");
        }
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(String line : lines){
            System.out.println(line);
        }
    }
}
