package ioFileCRUD.pathNav;

import ioFileCRUD.pathNav.studentWrite.student.Course;
import ioFileCRUD.pathNav.studentWrite.student.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Stream;

public class lecture310 {
    public static void main(String[] args) {
        String lineSeparator = "-".repeat(50);
        String header = """
                Student Id,Country Code, Enrolled Year,Age,Gender,\
                Experienced,Course Code,Engagement Month,Engagement Year,\
                Engagement Type""";

        Course jmc = new Course("JMC", "Java MasterClass");
        Course pymc = new Course("PYC", "Python MasterClass");
        Course rmc = new Course("RMC", "React MasterClass");

        List<Student> students = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc, rmc))
                .limit(30)
                .toList();
        System.out.println(lineSeparator);
//using buffered writer to write the records
        try (BufferedWriter bw = new BufferedWriter(new PrintWriter("take1.csv"))) {
            bw.write(header);
            bw.newLine();
            int count = 0;
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    bw.write(record);
                    bw.newLine();
                    count++;
                    if (count % 5 == 0) {
                        Thread.sleep(2000);
                        System.out.print(".");
                    }

                    if (count % 10 == 0) {
                        bw.flush();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing the file with error message: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//using file writer to write the records
        try (FileWriter fw = new FileWriter("take3.csv")) {
            fw.write(header);
            fw.write(System.lineSeparator());
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    fw.write(record);
                    fw.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing the file with error message: " + e.getMessage());
        }

        //using print writer to write the records
        try (PrintWriter pw = new PrintWriter("formattedtake4.txt")) {
            pw.println(header);
            for (Student s : students) {
                for (var record : s.getEngagementRecords()) {
//                    pw.println(record);
                    String[] recordData = record.split(",");
                    pw.printf("%-12d%-5s%2d%4d%3d%-1s",
                            s.getStudentId(),
                            s.getCountry(),
                            s.getEnrollmentYear(),
                            s.getEnrollmentMonth(),
                            s.getEnrollmentAge(),
                            s.getGender());
                    pw.printf("%-1s", s.hasExperience() ? "Y" : "N");
                    pw.format("%-3s%10.2f%-10s%-4s%-30s",
                            recordData[7],
                            s.getPercentComplete(recordData[7]),
                            recordData[8],
                            recordData[9],
                            recordData[10]);
                    pw.println();
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing the file with error message: " + e.getMessage());
        }

    }
}
