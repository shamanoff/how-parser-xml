package net.eutkin.parser;

import net.eutkin.domain.News;

import java.io.InputStream;
import java.util.List;

/**
 * Создан 04.10.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public interface XmlParser {

    List<News> parse(InputStream is);
}
