package com.global.commtech.test.anagramfinder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testPrint() {
        Printer printer = new Printer();
        Collection<StringBuilder> lines = new ArrayList<>(Arrays.asList(
                new StringBuilder("Hello"),
                new StringBuilder("World")
        ));
        printer.print(lines);
        assertEquals("[Hello, World]" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testPrintEmptyCollection() {
        Printer printer = new Printer();
        Collection<StringBuilder> lines = new ArrayList<>();
        printer.print(lines);
        assertEquals("[]" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testPrintNullCollection() {
        Printer printer = new Printer();
        printer.print(null);
        assertEquals("null" + System.lineSeparator(), outContent.toString());
    }

}