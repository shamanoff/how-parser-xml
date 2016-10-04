package net.eutkin.parser;

import net.eutkin.domain.News;
import net.eutkin.parser.helper.SAXHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.springframework.util.Assert.notNull;

/**
 * Создан 04.10.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Service
public class SaxParser implements XmlParser {

    private final SAXHandler handler;

    @Autowired
    public SaxParser(SAXHandler handler) {
        notNull(handler);
        this.handler = handler;
    }

    @Override
    public List<News> parse(InputStream is) {
        try {
            return parseXml(is);
        } catch (ParserConfigurationException e) {
            return emptyList();
        } catch (SAXException | IOException e) {
            return emptyList();
        }
    }

    private List<News> parseXml(InputStream is) throws ParserConfigurationException, IOException, SAXException {
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = parserFactor.newSAXParser();
        parser.parse(is, handler);
        return handler.newsList();

    }
}
