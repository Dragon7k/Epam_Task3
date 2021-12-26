package by.epam.composite.entity;

public enum TextType {
    TEXT("\n    "),
    PARAGRAPH(" "),
    SENTENCE(" "),
    LEXEME(""),
    WORD(""),
    LETTER(""),
    PUNCTUATION_SYMBOL("");

    private final String delimiter;

    TextType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
