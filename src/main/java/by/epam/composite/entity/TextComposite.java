package by.epam.composite.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextComposite implements TextComponent{
    private static final String TABULATION_FOR_TEXT = "    ";
    private List<TextComponent> textComponents = new ArrayList<>();
    private TextType textType;

    public TextComposite() {}

    public TextComposite(TextType textType) {
        this.textType = textType;
    }

    public List<TextComponent> getTextComponents() {
        return textComponents;
    }

    public void setTextComponents(List<TextComponent> textComponents) {
        this.textComponents = textComponents;
    }

    public TextType getComponentType() {
        return textType;
    }

    public void setComponentType(TextType textType) {
        this.textType = textType;
    }

    @Override
    public void add(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public void remove(TextComponent textComponent) {
        textComponents.remove(textComponent);
    }

    @Override
    public TextType getType() {
        return textType;
    }

    @Override
    public List<TextComponent> getChildren() {
        return List.copyOf(textComponents);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {return true;}
        if (object == null || getClass() != object.getClass()) {return false;}
        TextComposite aThat = (TextComposite) object;
        if(getTextComponents() == null) {
            if(aThat.getTextComponents() != null){return false;}
        } else if(!getTextComponents().equals(aThat.getTextComponents())){return false;}
        if(getComponentType() == null) {
            return aThat.getComponentType() == null;
        } else return getComponentType().equals(aThat.getComponentType());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getComponentType() == null ? 0 : getComponentType().hashCode());
        result = prime * result + (getTextComponents() == null ? 0 : getTextComponents().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String delimiter = textType.getDelimiter();
        if (this.textType == TextType.TEXT) {
            builder.append(TABULATION_FOR_TEXT);
        }
        for (TextComponent textNode : this.getChildren()) {
            builder.append(textNode.toString()).append(delimiter);
        }
        return builder.toString();
    }

    public static class SentenceComparator implements Comparator<TextComponent> {

        @Override
        public int compare(TextComponent textComponent1, TextComponent textComponent2) {
            return Integer.compare(textComponent1.getChildren().size(), textComponent2.getChildren().size());
        }
    }
}
