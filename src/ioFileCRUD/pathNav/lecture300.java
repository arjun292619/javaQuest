package ioFileCRUD.pathNav;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class lecture300 {
    public static void main(String[] args) {
        useFile("testfile.txt");
        usePath("pathFile.txt");
        System.out.println("-".repeat(40));
    }

    private static void useFile(String fileLocation) {
        File file = new File(fileLocation);
        boolean fileExists = file.exists();

        System.out.printf("File '%s' %s%n",
                fileLocation,
                (fileExists ? "exists" : "does not exists")
        );

        if (fileExists) {
            System.out.println("Deleting File: " + fileLocation);
            fileExists = !file.delete();
        }

        if (!fileExists) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An exception was thrown when creating the file.");
            }
            System.out.println("Created new file: " + fileLocation);
            if (file.canWrite()) {
                System.out.println("Can do WRITES to file here!");
            }
        }
    }

    private static void usePath(String filePath) {
        Path path = Path.of(filePath);
        boolean fileExists = Files.exists(path);
        System.out.printf("File '%s' %s%n",
                filePath,
                (fileExists ? "exists" : "does not exists")
        );
        if (fileExists) {
            System.out.println("Deleting File: " + filePath);
            try {
                Files.delete(path);
                fileExists = false;
            } catch (IOException e) {
                System.out.println("An exception was thrown when deleting the file.");
            }
        }

        if (!fileExists) {
            try {
                Files.createFile(path);
                if (Files.isWritable(path)) {
//                    System.out.println("Can do WRITES to file here!");
                    Files.writeString(path, """
                            Hickory Dickory Dock,
                            The mouse went up the clock,
                            The mouse fell down,
                            The clock struck 1,
                            Hickory Dickory Dock""");
                }
                System.out.println("Files read is done after writing");
                System.out.println("-".repeat(40));
                Files.readAllLines(path).forEach(System.out::println);
                System.out.println("-".repeat(40));
            } catch (IOException e) {
                System.out.println("An exception was thrown when creating the file.");
            }
        }
    }
}
