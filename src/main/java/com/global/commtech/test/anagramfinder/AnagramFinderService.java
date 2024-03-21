package com.global.commtech.test.anagramfinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.TreeMap;

@Component
public class AnagramFinderService {
    private Printer printer;

    AnagramFinderService(Printer printer) {
        this.printer = printer;
    }

    private static final Logger logger = LoggerFactory.getLogger(AnagramFinderService.class);
    private static final String separator = ",";

    void findAnagrams(final File inputFile) {

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            groupAnagramsTogether(reader);
        } catch (IOException e) {
            logger.error("Error while reading the data");
        }
    }

    private void groupAnagramsTogether(BufferedReader reader) throws IOException {
        String currentLine = reader.readLine();
        String nextLine = null;
        TreeMap<String, StringBuilder> anagramMap = new TreeMap<>();
        do {
            while (ifLineExists(currentLine)) {
                updateAnagramMap(currentLine, anagramMap);
                nextLine = reader.readLine();
                if (isNextLineIsOfDifferentSize(nextLine, currentLine)) break;
                currentLine = nextLine;
            }
            printer.print(anagramMap.values());
            currentLine = nextLine;
            anagramMap.clear();
        } while (ifLineExists(nextLine));
    }

    private static void updateAnagramMap(String currentLine, TreeMap<String, StringBuilder> anagramMap) {
        String key = getUniqueKey(currentLine);
        if (anagramMap.containsKey(key)) {
            anagramMap.put(key, anagramMap.get(key).append(separator).append(currentLine));
        } else {
            anagramMap.put(key, new StringBuilder(currentLine));
        }
    }

    private static boolean isNextLineIsOfDifferentSize(String nextLine, String line) {
        return nextLine != null && line.length() != nextLine.length();
    }

    private static boolean ifLineExists(String line) {
        return line != null;
    }

    private static String getUniqueKey(final String line) {
        char[] charKey = line.toCharArray();
        Arrays.sort(charKey);
        return new String(charKey);
    }

}
