package net.eutkin.parser;

import net.eutkin.domain.News;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Создан 04.10.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Service
public class StaxParser implements XmlParser {

    private List<News> newsList = new ArrayList<>();
    private News news;
    private String content;
    private final DateTimeFormatter formatter;

    public StaxParser() {
        this.formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
    }

    @Override
    public List<News> parse(InputStream is) {
        try {
            parseXml(is);
            return newsList;
        } catch (XMLStreamException e) {
            return Collections.emptyList();
        }
    }

    private void parseXml(InputStream is) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(is);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    if ("item".equals(reader.getLocalName())) {
                        this.news = new News();
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    this.content = reader.getText().trim();
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    switch (reader.getLocalName()) {
                        case "item":
                            newsList.add(news); break;
                        case "title":
                            this.news.title(content); break;
                        case "link":
                            this.news.link(content); break;
                        case "pubDate":
                            LocalDateTime pubDate = LocalDateTime.parse(content, formatter);
                            this.news.pubDate(pubDate); break;
                    }
                    break;
            }
        }
    }
}
