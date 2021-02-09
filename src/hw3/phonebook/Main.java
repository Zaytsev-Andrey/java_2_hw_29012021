package hw3.phonebook;

import java.util.Collection;

// Задание 2
public class Main {
    public static void main(String[] args) {
        System.out.println("Телефонный справочник");
        Phonebook phonebook = new Phonebook();
        phonebook.add("Александров", "79181234567");
        phonebook.add("Александров", "79161234567");
        phonebook.add("Быстрова", "79031234567");
        phonebook.add("Колобков", "79261234567");

        System.out.println("Александров - " + formatPhoneNumbers(phonebook.get("Александров")));
        System.out.println("Быстрова - " + formatPhoneNumbers(phonebook.get("Быстрова")));
        System.out.println("Колобков - " + formatPhoneNumbers(phonebook.get("Колобков")));

        System.out.println();
        System.out.println("Телефонный справочник (v2)");
        Phonebook2 phonebook2 = new Phonebook2();
        phonebook2.add("Александров", "79181234567");
        phonebook2.add("Александров", "79161234567");
        phonebook2.add("Быстрова", "79031234567");
        phonebook2.add("Колобков", "79261234567");

        System.out.println("Александров - " + formatPhoneNumbers(phonebook2.get("Александров")));
        System.out.println("Быстрова - " + formatPhoneNumbers(phonebook2.get("Быстрова")));
        System.out.println("Колобков - " + formatPhoneNumbers(phonebook2.get("Колобков")));
    }

    private static String formatPhoneNumbers(Collection<String> phoneNumbers) {
        return phoneNumbers.toString().replaceAll("\\[|\\]", "");
    }
}
