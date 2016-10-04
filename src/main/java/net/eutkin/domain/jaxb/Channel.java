package net.eutkin.domain.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Создан 04.10.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@XmlRootElement(name = "channel")
public class Channel {

    @XmlElement(name = "item")
    public List<News> content;
}
