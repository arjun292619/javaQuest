package collections;

import collections.SetsDemo.Contact;
import collections.SetsDemo.ContactData;

import java.util.*;
import java.util.stream.Collectors;

public class OrderedSetDemo {
    public static void main(String[] args) {
        List<Contact> emails = ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
        Comparator<Contact> nameSorter = Comparator.comparing(Contact::getName);
        NavigableSet<Contact> sorted = new TreeSet<>(nameSorter);
        sorted.addAll(phones);
        sorted.forEach(System.out::println);
        String lineSeparator = "_".repeat(90);
        System.out.println(lineSeparator);

//        var justNames = sorted.stream()
//                .map(Contact::getName)
//                .collect(TreeSet::new, NavigableSet::add, NavigableSet::addAll);

        NavigableSet<String> justNames = new TreeSet<>();
        phones.forEach(phone -> justNames.add(phone.getName()));
        System.out.println(justNames);
        System.out.println(lineSeparator);
        NavigableSet<Contact> dupeSet = new TreeSet<>(sorted);
        dupeSet.addAll(emails);
        dupeSet.forEach(System.out::println);
        System.out.println(lineSeparator);
        List<Contact> contactList = new ArrayList<>(dupeSet);
        contactList.sort(sorted.comparator());
        contactList.forEach(System.out::println);
        System.out.println(lineSeparator);
        Contact min = Collections.min(dupeSet, nameSorter);
        Contact max = Collections.max(dupeSet, sorted.comparator());
        System.out.println(lineSeparator);
        Contact first = dupeSet.first();
        Contact last = dupeSet.last();
        System.out.printf("min = %s, first = %s %n", min.getName(), first.getName());
        System.out.printf("max = %s, last = %s %n", max.getName(), last.getName());
        System.out.println(lineSeparator);
        System.out.printf("Before poll action: %s %n", dupeSet);
/*        Contact firstContact = dupeSet.pollFirst();
        Contact lastContact = dupeSet.pollLast();
        System.out.println(firstContact.toString());
        System.out.println(lastContact.toString());*/
        System.out.printf("After poll action: %s %n", dupeSet);
//        Demo of subset methods
        System.out.println(lineSeparator);
        System.out.println("Demo of subset methods");
        System.out.println(lineSeparator);
        Contact daffy = new Contact("Daffy Duck");
        Contact daisy = new Contact("Daisy Duck");
        Contact snoopy = new Contact("Snoopy");
        Contact archie = new Contact("Archie");

//        Ceiling and Higher Methods
        System.out.println("Ceiling and Higher Methods");
        for (Contact c : List.of(daffy, daisy, last, snoopy, archie)) {
            System.out.printf("ceiling(%s) = %s%n", c.getName(), dupeSet.ceiling(c));
            System.out.printf("higher(%s) = %s%n", c.getName(), dupeSet.higher(c));
            System.out.println("-".repeat(25));
        }

        //        Floor and lower Methods
        System.out.println("Floor and lower Methods");
        for (Contact c : List.of(daffy, daisy, last, first, snoopy, archie)) {
            System.out.printf("floor(%s) = %s%n", c.getName(), dupeSet.floor(c));
            System.out.printf("lower(%s) = %s%n", c.getName(), dupeSet.lower(c));
            System.out.println("-".repeat(25));
        }

//        Descending Sets based on argument
        NavigableSet<Contact> descendingSet = dupeSet.descendingSet();
        descendingSet.forEach(System.out::println);

        System.out.println(lineSeparator);
        Contact lastContact = descendingSet.pollLast();
        System.out.println("Removed Contact: " + lastContact);
        descendingSet.forEach(System.out::println);
        System.out.println(lineSeparator);
        dupeSet.forEach(System.out::println);
        System.out.println(lineSeparator);

//        extracting headsets and tailsets using Treeset methods methods
        Contact marion = new Contact("Maid Marion");
        var headSet = dupeSet.headSet(marion, true);
//        var headSet = dupeSet.headSet(marion);
        headSet.forEach(System.out::println);
        System.out.println(lineSeparator);
        var tailSet = dupeSet.tailSet(marion, false);
//        var tailSet = dupeSet.tailSet(marion);
        tailSet.forEach(System.out::println);
        System.out.println(lineSeparator);
//        extracting subset using Treeset methods methods
        Contact linus = new Contact("Linus Van Pelt");
        var subset = dupeSet.subSet(linus, false, marion, true);
//        var subset = dupeSet.subSet(linus, marion);
        subset.forEach(System.out::println);
    }
}
