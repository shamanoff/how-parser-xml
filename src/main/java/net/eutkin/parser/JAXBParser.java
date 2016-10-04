package net.eutkin.parser;

import net.eutkin.domain.News;
import net.eutkin.domain.jaxb.Rss;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Создан 04.10.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Service
public class JAXBParser implements XmlParser {

    @Override
    public List<News> parse(InputStream is) {
        try {
            return parseXml(is);
        } catch (JAXBException e) {
            return Collections.emptyList();
        }
    }

    private List<News> parseXml(InputStream is) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(Rss.class);
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        Rss rss = (Rss) unmarshaller.unmarshal(is);

        return rss
                .channel
                .content
                .stream()
                .map(net.eutkin.domain.jaxb.News::toPojo)
                .collect(Collectors.toList());
    }
}
