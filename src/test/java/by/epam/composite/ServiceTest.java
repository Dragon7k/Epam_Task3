package by.epam.composite;

import by.epam.composite.entity.TextComponent;
import by.epam.composite.exception.TextException;
import by.epam.composite.parser.impl.TextParser;
import by.epam.composite.service.impl.TextServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;



public class ServiceTest {
    private static TextComponent textNode;
    private static final TextServiceImpl textService = new TextServiceImpl();

    @BeforeAll
    static void setUp() throws IOException, TextException {
        URL fileUrl = ServiceTest.class.getClassLoader().getResource("file/text.txt");
        File file = new File(fileUrl.getFile());
        String filePath = file.getAbsolutePath();
        Path path = Paths.get(filePath);
        String text = Files.readString(path);
        TextParser textParser = TextParser.getInstance();
        textNode = textParser.parse(text);
    }
    @Test
    void ifCalculateConsonantsNumberReturnsCorrectNumber(){
        long actual = textService.countConsonants(textNode);
        long expected = 363;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void ifCalculateVowelsNumberReturnsCorrectNumber(){
        long actual = textService.countVowel(textNode);
        int expected = 270;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void ifLongestWordIsCorrect(){
        String actual = textService.longestWord(textNode);
        String expected = "everywhere";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void ifFindSameWordsReturnsCorrectNumber(){
        int actual = textService.countSameWord(textNode,"my");
        int expected = 10;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findSentenceWithLongestWordTest() throws TextException {
        List<TextComponent> arr = textService.findSentenceWithLongestWord(textNode);
        String actual = arr.get(0).toString();
        String expected =  "I fed my cat myself and he followed me everywhere about the house. ";
        Assertions.assertEquals(actual,expected);
    }
}
