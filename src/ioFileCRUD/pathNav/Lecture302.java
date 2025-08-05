package ioFileCRUD.pathNav;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Stream;

public class Lecture302 {
    public static void main(String[] args) {
        String lineSeparator = "-".repeat(50);
        System.out.println(lineSeparator);
        Path currPath = Path.of("");
        System.out.println("cwd: " + currPath.toAbsolutePath());

        System.out.println("Listing using List");
        try (Stream<Path> paths = Files.list(currPath)) {
            paths.
                    map(Lecture302::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(lineSeparator);
        System.out.println("Listing and filtering using walk");
        try (Stream<Path> pathStream = Files.walk(currPath, 2)) {
            pathStream
                    .filter(Files::isRegularFile)
                    .map(Lecture302::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(lineSeparator);
        System.out.println("Listing and filtering using find");
//        try (Stream<Path> pathStream = Files.find(currPath, 2,
//                ((path, fAttributes) -> Files.isRegularFile(path)))) {
//            pathStream
        try (Stream<Path> pathStream = Files.find(currPath, Integer.MAX_VALUE,
                ((path, fAttributes) ->
                        fAttributes.isRegularFile() && fAttributes.size() > 2000))) {
            pathStream
                    .map(Lecture302::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Path ideaFolderPath = currPath.resolve(".idea");
        System.out.println(lineSeparator);
        System.out.println("Using Directory Stream from Files");
        try (var dirs = Files.newDirectoryStream(ideaFolderPath, "*.xml")) {
            dirs.forEach(dir -> System.out.println(Lecture302.listDir(dir)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(lineSeparator);
        System.out.println("Using Directory Stream from Files to filter by extension: Alternative to glob");
        try (var dirs = Files.newDirectoryStream(ideaFolderPath,
                p -> p.getFileName().toString().endsWith(".xml") && Files.isRegularFile(p) && Files.size(p) > 1000)) {
            dirs.forEach(dir -> System.out.println(Lecture302.listDir(dir)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String listDir(Path path) {
        try {
            boolean isDir = Files.isDirectory(path);
            FileTime dateField = Files.getLastModifiedTime(path);
            LocalDateTime localModDt = LocalDateTime.ofInstant(dateField.toInstant(), ZoneId.systemDefault());
            return "%tD %tT %-5s %12s %s".formatted(localModDt, localModDt,
                    (isDir ? "<DIR>" : ""), (isDir ? "" : Files.size(path)), path);
        } catch (IOException e) {
            System.out.println("There is error with the path provided: " + path + " = " + e.getMessage());
            return path.toString();
        }
    }
}
