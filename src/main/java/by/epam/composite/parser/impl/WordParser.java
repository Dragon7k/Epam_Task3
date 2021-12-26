package by.epam.composite.parser.impl;

import by.epam.composite.entity.Symbol;
import by.epam.composite.entity.TextComponent;
import by.epam.composite.entity.TextComposite;
import by.epam.composite.entity.TextType;
import by.epam.composite.exception.TextException;
import by.epam.composite.parser.BaseParser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class WordParser implements BaseParser {
    private static final Logger logger = LogManager.getLogger();
    private static final WordParser INSTANCE = new WordParser();
    private static final String REGEXP_FOR_WORD_PARSING = "";
    private static final String REGEXP_FOR_PUNCTUATION = "-";

    private WordParser() {
    }

    public static WordParser getInstance() {
        return INSTANCE;
    }

    @Override
    public TextComponent parse(String text) throws TextException {
        String[] symbols = text.split(REGEXP_FOR_WORD_PARSING);
        TextComponent textNode = new TextComposite(TextType.WORD);
        for (var symbol : symbols) {
            Symbol symbolNode = new Symbol();
            symbolNode.setSymbol(symbol.charAt(0));
            if (symbol.matches(REGEXP_FOR_PUNCTUATION)) {
                symbolNode.setComponentType(TextType.PUNCTUATION_SYMBOL);
            } else {
                symbolNode.setComponentType(TextType.LETTER);
            }
            textNode.add(symbolNode);
        }
        logger.log(Level.INFO, "Word parsed successfully");
        return textNode;
    }
}
