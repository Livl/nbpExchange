package pl.parser.nbp;

import org.junit.Before;
import org.junit.Test;
import pl.parser.nbp.model.Exchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MathOperationsTest {

    private List<Exchange> exchangeList = new ArrayList<>();

    @Before
    public void setUp() {
        exchangeList = Arrays.asList(
                exchange(4.2135, 4.1301),
                exchange(4.2461, 4.1621),
                exchange(4.2370, 4.1530),
                exchange(4.2409, 4.1569)
        );
    }

    @Test
    public void shouldCalculateArithmeticMean() throws IOException {
        String eur = MathOperations.calculateArithmeticMean(exchangeList);
        assertThat(eur, is(equalTo("4,1505")));
    }

    @Test
    public void shouldCalculateStandardDeviation() throws IOException {
        String eur = MathOperations.calculateStandardDeviation(exchangeList);
        assertThat(eur, is(equalTo("0,0125")));
    }

    private Exchange exchange(double sellingExchange, double buyingExchange) {
        Exchange exchange = new Exchange();
        exchange.setSellingExchange(sellingExchange);
        exchange.setBuyingExchange(buyingExchange);
        exchange.setCurrencyCode("EUR");
        return exchange;
    }
}