package by.epam.composite;

import by.epam.composite.entity.TextComponent;
import by.epam.composite.exception.TextException;
import by.epam.composite.parser.impl.TextParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextParserTest {
    @Test
    void ifTextParserParsedCorrectly() throws IOException, TextException {
        URL fileUrl = TextParserTest.class.getClassLoader().getResource("file/text.txt");
        File file = new File(fileUrl.getFile());
        String filePath = file.getAbsolutePath();
        Path path = Paths.get(filePath);
        String text = Files.readString(path);
        TextParser textParser = TextParser.getInstance();
        TextComponent textNode = textParser.parse(text);
        int actual = (int) textNode.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .mapToLong(s -> s.getChildren().size())
                .sum();
        int expected = 168;
        Assertions.assertEquals(expected, actual);
    }

}
