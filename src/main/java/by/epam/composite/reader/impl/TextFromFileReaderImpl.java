package by.epam.composite.reader.impl;

import by.epam.composite.exception.TextException;
import by.epam.composite.reader.TextFileFromReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFromFileReaderImpl implements TextFileFromReader {
    private static final Logger logger = LogManager.getLogger();

    public String readText(String filePath) throws TextException {
        if (getClass().getClassLoader().getResource(filePath) == null) {
            throw new TextException("File " + filePath + " does not exist.");
        }
        String fileText;
        try {
            Path pathToFile = Paths.get(getClass().getClassLoader().getResource(filePath).toURI());
            fileText = Files.readString(pathToFile);
        } catch (IOException | URISyntaxException exception) {
            throw new TextException("Error of reading file \"" + filePath + "\": ", exception);
        }
        return fileText;
    }
}
