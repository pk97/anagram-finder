package com.global.commtech.test.anagramfinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Logger;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class AnagramCommandLineRunner implements CommandLineRunner {
    private Logger logger;

    @Override
    public void run(final String... args) throws Exception {
        final File inputFile = validate(args);

        findAnagrams(inputFile);

    }

    private void findAnagrams(File inputFile) {

        try ( BufferedReader reader = new BufferedReader(new FileReader(inputFile))){
            String line = reader.readLine();
            String nextLine = null;
            TreeMap<String, StringBuilder> AnagramMap = new TreeMap<>();
            do {
                while (line != null) {
                    String key = getUniqueKey(line);
                    if (AnagramMap.containsKey(key)) {
                        AnagramMap.put(key, AnagramMap.get(key).append(", ").append(line));
                    } else {
                        AnagramMap.put(key, new StringBuilder(line));
                    }
                     nextLine = reader.readLine();
                    boolean nextSetOfWords = nextLine != null && line.length() != nextLine.length();
                    if (nextSetOfWords) break;
                    line = nextLine;
                }

                Path output = Paths.get("output.txt");
                AppendAnagrams(output, AnagramMap);
                line = nextLine;
                AnagramMap.clear();
            } while (nextLine != null);


        } catch (IOException e) {
            logger.info("error while reading the file");
        }
    }

    private static String getUniqueKey(String line) {
        char[] charKey = line.toCharArray();
        Arrays.sort(charKey);
        return new String(charKey);
    }

    private static void AppendAnagrams(Path output, TreeMap<String, StringBuilder> AnagramMap) throws IOException {
        Files.write(output, AnagramMap.values(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    private static File validate(String[] args) {
        Assert.isTrue(args.length == 1, "Please ensure that the input file is provided");

        final File file = new File(args[0]);
        Assert.isTrue(file.exists(), args[0] + " Does not exist");
        return file;
    }
}


//code
//test
//log
//readme
// time & space complexity