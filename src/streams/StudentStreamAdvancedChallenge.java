package streams;

import streams.StudentCourseStream.Course;
import streams.StudentCourseStream.CourseEngagement;
import streams.StudentCourseStream.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentStreamAdvancedChallenge {
    public static void main(String[] args) {
        System.out.println("-".repeat(50));

        Course pymc = new Course("PYMC", "Python MasterClass", 50);
        Course jmc = new Course("JMC", "Java MasterClass", 100);
        Course rcc = new Course("RCC", "React Complete Course", 92);
        Course smc = new Course("SMC", "Spring MasterClass", 84);
        Course jgames = new Course("JGAME", "Creating Games in Java");

        int currentYear = LocalDate.now().getYear();
//        creating a sample student stream
        List<Student> students = Stream.generate(() -> Student.getRandomStudent(pymc, jmc, rcc, smc, jgames))
                .filter(s -> currentYear - s.getYearEnrolled() <= 4)
                .limit(10000)
                .toList();

        System.out.println(students.stream()
                .mapToInt(Student::getYearEnrolled)
                .summaryStatistics());
        System.out.println("-".repeat(50));
        students.subList(0, 10).forEach(System.out::println);
        System.out.println("-".repeat(50));
        System.out.println(students.stream()
                .mapToInt(s -> s.getEngagementMap().size())
                .summaryStatistics());
        System.out.println("-".repeat(50));

//        var courseStudentCount = students.stream()
//                .collect(Collectors.groupingBy(Student::getEngagementMap));

        var mappedCount = students.stream()
                .flatMap(student -> student.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourse, Collectors.counting()));
        mappedCount.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println("-".repeat(50));

        var lectureCounts = students.stream()
                .collect(Collectors.groupingBy(s -> s.getEngagementMap().size(), Collectors.counting()));
        lectureCounts.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println("-".repeat(50));

        var mappedPercentages = students.stream()
                .flatMap(student -> student.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourse,
                        Collectors.averagingDouble(CourseEngagement::getPercentComplete)));
        mappedPercentages.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println("-".repeat(50));

        var mappedSummary = students.stream()
                .flatMap(student -> student.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourse,
                        Collectors.summarizingDouble(CourseEngagement::getPercentComplete)));
        mappedSummary.forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println("-".repeat(50));
//        Create a nested Map
//        var mappedData = students.stream()
//                .flatMap(student -> student.getEngagementMap().values().stream())
//                .collect(Collectors.groupingBy(CourseEngagement::getCourse));
//        mappedData.values().stream().limit(3).forEach((v) -> System.out.println(v));

        var nestedYearMap = students.stream()
                .flatMap(student -> student.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourse,
                        Collectors.groupingBy(CourseEngagement::getLastActivityYear,
                                Collectors.counting())));

        nestedYearMap.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println("-".repeat(50));

        var result = students.stream()
                .flatMap(s -> s.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getEnrollmentDateYear,
                        Collectors.groupingBy(CourseEngagement::getCourse, Collectors.counting())));

        result.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println("-".repeat(50));
    }
}
