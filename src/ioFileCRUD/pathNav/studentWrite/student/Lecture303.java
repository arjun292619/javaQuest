package ioFileCRUD.pathNav.studentWrite.student;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Lecture303 {
    public static void main(String[] args) {
        String ls = "-".repeat(50);
        System.out.println(ls);
        Path startingpath = Path.of(".");
//        System.out.println(startingpath + " : " + startingpath.toAbsolutePath());
        FileVisitor<Path> statsVisitor = new StatsVisitor();
        try {
            Files.walkFileTree(startingpath, statsVisitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class StatsVisitor extends SimpleFileVisitor<Path> {
        private Path intialPath = null;
        private final Map<Path, Long> folderSizes = new LinkedHashMap<>();
        private int initialCount;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);
            folderSizes.merge(file.getParent(), 0L, (o, n) -> o + attrs.size());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(dir);
            Objects.requireNonNull(attrs);
//            System.out.println("pre visit dir path: " + dir + " count: " + dir.getNameCount());
            if (intialPath == null) {
                intialPath = dir;
                initialCount = dir.getNameCount();
            } else {
                int relativeLevel = dir.getNameCount() - initialCount;
                if (relativeLevel == 1) {
                    folderSizes.clear();
                }
                folderSizes.put(dir, 0L);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            Objects.requireNonNull(dir);
//            if (exc != null) {
//                throw exc;
//            }
//            System.out.println("post visit dir path: " + dir + " count: " + dir.getNameCount());
            if (dir.equals(intialPath)) {
                return FileVisitResult.TERMINATE;
            }
            int relativeLevel = dir.getNameCount() - initialCount;
            if (relativeLevel == 1) {
                folderSizes.forEach((k, v) -> {
                    int level = k.getNameCount() - initialCount - 1;
                    System.out.printf("%s[%s] - %,d bytes %n",
                            "\t".repeat(level), k.getFileName(), v);
                });
            } else {
                long size = folderSizes.get(dir);
                folderSizes.merge(dir.getParent(), 0L, (o, n) -> o + size);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
