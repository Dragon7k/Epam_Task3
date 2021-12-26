package by.epam.composite.parser.impl;

import by.epam.composite.entity.TextComponent;
import by.epam.composite.entity.TextComposite;
import by.epam.composite.entity.TextType;
import by.epam.composite.exception.TextException;
import by.epam.composite.parser.BaseParser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser implements BaseParser {
    private static final Logger logger = LogManager.getLogger();
    private static final SentenceParser INSTANCE = new SentenceParser();
    private static final String REGEXP_FOR_SENTENCE_PARSING = "\\s+";
    private final BaseParser lexemeParser = LexemeParser.getInstance();

    private SentenceParser() {
    }

    public static SentenceParser getInstance() {
        return INSTANCE;
    }

    @Override
    public TextComponent parse(String text) throws TextException {
        String[] words = text.split(REGEXP_FOR_SENTENCE_PARSING);
        TextComponent textNode = new TextComposite(TextType.SENTENCE);
        for (var word : words) {
            TextComponent wordNode = lexemeParser.parse(word);
            textNode.add(wordNode);
        }
        logger.log(Level.INFO, "Sentence parsed successfully");
        return textNode;
    }
}
