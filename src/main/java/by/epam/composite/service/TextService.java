package by.epam.composite.service;

import by.epam.composite.entity.TextComponent;
import by.epam.composite.entity.TextComposite;
import by.epam.composite.exception.TextException;

import java.util.List;
import java.util.Map;

public interface TextService {
    int countSameWord(TextComponent textComponent, String word);
    long countVowel(TextComponent sentence);
    long countConsonants(TextComponent sentence);
    List<TextComponent> findSentenceWithLongestWord(TextComponent textComponent) throws TextException;
    String longestWord(TextComponent textComponent);
}
