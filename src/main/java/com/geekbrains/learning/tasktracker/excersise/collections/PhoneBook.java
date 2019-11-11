package com.geekbrains.learning.tasktracker.excersise.collections;

import java.util.*;

public class PhoneBook {
    Set<PhoneNumber> records = new HashSet<>();

    class PhoneNumber {
        String name;
        Set<String> phones;

        PhoneNumber(String name, String... phones) {
            this.name = name;
            this.phones = new HashSet<>(Arrays.asList(phones));
        }

        @Override
        public String toString() {
            return "Name: \"" + name + "\" Pnones: " + phones;
        }
    }

    public void add(String name, String... phones) {
        PhoneNumber phoneRecords = new PhoneNumber(name, phones);
        this.records.add(phoneRecords);
    }

    public List<PhoneNumber> get(String name) {
        List<PhoneNumber> result = new ArrayList<>();
        for (PhoneNumber phoneNumber : records) {
            if (phoneNumber.name.equals(name)) {
                result.add(phoneNumber);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PhoneBook myPhoneBook = new PhoneBook();
        System.out.println(myPhoneBook.records);
        myPhoneBook.add("Али-Баба", "8-800-001", "8-800-002");
        myPhoneBook.add("Белый", "8-555-01", "8-555-02", "8-555-03");
        myPhoneBook.add("Шереметьев", "8-555-01", "8-555-02", "8-555-03");
        myPhoneBook.add("Шереметьев", "777");
        System.out.println(myPhoneBook.records);
        System.out.println("- - - - - - - -");
        System.out.println(myPhoneBook.get("Шереметьев"));
    }

}
