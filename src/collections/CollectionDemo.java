package collections;

import java.util.*;

public class CollectionDemo {
    public static void main(String[] args) {
        System.out.println("-".repeat(50));
//        List<String> nameList = new ArrayList<>();
        Collection<String> nameList = new HashSet<>();
        String[] names = {"Anna", "Carol", "Bob", "Edna"};
        nameList.addAll(Arrays.asList(names));
        System.out.println(nameList);
        nameList.add("Fred");
        nameList.addAll(Arrays.asList("George", "Gary", "Grace"));
        System.out.println(nameList);
        System.out.println("-".repeat(50));
        System.out.println("Is Gary in the list: " + nameList.contains("Gary"));
        System.out.println("-".repeat(50));

        nameList.removeIf(s -> s.charAt(0) == 'G');
        System.out.println(nameList);
        System.out.println("Is Gary in the list: " + nameList.contains("Gary"));
        System.out.println("-".repeat(50));System.out.println("-".repeat(50));
    }
}
