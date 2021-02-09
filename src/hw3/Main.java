package hw3;

import java.util.HashMap;
import java.util.Map;

// Задание 1
public class Main {
    public static void main(String[] args) {
        System.out.println("Уникальные слова и их количество");
        String[] words = {"Вот", "дом",
                "Который", "построил", "Джек",
                "А", "это", "пшеница",
                "Которая", "в", "темном", "чулане", "хранится",
                "В", "доме",
                "Который", "построил", "Джек",
                "А", "это", "веселая", "птица-синица",
                "Которая", "часто", "ворует", "пшеницу",
                "Которая", "в", "темном", "чулане", "хранится",
                "В", "доме",
                "Который", "построил", "Джек"};

        for (Map.Entry<String, Integer> uniqueWord : getCountOfEachWord(words).entrySet()) {
            System.out.printf("Слово \"%s\" встречается - %d раз(а)\n", uniqueWord.getKey(), uniqueWord.getValue());
        }
    }

    private static Map<String, Integer> getCountOfEachWord(String[] words) {
        Map<String, Integer> uniqueWords = new HashMap<>();
        for (String word : words) {
            uniqueWords.merge(word.toLowerCase(), 1, (k, v) -> k += 1);
        }

        return uniqueWords;
    }
}
