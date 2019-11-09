package com.geekbrains.learning.tasktracker.excersise.collections;

import java.util.*;

public class CollClass {
    public static void main(String[] args) {
        String[] wordList = {"Создать", "массив", "набором", "слов", "слов", "должны", "встречаться", "повторяющиеся",
                "вывести", "список", "уникальных", "слов", "из", "которых", "состоит", "массив"};

        System.out.println("Всего слов: " + wordList.length + ". Входной массив: " + Arrays.toString(wordList));

        Set<String> wordsSet = new HashSet<>(Arrays.asList(wordList));
        System.out.println("Из них уникальных: " + wordsSet.size() + ". А именно: " + wordsSet);

        List<String> repeatAble = new ArrayList<>(Arrays.asList(wordList));
        for (String word : wordsSet) {
            repeatAble.remove(word);
        }
        Set<String> nonRepeatWordsSet = new HashSet<>(wordsSet);
        nonRepeatWordsSet.removeAll(repeatAble);
        System.out.println("Из них не имеющих дубликатов: " + nonRepeatWordsSet.size() + " А именно: " + nonRepeatWordsSet);

        System.out.println("-------------");
        for (String word : wordsSet) {
            int count = 0;
            for (String wrd : wordList) {
                if (word.equals(wrd)) {
                    count++;
                }
            }
            System.out.println(String.format("Слово \"%s\" встречается %d раз", word, count));
        }

    }

}
