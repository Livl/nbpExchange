package pl.parser.nbp.parser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import pl.parser.nbp.model.Exchange;
import pl.parser.nbp.model.ExchangeTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

class Parser {
    private static Logger logger = Logger.getLogger("Parser");

    static Optional<Exchange> findExchange(LocalDate localDate, String currency) throws IOException {
        Optional<ExchangeTable> exchangeTable = getExchangeTable(localDate);
        logger.info("Searching for currency: " + currency);
        return exchangeTable
                .map(ExchangeTable::getExchanges)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .filter(exchange -> exchange.getCurrencyCode().equals(currency))
                .findFirst();
    }

    private static Optional<ExchangeTable> getExchangeTable(LocalDate localDate) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        return getXmlFileName(localDate)
                .flatMap(filename -> readExchangeRates(xmlMapper, filename));
    }

    private static Optional<ExchangeTable> readExchangeRates(XmlMapper xmlMapper, String filename) {
        try {
            URL url = new URL("http://www.nbp.pl/kursy/xml/" + filename + ".xml");
            logger.info("Reading content from " + filename);
            ExchangeTable value = xmlMapper.readValue(url, ExchangeTable.class);
            return Optional.of(value);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private static Optional<String> getXmlFileName(LocalDate localDate) throws IOException {
        URL url;
        if (localDate.getYear() == Year.now().getValue()) {
            logger.info("Searching filename from: dir.txt");
            url = new URL("http://www.nbp.pl/kursy/xml/dir.txt");
        } else {
            logger.info("Searching filename from: dir " + localDate.getYear() + ".txt");
            url = new URL("http://www.nbp.pl/kursy/xml/dir" + localDate.getYear() + ".txt");
        }
        return matchNameFromDate(localDate, url);
    }

    private static Optional<String> matchNameFromDate(LocalDate localDate, URL url) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String date = localDate.format(DateTimeFormatter.ofPattern("yyMMdd"));
        return in.lines()
                .filter(s -> s.matches("c[0-9]{3}z" + date))
                .findFirst();
    }

}
