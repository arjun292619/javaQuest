package streams.StudentCourseStream;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StatTerminalStreams {
    public static void main(String[] args) {
        System.out.println("-".repeat(50));

        Course pymc = new Course("PYMC", "Python MasterClass");
        Course jmc = new Course("JMC", "Java MasterClass");
        Course rcc = new Course("RCC", "React Complete Course");
        Course smc = new Course("SMC", "Spring MasterClass");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(pymc, jmc, rcc, smc))
                .limit(1000)
                .toList();

        int minAge = 18;

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .findAny()
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                                s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));
        System.out.println("-".repeat(50));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .findFirst()
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                                s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));
        System.out.println("-".repeat(50));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .min(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                                s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));
        System.out.println("-".repeat(50));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .max(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                                s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));
        System.out.println("-".repeat(50));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .mapToInt(Student::getAge)
                .average()
                .ifPresentOrElse(a -> System.out.printf("Avg age under 21: %.2f%n", a),
                        () -> System.out.println("Didn't find anyone under " + minAge));
        System.out.println("-".repeat(50));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .reduce((a, b) -> {
//                    System.out.println("a: " + a);
//                    System.out.println("b: " + b);
                    return String.join(",", a, b);
                })
                .ifPresentOrElse(System.out::println, () -> System.out.println("None found"));
        System.out.println("-".repeat(50));

        students.stream()
                .map(Student::getCountryCode)
                .distinct()
//                .filter(c -> c.contains("FR"))
                .filter(c -> c.contains("AU"))
                .findAny()
                .ifPresentOrElse(System.out::println,
                        () -> System.out.println("Missing FR"));
        System.out.println("-".repeat(50));
    }
}
