package by.epam.composite.parser.impl;

import by.epam.composite.entity.TextComponent;
import by.epam.composite.entity.TextComposite;
import by.epam.composite.entity.TextType;
import by.epam.composite.exception.TextException;
import by.epam.composite.parser.BaseParser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TextParser implements BaseParser {
    private static final Logger logger = LogManager.getLogger();
    private static final TextParser INSTANCE = new TextParser();
    private static final int SPACE_NUMBER_BEFORE_PARAGRAPH = 6;
    private static final String REGEXP_FOR_TEXT_PARSING = "\\s{" + SPACE_NUMBER_BEFORE_PARAGRAPH + "}";
    private final BaseParser paragraphParser = ParagraphParser.getInstance();

    private TextParser() {
    }

    public static TextParser getInstance() {
        return INSTANCE;
    }

    @Override
    public TextComponent parse(String text) throws TextException {
        String[] paragraphs = text.strip().split(REGEXP_FOR_TEXT_PARSING);
        TextComponent textNode = new TextComposite(TextType.TEXT);
        for (var paragraph : paragraphs) {
            paragraph = paragraph.strip();
            TextComponent paragraphNode = paragraphParser.parse(paragraph);
            textNode.add(paragraphNode);
        }
        logger.log(Level.INFO, "Text parsed successfully");
        return textNode;
    }
}
