package pl.parser.nbp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Exchange {

    @JacksonXmlProperty(localName = "nazwa_waluty")
    private String currencyName;

    @JacksonXmlProperty(localName = "przelicznik")
    private String multiplier;

    @JacksonXmlProperty(localName = "kod_waluty")
    private String currencyCode;

    @JacksonXmlProperty(localName = "kurs_kupna")
    @JsonDeserialize(using = DoubleDeserializer.class)
    private double buyingExchange;

    @JacksonXmlProperty(localName = "kurs_sprzedazy")
    @JsonDeserialize(using = DoubleDeserializer.class)
    private double sellingExchange;

    public double getSellingExchange() {
        return sellingExchange;
    }

    public void setSellingExchange(double sellingExchange) {
        this.sellingExchange = sellingExchange;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getMultiplier() {
        return multiplier;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getBuyingExchange() {
        return buyingExchange;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setMultiplier(String multiplier) {
        this.multiplier = multiplier;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setBuyingExchange(double buyingExchange) {
        this.buyingExchange = buyingExchange;
    }
}
