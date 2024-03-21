package com.global.commtech.test.anagramfinder;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class Printer {

    void print(Collection<StringBuilder> line) {
        System.out.println(line);
    }
}
