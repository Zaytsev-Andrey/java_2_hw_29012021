package hw3.phonebook;

import java.util.*;

public class Phonebook {
    private Map<String, Set<String>> book;

    public Phonebook() {
        book = new HashMap<>();
    }

    /**
     * Добавляет в справочник абонента (фамилию и номер телефона). Одинаковые номера для одного абонента не дублируюся.
     * @param surname фамилия абонента
     * @param phoneNumber номер телефона абонента
     */
    public void add(String surname, String phoneNumber) {
        Set<String> phoneNumbers = book.getOrDefault(surname, new HashSet<>());
        phoneNumbers.add(phoneNumber);
        book.put(surname, phoneNumbers);
    }

    /**
     * Возвращает все номера телефонов по переданной фамилии
     * @param surname фамилия абонента
     * @return множество номеров телефонов
     */
    public Set<String> get(String surname) {
        return book.get(surname);
    }
}
