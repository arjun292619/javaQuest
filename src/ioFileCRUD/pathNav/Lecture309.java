package ioFileCRUD.pathNav;

import ioFileCRUD.pathNav.studentWrite.student.Course;
import ioFileCRUD.pathNav.studentWrite.student.Student;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Lecture309 {
    public static void main(String[] args) {
        String lineSeparator = "-".repeat(50);
        String header = """
                Student Id,Country Code, Enrolled Year,Age,Gender,\
                Experienced,Course Code,Engagement Month,Engagement Year,\
                Engagement Type""";

        Course jmc = new Course("JMC", "Java MasterClass");
        Course pymc = new Course("PYC", "Python MasterClass");

        List<Student> students = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(10)
                .toList();
        System.out.println(lineSeparator);
//        System.out.println(header);
//        students.forEach(s -> s.getEngagementRecords().forEach(System.out::println));

        Path path = Path.of("students.csv");
//        try {
//            Files.writeString(path, header);
//            for (Student s : students) {
//                Files.write(path, s.getEngagementRecords(), StandardOpenOption.APPEND);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            List<String> data = new ArrayList<>();
            data.add(header);
            for (Student s : students) {
                data.addAll(s.getEngagementRecords());
            }
            Files.write(path, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = Files.newBufferedWriter(Path.of("take2.csv"))) {
            bw.write(header);
            bw.newLine();
            for (Student s : students) {
                for (var record : s.getEngagementRecords()) {
                    bw.write(record);
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
