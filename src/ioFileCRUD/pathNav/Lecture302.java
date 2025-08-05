package ioFileCRUD.pathNav;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.stream.Stream;

public class Lecture302 {
    public static void main(String[] args) {
        String lineSeparator = "-".repeat(50);
        System.out.println(lineSeparator);
        Path currPath = Path.of("");
        System.out.println("cwd: " + currPath.toAbsolutePath());
        try (Stream<Path> paths = Files.list(currPath)) {
            paths.
                    map(Lecture302::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String listDir(Path path) {
        try {
            boolean isDir = Files.isDirectory(path);
            FileTime dateField = Files.getLastModifiedTime(path);
            return "%s %-15s %s".formatted(dateField, (isDir ? "<DIR>" : ""), path);
        } catch (IOException e) {
            System.out.println("There is error with the path provided: " + path + " = " + e.getMessage());
            return path.toString();
        }
    }
}
