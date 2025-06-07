package streams;

import streams.StudentCourseStream.Course;
import streams.StudentCourseStream.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalDemo {
    public static void main(String[] args) {
        System.out.println("-".repeat(50));

        Course pymc = new Course("PYMC", "Python MasterClass", 50);
        Course jmc = new Course("JMC", "Java MasterClass", 100);
        Course rcc = new Course("RCC", "React Complete Course", 90);
        Course smc = new Course("SMC", "Spring MasterClass", 75);
        Course jgames = new Course("JGAME", "Creating Games in Java");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(pymc, jmc, rcc, smc, jgames))
                .limit(1000)
                .collect(Collectors.toList());

        Optional<Student> o1 = getStudent(null, "first");
        System.out.println("Empty = " + o1.isEmpty() + ", Present = " + o1.isPresent());
        System.out.println(o1);
        System.out.println("-".repeat(50));

        o1 = getStudent(new ArrayList<>(), "first");
        System.out.println("Empty = " + o1.isEmpty() + ", Present = " + o1.isPresent());
        System.out.println(o1);
//        System.out.println(o1.get());
        o1.ifPresentOrElse(System.out::println, () -> System.out.println("Empty Optional Value"));
        System.out.println("-".repeat(50));

//        students.addFirst(null);
        Optional<Student> o2 = getStudent(students, "first");
        System.out.println("Empty = " + o2.isEmpty() + ", Present = " + o2.isPresent());
        System.out.println(o2);
//        if (o2.isPresent()) System.out.println(o2.get());
        o2.ifPresent(System.out::println);
        System.out.println("-".repeat(50));

//        Student firstStudent = (o2.isPresent()) ? o2.get() : null;
//        Student firstStudent = o2.orElse(null);
//        Student firstStudent = o2.orElse(getDummyStudent());
        Student firstStudent = o2.orElseGet(() -> getDummyStudent(jmc));
        long id = firstStudent.getStudentId();
        System.out.println("first Student id " + id);

        System.out.println("-".repeat(50));
        List<String> countries = students.stream()
                .map(student -> student.getCountryCode())
                .distinct()
                .toList();

        Optional.of(countries)
                .map(s -> String.join(",", s))
                .filter(l -> l.contains("FR"))
                .ifPresentOrElse(System.out::println, () -> System.out.println("Missing FR"));
    }

    private static Optional<Student> getStudent(List<Student> list, String type) {
        if (list == null || list.size() == 0) {
            return Optional.empty();
        } else if (type.equals("first")) {
            return Optional.ofNullable(list.getFirst());
        } else if (type.equals("last")) {
            return Optional.ofNullable(list.getLast());
        }
        return Optional.of(list.get(new Random().nextInt(list.size())));
    }

    private static Student getDummyStudent(Course... courses) {
        System.out.println("Getting a dummy student");
        return new Student("NO", 1, 1, "U", false, courses);
    }
}
