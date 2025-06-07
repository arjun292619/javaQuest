package streams;

import streams.StudentCourseStream.Course;
import streams.StudentCourseStream.Student;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamsMap {
    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python MasterClass", 50);
        Course jmc = new Course("JMC", "Java MasterClass", 100);
        Course rcc = new Course("RCC", "React Complete Course", 90);
        Course smc = new Course("SMC", "Spring MasterClass", 75);
        Course jgames = new Course("JGAME", "Creating Games in Java");
        System.out.println("-".repeat(50));

        List<Student> students = Stream.iterate(1, integer -> integer <= 5000, integer -> integer + 1)
                .map(n -> Student.getRandomStudent(pymc, jmc, rcc, smc, jgames))
                .toList();
        var mappedStudents = students.stream()
                .collect(groupingBy(Student::getCountryCode));

        mappedStudents.forEach((k, v) -> System.out.println(k + " " + v.size()));
//        System.out.println("Australian Students: " + mappedStudents.get("AU").size());

        System.out.println("-".repeat(50));
//       Hash map of students 25 and under
        int minAge = 25;
        var under25Map = students.stream()
                .collect(groupingBy(Student::getCountryCode,
                        filtering(s -> {
                                    s = (Student) s;
                                    return s.getAge() <= minAge;
                                },
                                toList())));
        under25Map.forEach((k, v) -> System.out.println(k + " " + v.size()));
        System.out.println("-".repeat(50));
//        Get a map data by partitioning on a predicate or condition
        var experiencedMap = students.stream()
                .collect(partitioningBy(Student::isProgrammingExperience));
        System.out.println("Experienced Students = " + experiencedMap.get(true).size());
        System.out.println("-".repeat(50));
        var experiencedCount = students.stream()
                .collect(partitioningBy(Student::isProgrammingExperience, counting()));
        System.out.println("Experienced Students = " + experiencedCount.get(true));
        System.out.println("-".repeat(50));

//        custom predicate and partitioning
        var expAndActive = students.stream()
                .collect(partitioningBy((student ->
                        student.isProgrammingExperience() &&
                                student.getMonthsSinceActive() == 0), counting()));

        System.out.println("Experienced Active Students = " + expAndActive.get(true));
        System.out.println("-".repeat(50));
//        Get or Return a multi level Map
        var multiLevelMapper = students.stream()
                .collect(groupingBy(Student::getCountryCode, groupingBy(Student::getGender)));

        multiLevelMapper.forEach((k, v) -> {
            System.out.println(k);
            v.forEach((k1, v1) -> {
                System.out.println("\t" + k1 + " " + v1.size());
            });
        });

        System.out.println("-".repeat(50));
//        Flat mapping Operations

//        long studentCount = students.stream().count();
        long studentCount = students.size();

        long studentBodyCount = 0;
        for (var list : experiencedMap.values()) {
            studentBodyCount += list.size();
        }
        System.out.println("studentBodyCount: " + studentBodyCount);
        int stdntCount = experiencedMap.values().stream()
                .mapToInt(list -> list.size())
                .sum();
        System.out.println("studentBodyCount: " + stdntCount);
        System.out.println("-".repeat(50));
        var stdntList = experiencedMap.values().stream()
                .map(l -> l.stream())
                .toList();
//                        .filter(student -> student.getMonthsSinceActive() <= 3)
//                        .count())
//                .mapToLong(l -> l)
//                .sum();

        System.out.println("studentBodyCount: " + stdntList);
        System.out.println("-".repeat(50));
        var result = experiencedMap.values().stream()
                .flatMap(list -> list.stream())
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .toList();

        System.out.println("result: " + result.size());
        System.out.println("-".repeat(50));

        var flattenedMultiLevel = multiLevelMapper.values().stream()
                .flatMap(map -> map.values().stream()
                        .flatMap(l -> l.stream()))
//                .toList();
                .limit(3)
                .toList();
//        reformatted code
        flattenedMultiLevel = multiLevelMapper.values().stream()
                .flatMap(map -> map.values().stream())
                .flatMap(l -> l.stream())
                .limit(3)
                .toList();

        flattenedMultiLevel.forEach(System.out::println);
    }
}
