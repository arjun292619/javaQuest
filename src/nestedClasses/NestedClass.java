package nestedClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NestedClass {
    public static void main(String[] args) {
        System.out.println("-".repeat(50));
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(100001, "Ralph", 2015),
                new Employee(100005, "Carole", 2021),
                new Employee(100022, "Jane", 2013),
                new Employee(13151, "William", 2020),
                new Employee(100050, "Laura", 2018),
                new Employee(120050, "Jim", 2024),
                new Employee(103255, "Jeremy", 2010),
                new Employee(100434, "Elizabeth", 2022)
        ));
        System.out.println("Using the Comparator outside the class");
        var comparator = new EmployeeContractor<>();
        Collections.sort(employees, comparator);
        employees.forEach(System.out::println);
        var ec = new Employee.EmployeeComparator<>();

//        employees.sort(new Employee.EmployeeComparator<>());
        System.out.println("-".repeat(50));
        System.out.println("Using the static nested Comparator inside the class");
        employees.sort(new Employee.EmployeeComparator<>("yearStarted").reversed());
        employees.forEach(System.out::println);

        System.out.println("-".repeat(50));
        System.out.println("Using the nested instance class Comparator inside the class");
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(100201, "Meg", 2019, "Target"),
                new StoreEmployee(140071, "Joe", 2020, "Walmart"),
                new StoreEmployee(165001, "Steve", 2015, "Macy's"),
                new StoreEmployee(165901, "Tom", 2021, "BestBuy"),
                new StoreEmployee(102501, "Nicole", 2016, "Aldi"),
                new StoreEmployee(170401, "Beth", 2018, "Safeway"),
                new StoreEmployee(194301, "marty", 2017, "Office Depot")
        ));

//        storeEmployees.sort(new StoreEmployee.EmployeeComparator<>()); this uses employee comparator inherited from Employee class

        var scomparator = new StoreEmployee().new StoreEmployeeComparator<>();
        storeEmployees.sort(scomparator);
        storeEmployees.forEach(System.out::println);

        System.out.println("-".repeat(50));
        System.out.println("Using local classes inside the block to create a local class");
        System.out.println("With Pig Latin Names");
        addPigLatinName(storeEmployees);
    }

    public static void addPigLatinName(List<? extends StoreEmployee> list) {
        class DecoratedEmployee extends StoreEmployee implements Comparable<DecoratedEmployee> {
            private String pigLatinName;
            private Employee originalInstance;

            public DecoratedEmployee(String pigLatinName, Employee originalInstance) {
                this.pigLatinName = pigLatinName;
                this.originalInstance = originalInstance;
            }

            @Override
            public String toString() {
                return originalInstance.toString() + " " +
                        pigLatinName;
            }

            @Override
            public int compareTo(DecoratedEmployee o) {
                return pigLatinName.compareTo(o.pigLatinName);
            }
        }

        List<DecoratedEmployee> deList = new ArrayList<>(list.size());

        for (StoreEmployee e : list) {
            String name = e.getName();
            String pigLatin = name.substring(1) + name.charAt(0) + "ay";
            deList.add(new DecoratedEmployee(pigLatin, e));
        }
        deList.stream().sorted().forEach(System.out::println);
    }
}
