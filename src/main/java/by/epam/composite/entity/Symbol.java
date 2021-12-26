package by.epam.composite.entity;

import by.epam.composite.exception.TextException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


public class Symbol implements TextComponent{
    private static final Logger logger = LogManager.getLogger();
    private TextType textType;
    private char symbol;

    public Symbol() {

    }

    public Symbol(TextType textType, char symbol) throws TextException {
        EnumSet<TextType> textTypes = EnumSet.range(TextType.LETTER, TextType.PUNCTUATION_SYMBOL);
        if (!textTypes.contains(textType)) {
            throw new TextException("Invalid type");
        }
        this.symbol = symbol;
        this.textType = textType;
    }

    public TextType getComponentType() {
        return textType;
    }

    public void setComponentType(TextType componentType) {
        this.textType = componentType;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void add(TextComponent textComponent) throws TextException {
        throw new TextException("Unsupported operation \"add\" on symbol");
    }

    @Override
    public void remove(TextComponent textComponent) throws TextException {
        throw new TextException("Unsupported operation \"remove\" on symbol");
    }

    @Override
    public TextType getType() {
        return textType;
    }

    @Override
    public List<TextComponent> getChildren() {
        List<TextComponent> childNodes = new ArrayList<>();
        return childNodes;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {return true;}
        if (object == null || getClass() != object.getClass()) {return false;}
        Symbol symbol1 = (Symbol) object;
        if(getSymbol() != symbol1.getSymbol()){return false;}
        if(getComponentType() == null) {
            return symbol1.getComponentType() == null;
        } else return getComponentType().equals(symbol1.getComponentType());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getComponentType() == null ? 0 : getComponentType().hashCode());
        result = prime * result + Character.hashCode(symbol);
        return result;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }
}
