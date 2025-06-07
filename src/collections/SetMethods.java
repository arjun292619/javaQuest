package collections;

import collections.SetsDemo.Contact;
import collections.SetsDemo.ContactData;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetMethods {
    public static void main(String[] args) {
        List<Contact> emails = ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
        printData("Original Phone List", phones);
        printData("Original Email List", emails);

        Set<Contact> emailContacts = new HashSet<>(emails);
        Set<Contact> phoneContacts = new HashSet<>(phones);

        printData("Phones Set", phoneContacts);
        printData("Emails Set", emailContacts);
        System.out.println("-".repeat(50));
        int index = emails.indexOf(new Contact("Robin Hood"));
        Contact robinhood = emails.get(index);
        robinhood.addEmail("Sherwood Forest");
        robinhood.addEmail("Sherwood Forest");
        System.out.println(robinhood);
        robinhood.replaceEmailIfExists("RHood@sherwoodforest.com", "RHood@sherwoodforest.org");
        System.out.println(robinhood);
        System.out.println("-".repeat(50));

//        Union operation of Sets
        Set<Contact> unionEP = new HashSet<>();
        unionEP.addAll(emails);
        unionEP.addAll(phones);
        printData("Emails ∪ phones - Union of Emails and Phones", unionEP);
//        intersection operation of sets
        Set<Contact> intersectEP = new HashSet<>(emailContacts);
        intersectEP.retainAll(phoneContacts);
        printData("Emails ∩ phones - Interscet of Emails and Phones", intersectEP);
        Set<Contact> intersectPE = new HashSet<>(phoneContacts);
        intersectPE.retainAll(emailContacts);
        printData("Phones ∩ emails - Interscet of phones and emails", intersectPE);
//        asymmetric Operations such as difference such as only phone contacts or only email contacts
        Set<Contact> onlyPhones = new HashSet<>(phoneContacts);
        onlyPhones.removeAll(emailContacts);
        printData("Phones minus emails - Only Phone Contact", onlyPhones);

        Set<Contact> onlyEmails = new HashSet<>(emailContacts);
        onlyEmails.removeAll(phoneContacts);
        printData("Email minus Phones - Only Email Contacts", onlyEmails);
//        Set symmetric difference AUB-AnB
        Set<Contact> symmetricDiff = new HashSet<>(onlyPhones);
        Set<Contact> symmetricDiff1 = new HashSet<>(unionEP);
        symmetricDiff1.removeAll(intersectEP);
        symmetricDiff.addAll(onlyEmails);
        printData("Symmetric Difference of Emails and Phones", symmetricDiff);
        printData("Symmetric Difference of Emails and Phones method 2", symmetricDiff1);


//        printData("Phones Set", phoneContacts);
//        printData("Emails Set", emailContacts);

    }

    public static void printData(String header, Collection<Contact> contacts) {
        System.out.println("-".repeat(50));
        System.out.println(header);
        System.out.println("-".repeat(50));
        contacts.forEach(System.out::println);
    }


}
