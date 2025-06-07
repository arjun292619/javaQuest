package collections.maps;

import collections.SetsDemo.Contact;
import collections.SetsDemo.ContactData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        String lineSeparator = "-".repeat(50);
        System.out.println(lineSeparator);
        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.forEach(System.out::println);
        System.out.println(lineSeparator);

        Map<String, Contact> contactsMap = new HashMap<>();
        for (Contact c : fullList) {
            contactsMap.put(c.getName(), c);
        }

        contactsMap.forEach((k, v) -> System.out.println("key= " + k + " , value= " + v));
        System.out.println(lineSeparator);
        System.out.println(contactsMap.get("Charlie Brown"));
        System.out.println(contactsMap.get("Charlie brown"));
        Contact defaultContact = new Contact("Chuck Brown");
        System.out.println(contactsMap.getOrDefault("Charlie brown", defaultContact));
        System.out.println(lineSeparator);
        contactsMap.clear();
        for (Contact c : fullList) {
            Contact duplicate = contactsMap.put(c.getName(), c);
            if (duplicate != null) {
//                System.out.println("duplicate: " + duplicate);
//                System.out.println("current: " + c);
                contactsMap.put(c.getName(), c.mergeContact(duplicate));
            }
//            'else {
//                System.out.println("inserting 1st entry \n duplicate: " + duplicate);
//                System.out.println("current: " + c);
//                System.out.println("-".repeat(25));
//            }
        }
        contactsMap.forEach((k, v) -> System.out.println("key= " + k + " , value= " + v));
        System.out.println(lineSeparator);
        contactsMap.clear();
        for (Contact c : fullList) {
            contactsMap.putIfAbsent(c.getName(), c);
        }
        contactsMap.forEach((k, v) -> System.out.println("key= " + k + " , value= " + v));
        System.out.println(lineSeparator);
        contactsMap.clear();
        for (Contact c : fullList) {
            Contact temp = contactsMap.putIfAbsent(c.getName(), c);
            if (temp != null) {
                contactsMap.put(c.getName(), c.mergeContact(temp));
            }
        }
        contactsMap.forEach((k, v) -> System.out.println("key= " + k + " , value= " + v));
        System.out.println(lineSeparator);
        contactsMap.clear();
        fullList.forEach(contact -> contactsMap.merge(contact.getName(), contact,
                (o, n) -> {
                    System.out.println("old: " + o + ", new: " + n);
                    Contact merged = o.mergeContact(n);
                    System.out.println("merged: " + merged);
                    return merged;
                }));
        contactsMap.forEach((k, v) -> System.out.println("key= " + k + " , value= " + v));
    }
}
