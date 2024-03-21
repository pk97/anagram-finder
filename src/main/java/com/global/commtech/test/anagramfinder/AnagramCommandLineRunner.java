package com.global.commtech.test.anagramfinder;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;

@Component
@RequiredArgsConstructor
public class AnagramCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AnagramCommandLineRunner.class);

    @Autowired
    private AnagramFinderService anagramFinderService;

    @Override
    public void run(final String... args) throws Exception {
        final File inputFile = validate(args);
        anagramFinderService.findAnagrams(inputFile);
        logger.info("Successfully found all the anagrams");

    }


    private static File validate(final String[] args) {
        Assert.isTrue(args.length == 1, "Please ensure that the input file is provided");

        final File file = new File(args[0]);
        Assert.isTrue(file.exists(), args[0] + " Does not exist");
        return file;
    }
}