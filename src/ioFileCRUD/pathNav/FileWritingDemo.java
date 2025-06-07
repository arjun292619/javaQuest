package ioFileCRUD.pathNav;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWritingDemo {
    public static void main(String[] args) {
        String header = """
                """;
        try (PrintWriter pwriter = new PrintWriter("students4.csv")) {
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Path path = Path.of("worry.html");
        try  {
            var data = Files.readAllLines(path);
            System.out.println(data.size());
            data.forEach(line->{
                System.out.println(line);
                System.out.println();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
