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
    @Autowired
    private Printer printer;

    private static final Logger logger = LoggerFactory.getLogger(AnagramFinderService.class);

     void findAnagrams(final File inputFile) {

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line = reader.readLine();
            String nextLine = null;
            TreeMap<String, StringBuilder> anagramMap = new TreeMap<>();
            do {
                while (ifLineExists(line)) {
                    String key = getUniqueKey(line);
                    if (anagramMap.containsKey(key)) {
                        anagramMap.put(key, anagramMap.get(key).append(",").append(line));
                    } else {
                        anagramMap.put(key, new StringBuilder(line));
                    }
                    nextLine = reader.readLine();
                    boolean lengthOfNextLineIsNotEqualToCurrentLine = nextLine != null && line.length() != nextLine.length();
                    if (lengthOfNextLineIsNotEqualToCurrentLine) break;
                    line = nextLine;
                }

                anagramMap.values()
                        .forEach(i -> printer.print(i.toString()));
                line = nextLine;
                anagramMap.clear();
            } while (ifLineExists(nextLine));


        } catch (IOException e) {
            logger.error("Error while reading the data");
        }
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
