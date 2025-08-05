package ioFileCRUD.pathNav;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

public class lecture301 {
    public static void main(String[] args) {
        String ls = "-".repeat(50);
        System.out.println(ls);
        Path path = Path.of("files/test.txt");
        Path nestedPath = Path.of("this/is/several/folders/test.txt");
        Path cwd = Path.of("");
        logStatement(nestedPath);
        Path pwd = Path.of("/");
        printPathInfo(path);
        logStatement(path);
        printFileAttributes(path);
        printPathInfo(cwd);
//        printPathInfo(pwd);
    }

    private static void printPathInfo(Path path) {
        System.out.println("Path: " + path);
        System.out.println("filename: " + path.getFileName());
        System.out.println("parent: " + path.getParent());
        Path absolutePath = path.toAbsolutePath();
        System.out.println("Absolute Path: " + absolutePath);
        System.out.println("Absolute Path root: " + absolutePath.getRoot());
        System.out.println("Root: " + path.getRoot());
        System.out.println("isAbsolute: " + path.isAbsolute());
        System.out.println("-".repeat(50));
        System.out.println(absolutePath.getRoot());
//        navigating path using iterator
//        int i = 1;
//        var iter = path.toAbsolutePath().iterator();
//        while (iter.hasNext()) {
//            System.out.println(".".repeat(i++) + " " + iter.next());
//        }
//        navigating path using loop
        for (int i = 0; i < absolutePath.getNameCount(); i++) {
            System.out.println(".".repeat(i + 1) + " " + absolutePath.getName(i));
        }
        System.out.println("-".repeat(50));
    }

    private static void logStatement(Path path) {
        try {
            Path parent = path.getParent();
            if (!Files.exists(parent)) {
//                Files.createDirectory(parent);
                Files.createDirectories(parent);
            }
            Files.writeString(path, Instant.now() + ": Humpty Dumpty went up the wall \n",
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printFileAttributes(Path path) {
        try {
            var attr = Files.readAttributes(path, "*");
            attr.entrySet().forEach(System.out::println);
            System.out.println(Files.probeContentType(path));
        } catch (IOException e) {
            System.out.println("Problem printg attributes");
        }
    }
}
