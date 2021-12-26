package by.epam.composite.service.impl;

import by.epam.composite.entity.TextComponent;
import by.epam.composite.entity.TextComposite;
import by.epam.composite.entity.TextType;
import by.epam.composite.exception.TextException;
import by.epam.composite.service.TextService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private static final String REGEXP_FOR_VOWEL = "[AEIOUYaeiouy]";
    private static final String REGEXP_FOR_CONSONANTS = "[[^AEIOUYaeiouy]&&A-Za-z]";

    @Override
    public int countSameWord(TextComponent textComponent, String word)  {
        return (int) textComponent.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(l -> l.getType() == TextType.WORD)
                .filter(w -> w.toString().equalsIgnoreCase(word))
                .count();
    }

    @Override
    public long countVowel(TextComponent sentence) {
        return calculateSoundNumber(sentence, REGEXP_FOR_VOWEL);
    }

    @Override
    public long countConsonants(TextComponent sentence) {
        return calculateSoundNumber(sentence, REGEXP_FOR_CONSONANTS);
    }

    @Override
    public List<TextComponent> findSentenceWithLongestWord(TextComponent textComponent) throws TextException {

        Optional<Integer> maxWordLength = textComponent.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .map(this::receiveMaxLettersNumberInWord)
                .max(Integer::compareTo);
        if (maxWordLength.isEmpty()) {
            throw new TextException("There aren't any words in text");
        }
        return textComponent.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .filter(s -> receiveMaxLettersNumberInWord(s) == maxWordLength.get())
                .collect(Collectors.toList());
    }

    @Override
    public String longestWord(TextComponent textComponent) {
        return textComponent.getChildren()
                .stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(TextType.WORD))
                .max(Comparator.comparingInt(w -> w.toString().length()))
                .get().toString();

    }

    private int receiveMaxLettersNumberInWord(TextComponent sentence) {
        return sentence.getChildren().stream()
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType() == TextType.WORD)
                .map(node -> node.getChildren().size())
                .max(Integer::compareTo)
                .orElse(-1);
    }

    private long calculateSoundNumber(TextComponent sentence, String regexpForVowel) {
        return sentence.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(l -> l.getType() == TextType.WORD)
                .flatMap(w -> w.getChildren().stream())
                .filter(l -> l.toString().matches(regexpForVowel)).count();
    }
}
