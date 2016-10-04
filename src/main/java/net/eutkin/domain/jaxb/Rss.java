package net.eutkin.domain.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Создан 04.10.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@XmlRootElement(name = "rss")
public class Rss {

    @XmlElement(name = "channel")
    public Channel channel;
}
