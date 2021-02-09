package hw3.phonebook;

import java.util.*;

public class Phonebook2 {
    private Map<String, String> book;

    public Phonebook2() {
        book = new HashMap<>();
    }

    /**
     * Добавляет в справочник абонента (фамилию и номер телефона). Номера телефонов для всех абонентов являются
     * уникалбными.
     * @param surname фамилия абонента
     * @param phonebook номер телефона абонента
     * @return если номер телефона уже пресутствовал в справочнике метод возврящает предыдущую фамилию, иначе null
     */
    public String add(String surname, String phonebook) {
        return book.put(phonebook, surname);
    }

    /**
     * Возвращает все номера телефонов по переданной фамилии
     * @param surname фамилия абонента
     * @return список номеров телефонов
     */
    public List<String> get(final String surname) {
        List<String> phoneNumbers = new ArrayList<>();
        for (Map.Entry<String, String> subscriber : book.entrySet()) {
            if (subscriber.getValue().equals(surname)) {
                phoneNumbers.add(subscriber.getKey());
            }
        }

        return phoneNumbers;
    }
}
