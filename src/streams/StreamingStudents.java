package streams;

import streams.StudentCourseStream.Course;
import streams.StudentCourseStream.Student;

import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamingStudents {
    public static void main(String[] args) {
        System.out.println("-".repeat(36));

        Course pymc = new Course("PYMC", "Python MasterClass");
        Course jmc = new Course("JMC", "Java MasterClass");
        Course rcc = new Course("RCC", "React Complete Course");
        Course smc = new Course("SMC", "Spring MasterClass");

//        Student tim = new Student("AU", 30, 2019, "M", true, jmc, pymc);
//        System.out.println(tim);
//
//        tim.watchLecture("JMC", 10, 5, 2019);
//        tim.watchLecture("PYMC", 7, 7, 2020);
//        System.out.println(tim);

//        Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
//                .limit(10)
//                .forEach(System.out::println);

        Student[] students = new Student[1000];
        Arrays.setAll(students, i -> Student.getRandomStudent(pymc, jmc));

//        var maleStudents = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
//                .limit(1000);
//        student distribution count of genders
        System.out.println("-".repeat(36));
        var maleStudents = Arrays.stream(students)
                .filter(s -> s.getGender().equals("M"));
        System.out.println("# of male students: " + maleStudents.count());
        System.out.println("-".repeat(36));
        for (String gender : List.of("M", "F", "U")) {
            var studentCount = Arrays.stream(students)
                    .filter(s -> s.getGender().equals(gender))
                    .count();
            System.out.println("# of male students: " + studentCount);
        }
//        student distribution count of age groups
        System.out.println("-".repeat(50));
        List<Predicate<Student>> predicateList = List.of(s -> s.getAge() < 30, s -> s.getAge() >= 30 && s.getAge() < 60);
        int total = 0;
        for (int i = 0; i < predicateList.size(); i++) {
            var ageStream = Arrays.stream(students)
                    .filter(predicateList.get(i));
            long count = ageStream.count();
            total += count;
            System.out.printf("# of students (%s) = %d%n", i == 0 ? " < 30" : " >= 30 && < 60", count);
        }

        System.out.println("# of students >= 60 = " + (students.length - total));
//        Using summary statistics to understand the data
        System.out.println("-".repeat(50));

        var ageStream = Arrays.stream(students)
                .mapToInt(Student::getAgeEnrolled);

        System.out.println("Stats for enrollment age: " + ageStream.summaryStatistics());
        var currentAgeStream = Arrays.stream(students)
                .mapToInt(Student::getAge);

        System.out.println("Stats for enrollment age: " + currentAgeStream.summaryStatistics());
        System.out.println("-".repeat(50));
//        get a list of countries student are from

        Arrays.stream(students)
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .forEach(country -> System.out.print(country + " "));
        System.out.println();
        System.out.println("-".repeat(50));

//        count of students active and enrolled for more than 7 years
        boolean longTerm = Arrays.stream(students)
                .anyMatch(student -> (student.getAge() - student.getAgeEnrolled() >= 7) && student.getMonthsSinceActive() < 12);
        System.out.println("longterm students? " + longTerm);

        long longTermCount = Arrays.stream(students)
                .filter(student -> (student.getAge() - student.getAgeEnrolled() >= 7) && student.getMonthsSinceActive() < 12)
                .count();
        System.out.println("longterm students# " + longTermCount);
        System.out.println("-".repeat(50));
//        Converting a stream to unmodifiable list
        List<Student> longTimeLearners = Arrays.stream(students)
                .filter(student -> (student.getAge() - student.getAgeEnrolled() >= 7) && student.getMonthsSinceActive() < 12)
                .filter(student -> !student.isProgrammingExperience())
                .limit(5)
                .toList();

//        //        Converting a stream to unmodifiable list
//        Student[] longTimeLearnersArr = (Student[]) Arrays.stream(students)
//                .filter(student -> (student.getAge() - student.getAgeEnrolled() >= 7) && student.getMonthsSinceActive() < 12)
//                .filter(student -> !student.isProgrammingExperience())
//                .limit(5)
//                .toArray();

        //        Converting a stream to unmodifiable list
        Student[] longTimeLearnersArr = Arrays.stream(students)
                .filter(student -> (student.getAge() - student.getAgeEnrolled() >= 7) && student.getMonthsSinceActive() < 12)
                .filter(student -> !student.isProgrammingExperience())
                .limit(5)
                .toArray(Student[]::new);

        var learners = Arrays.stream(students)
                .filter(student -> (student.getAge() - student.getAgeEnrolled() >= 7) && student.getMonthsSinceActive() < 12)
                .filter(student -> !student.isProgrammingExperience())
                .limit(5)
                .collect(Collectors.toList());

        Collections.shuffle(learners);
//        Collections.shuffle(longTimeLearners);


    }
}
