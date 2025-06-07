package streams;

import streams.StudentCourseStream.Course;
import streams.StudentCourseStream.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollections {
    public static void main(String[] args) {
        System.out.println("-".repeat(50));

        Course pymc = new Course("PYMC", "Python MasterClass");
        Course jmc = new Course("JMC", "Java MasterClass");
        Course rcc = new Course("RCC", "React Complete Course");
        Course smc = new Course("SMC", "Spring MasterClass");

        List<Student> studentList = Stream.generate(() -> Student.getRandomStudent(pymc, jmc, rcc, smc))
                .limit(1000)
                .toList();

        Set<Student> australianStudents = studentList.stream()
                .filter(s -> s.getCountryCode().equals("AU"))
                .collect(Collectors.toSet());

        System.out.println("# of Australian Students =  " + australianStudents.size());
        System.out.println("-".repeat(50));

        Set<Student> underThirty = studentList.stream()
                .filter(s -> s.getAgeEnrolled() < 30)
                .collect(Collectors.toSet());

        System.out.println("# of under thirty Students =  " + underThirty.size());
        System.out.println("-".repeat(50));

        Set<Student> youngAussies1 = new TreeSet<>(Comparator.comparing(Student::getStudentId));
        youngAussies1.addAll(australianStudents);
        youngAussies1.retainAll(underThirty);

        youngAussies1.forEach(s -> System.out.print(s.getStudentId() + " "));
        System.out.println();
        System.out.println("-".repeat(50));

        Set<Student> youngAussies2 = studentList.stream()
                .filter(student -> student.getAgeEnrolled() < 30)
                .filter(student -> student.getCountryCode().equals("AU"))
                .collect(() -> new TreeSet<>(Comparator.comparing(Student::getStudentId)), TreeSet::add, TreeSet::addAll);
//                .collect(Collectors.toSet());
        youngAussies2.forEach(s -> System.out.print(s.getStudentId() + " "));
        System.out.println();
        System.out.println("-".repeat(50));

        String countryList = studentList.stream()
                .map(s -> s.getCountryCode())
                .distinct()
                .sorted()
                .reduce("", (r, v) -> {
                    return r + " " + v;
                });
        System.out.println("Countrylist: " + countryList);
    }
}
