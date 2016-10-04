package net.eutkin.parser.helper;

import lombok.Getter;
import lombok.experimental.Accessors;
import net.eutkin.domain.News;
import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.parse;

/**
 * Создан 04.10.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Accessors(fluent = true)
@Service
public class SAXHandler extends DefaultHandler {

    @Getter
    private List<News> newsList;
    private News news;
    private String content;

    private final DateTimeFormatter formatter;

    public SAXHandler() {
        this.formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
        this.newsList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       if ("item".equals(qName)) {
            this.news = new News();
       }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        switch (qName) {
            //Add the employee to list once end tag is found
            case "item":
                this.newsList.add(news);
                break;
            case "title":
                news.title(content);
                break;
            case "link":
                news.link(content);
                break;
            case "pubDate":
                LocalDateTime pubDate = parse(content, formatter);
                news.pubDate(pubDate);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }

}
