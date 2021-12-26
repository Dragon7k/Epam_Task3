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

public class LexemeParser implements BaseParser {
    private static final Logger logger = LogManager.getLogger();
    private static final LexemeParser INSTANCE = new LexemeParser();
    private static final String REGEXP_FOR_LEXEME_PARSING = "(?<=[«(])|(?=[—:,).»!?])";
    private static final String REGEXP_FOR_WORD = "(\\w|-)+";
    private final BaseParser wordParser = WordParser.getInstance();

    private LexemeParser() {
    }

    public static LexemeParser getInstance() {
        return INSTANCE;
    }

    @Override
    public TextComponent parse(String text) throws TextException {
        String[] lexemes = text.strip().split(REGEXP_FOR_LEXEME_PARSING);
        TextComposite textNode = new TextComposite(TextType.LEXEME);
        for (var lexeme : lexemes) {
            TextComponent lexemeNode;
            if (lexeme.matches(REGEXP_FOR_WORD)) {
                lexemeNode = wordParser.parse(lexeme);
            } else {
                lexemeNode = new Symbol(TextType.PUNCTUATION_SYMBOL, lexeme.charAt(0));
            }
            textNode.add(lexemeNode);
        }
        logger.log(Level.INFO, "Word parsed successfully");
        return textNode;
    }
}
