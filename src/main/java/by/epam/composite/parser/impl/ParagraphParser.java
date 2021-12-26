package by.epam.composite.parser.impl;

import by.epam.composite.entity.TextComponent;
import by.epam.composite.entity.TextComposite;
import by.epam.composite.entity.TextType;
import by.epam.composite.exception.TextException;
import by.epam.composite.parser.BaseParser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser implements BaseParser {
    private static final Logger logger = LogManager.getLogger();
    private static final ParagraphParser INSTANCE = new ParagraphParser();
    private static final String REGEXP_FOR_PARAGRAPH_PARSING = "(?<=((\\.)|(!)|(\\?)))\\s+(?=[A-Z])";
    private final BaseParser sentenceParser = SentenceParser.getInstance();

    private ParagraphParser() {
    }

    public static ParagraphParser getInstance() {
        return INSTANCE;
    }

    @Override
    public TextComponent parse(String text) throws TextException {
        String[] sentences = text.split(REGEXP_FOR_PARAGRAPH_PARSING);
        TextComponent textNode = new TextComposite(TextType.PARAGRAPH);
        for (var sentence : sentences) {
            TextComponent sentenceNode = sentenceParser.parse(sentence);
            textNode.add(sentenceNode);
        }
        logger.log(Level.INFO, "Paragraph parsed successfully");
        return textNode;
    }
}
