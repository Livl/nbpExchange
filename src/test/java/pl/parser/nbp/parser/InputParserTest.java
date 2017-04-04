package pl.parser.nbp.parser;


import org.junit.Test;

import java.io.IOException;

public class InputParserTest {

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionDueToNonExistingDate() throws IOException {
        InputParser.parse("EUR", "2013-02-29", "2013-01-31");
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionDueToInvalidDateRange() throws IOException {
        InputParser.parse("EUR", "2013-02-28", "2013-01-20");
    }
}