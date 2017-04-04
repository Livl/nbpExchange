package pl.parser.nbp;

import pl.parser.nbp.model.Exchange;
import pl.parser.nbp.parser.InputParser;

import java.io.IOException;
import java.util.List;

public class MainClass {
    public static void main(String[] args) throws IOException {
        List<Exchange> exchanges = InputParser.parse(args[0], args[1], args[2]);

        String buyingExchange = MathOperations.calculateArithmeticMean(exchanges);
        String sellingExchange = MathOperations.calculateStandardDeviation(exchanges);

        System.out.println(buyingExchange);
        System.out.println(sellingExchange);
    }
}
