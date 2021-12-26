package by.epam.composite.parser;

import by.epam.composite.entity.TextComponent;
import by.epam.composite.entity.TextComposite;
import by.epam.composite.exception.TextException;

public interface BaseParser {
    TextComponent parse(String textValue) throws TextException;
}
