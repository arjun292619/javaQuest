package streams.StudentCourseStream;

import java.time.LocalDate;
import java.util.*;

public class Student {
    private static long lastStudentId = 1;
    private final static Random random = new Random();
    private final long studentId;
    private final String countryCode;
    private final int ageEnrolled;
    private final int yearEnrolled;
    private final String gender;
    private final boolean programmingExperience;
    private final Map<String, CourseEngagement> engagementMap = new HashMap<>();

    public Student(String countryCode, int ageEnrolled, int yearEnrolled, String gender, boolean programmingExperience, Course... courses) {
        this.studentId = lastStudentId++;
        this.countryCode = countryCode;
        this.ageEnrolled = ageEnrolled;
        this.yearEnrolled = yearEnrolled;
        this.gender = gender;
        this.programmingExperience = programmingExperience;

        for (Course course : courses) {
            addCourse(course, LocalDate.of(yearEnrolled, 1, 1));
        }
    }

    public void addCourse(Course course) {
        addCourse(course, LocalDate.now());
    }

    public void addCourse(Course course, LocalDate enrollDate) {
        engagementMap.put(course.courseCode(), new CourseEngagement(course, enrollDate, "Enrollment"));
    }

    public long getStudentId() {
        return studentId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getAgeEnrolled() {
        return ageEnrolled;
    }

    public int getYearEnrolled() {
        return yearEnrolled;
    }

    public String getGender() {
        return gender;
    }

    public boolean isProgrammingExperience() {
        return programmingExperience;
    }

    public Map<String, CourseEngagement> getEngagementMap() {
        return Map.copyOf(engagementMap);
    }

    public int getYearsSinceEnrolled() {
        return LocalDate.now().getYear() - yearEnrolled;
    }

    public int getAge() {
        return ageEnrolled + getYearsSinceEnrolled();
    }

    public int getMonthsSinceActive(String courseCode) {
        CourseEngagement info = engagementMap.get(courseCode);
        return info == null ? 0 : info.getMonthsSinceActive();
    }

    public int getMonthsSinceActive() {
        int inactiveMonths = (LocalDate.now().getYear() - 2014) * 12;
        for (String key : engagementMap.keySet()) {
            inactiveMonths = Math.min(inactiveMonths, getMonthsSinceActive(key));
        }
        return inactiveMonths;
    }

    public double getpercentComplete(String courseCode) {
        var info = engagementMap.get(courseCode);
        return info == null ? 0 : info.getPercentComplete();
    }

    public void watchLecture(String courseCode, int lectureNumber, int month, int year) {
        var activity = engagementMap.get(courseCode);
        if (activity != null) {
            activity.watchLecture(lectureNumber, LocalDate.of(year, month, 1));
        }
    }

    private static String getRandomVal(String... data) {
        return data[random.nextInt(data.length)];
    }

    private static Course[] getRandomCourses(Course... courses) {
        int count = random.nextInt(1, courses.length + 1);
        List<Course> cList = new ArrayList<>(Arrays.asList(courses));
        Collections.shuffle(cList);
        List<Course> selectedCourses = cList.subList(0, count);
        return selectedCourses.toArray(new Course[0]);
    }

    public static Student getRandomStudent(Course... courses) {
        int maxYear = LocalDate.now().getYear() + 1;
        Course[] randomCourses = getRandomCourses(courses);
        String country = getRandomVal("AU", "CA", "CN", "GB", "AU", "IN", "UA", "US");
        int age = random.nextInt(18, 90);
        int yearEnr = random.nextInt(2015, maxYear);

        Student student = new Student(country, age, yearEnr, getRandomVal("M", "F", "U"),
                random.nextBoolean(),
                randomCourses);

        for (Course course : randomCourses) {
            int lecture = random.nextInt(30, course.lectureCount());
            int year = random.nextInt(student.getYearEnrolled(), maxYear);
            int month = random.nextInt(1, 13);

            if (year == (maxYear - 1)) {
                if (month > LocalDate.now().getMonthValue()) {
                    month = LocalDate.now().getMonthValue();
                }
            }
            student.watchLecture(course.courseCode(), lecture, month, year);
        }
        return student;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", countryCode='" + countryCode + '\'' +
                ", ageEnrolled=" + ageEnrolled +
                ", yearEnrolled=" + yearEnrolled +
                ", gender='" + gender + '\'' +
                ", programmingExperience=" + programmingExperience +
                ", engagementMap=" + engagementMap +
                '}';
    }
}
