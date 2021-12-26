package by.epam.composite.entity;

import by.epam.composite.exception.TextException;

import java.util.List;

public interface TextComponent {

    void add(TextComponent component) throws TextException;

    void remove(TextComponent component) throws TextException;

    TextType getType();

    List<TextComponent> getChildren();

    String toString();
}
