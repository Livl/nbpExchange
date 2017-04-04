package pl.parser.nbp.parser;

import pl.parser.nbp.model.Exchange;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InputParser {
    public static List<Exchange> parse(String currency, String startDate, String endDate) throws IOException {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        if(start.isAfter(end)){
            throw new RuntimeException("Start date should be before end date");
        }

        long range = ChronoUnit.DAYS.between(start, end);
        List<Exchange> exchanges = new ArrayList<>();
        for (int i = 0; i <= range; i++) {
            Optional<Exchange> exchange = Parser.findExchange(start.plusDays(i), currency);
            exchange.ifPresent(exchanges::add);
        }

        if(exchanges.isEmpty()){
            throw new RuntimeException("Incorrect input value. Check currency code and dates");
        }
        return exchanges;
    }
}
