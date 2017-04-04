package pl.parser.nbp;

import pl.parser.nbp.model.Exchange;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

class MathOperations {

    private static Logger logger = Logger.getLogger("MathOperations");

    static String calculateArithmeticMean(List<Exchange> exchanges) throws IOException {
        logger.info("Calculating arithmetic mean for buying exchange");
        double sum = exchanges.stream()
                .mapToDouble(Exchange::getBuyingExchange)
                .sum();
        double result = sum / (double) exchanges.size();
        return formatResult(result);
    }

    static String calculateStandardDeviation(List<Exchange> exchanges) throws IOException {
        logger.info("Calculating standard deviation for selling exchange");
        double average = countAverageSell(exchanges);
        double sum = exchanges.stream()
                .map(Exchange::getSellingExchange)
                .mapToDouble(aDouble -> Math.pow(aDouble - average, 2.0))
                .sum();
        double result = Math.pow(sum / (double) exchanges.size(), 0.5);
        return formatResult(result);
    }

    private static String formatResult(double result) {
        NumberFormat instance = NumberFormat.getInstance(Locale.FRANCE);
        instance.setMaximumFractionDigits(4);
        instance.setMinimumFractionDigits(4);
        return instance.format(result);
    }

    private static double countAverageSell(List<Exchange> exchanges) {
        logger.info("Calculating arithmetic mean for selling exchange");
        double sum = exchanges.stream()
                .mapToDouble(Exchange::getSellingExchange)
                .sum();
        return sum / (double) exchanges.size();
    }

}
