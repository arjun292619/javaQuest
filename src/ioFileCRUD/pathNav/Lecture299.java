package ioFileCRUD.pathNav;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lecture299 {
    public static void main(String[] args) {
        System.out.println("-".repeat(30));
        System.out.println("Current working directory(cwd): " + new File("").getAbsolutePath());
        String fileName = "files/testing.csv";
//        File file = new File(".", fileName);
        File file = new File(new File("/").getAbsolutePath(), fileName);
        System.out.println("Files on C drive: " + file.getAbsolutePath());
        if (!file.exists()) {
            System.out.println("I cant run until the file exists");
            return;
        }
        System.out.println("The file is ready to be processed");

        for (File f : File.listRoots()) {
            System.out.println(f);
        }

        Path path = Paths.get(fileName);
        System.out.println();
        if (!Files.exists(path)) {
            System.out.println("2. File through Paths does not exist");
            return;
        }
        System.out.println("2. The file is ready to be processed");
    }
}
