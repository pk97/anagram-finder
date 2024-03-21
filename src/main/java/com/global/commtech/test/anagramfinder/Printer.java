package com.global.commtech.test.anagramfinder;

import org.springframework.stereotype.Component;

@Component
public class Printer {

    void print(String line) {
        System.out.println(line);
    }
}
