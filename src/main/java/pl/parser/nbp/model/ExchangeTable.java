package pl.parser.nbp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "tabela_kursow")
public class ExchangeTable {

    @JacksonXmlProperty(localName = "numer_tabeli")
    private String id;

    @JacksonXmlProperty(localName = "data_publikacji")
    private String snapshotDate;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "pozycja")
    private List<Exchange> exchanges;

    public String getId() {
        return id;
    }

    public String getSnapshotDate() {
        return snapshotDate;
    }

    public List<Exchange> getExchanges() {
        return exchanges;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSnapshotDate(String snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public void setExchanges(List<Exchange> exchanges) {
        this.exchanges = exchanges;
    }
}
