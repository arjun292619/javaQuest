package streams;

import streams.StudentCourseStream.Course;
import streams.StudentCourseStream.Student;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StudentChallenge {
    public static void main(String[] args) {
        System.out.println("-".repeat(50));

        Course pymc = new Course("PYMC", "Python MasterClass", 50);
        Course jmc = new Course("JMC", "Java MasterClass", 100);
        Course rcc = new Course("RCC", "React Complete Course", 90);
        Course smc = new Course("SMC", "Spring MasterClass", 75);
        Course jgames = new Course("JGAME", "Creating Games in Java");

//        var students = Stream.iterate(1, integer -> integer <= 5000, i -> i + 1)
//                .map(s -> Student.getRandomStudent(jmc, pymc))
//                .toList();

        var students = IntStream.rangeClosed(1, 5000)
                .mapToObj(i -> Student.getRandomStudent(jmc, pymc))
                .toList();

//        Average total percent completed
        double totalPercent = students.stream()
                .mapToDouble(s -> s.getpercentComplete("JMC"))
//                .sum();
                .reduce(0, Double::sum);
        double avePercent = totalPercent / students.size();

        System.out.printf("Average Pecentage Completed = %.2f%% %n", avePercent);

        int topPercent = (int) (1.25 * avePercent);
        System.out.printf("Best Percentage complete = %d%% %n", topPercent);

        Comparator<Student> longStudentComparator = Comparator.comparing(Student::getYearEnrolled);

        List<Student> hardWorkers = students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(student -> student.getpercentComplete("JMC") >= topPercent)
                .sorted(longStudentComparator)
                .limit(10)
                .toList();

//        System.out.println("hardworkers: " + hardWorkers.size());
        hardWorkers.forEach(s -> {
            s.addCourse(jgames);
            System.out.println(s);
        });

        System.out.println("-".repeat(50));

        students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(student -> student.getpercentComplete("JMC") >= topPercent)
                .sorted(longStudentComparator)
                .limit(10)
                .forEach(s -> {
                    s.addCourse(jgames);
                    System.out.print(s.getStudentId() + " ");
                });
        System.out.println();
        System.out.println("-".repeat(50));
        students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(student -> student.getpercentComplete("JMC") >= topPercent)
                .sorted(longStudentComparator)
                .limit(10)
                .collect(Collectors.toSet())
                .forEach(s -> {
                    s.addCourse(jgames);
                    System.out.print(s.getStudentId() + " ");
                });
        System.out.println();
        System.out.println("-".repeat(50));
        Comparator<Student> uniqueStudents = Comparator.comparing(Student::getStudentId);
        students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(student -> student.getpercentComplete("JMC") >= topPercent)
                .sorted(longStudentComparator)
                .limit(10)
                .collect(() -> new TreeSet<>(uniqueStudents), TreeSet::add, TreeSet::addAll)
                .forEach(s -> {
                    s.addCourse(jgames);
                    System.out.print(s.getStudentId() + " ");
                });
        System.out.println();
        System.out.println("-".repeat(50));
    }
}
