package net.eutkin.domain.jaxb;

import lombok.Getter;
import lombok.Setter;
import net.eutkin.domain.jaxb.adapter.LocalDateTimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

/**
 * Создан 04.10.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Getter @Setter
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class News {

    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "link")
    private String link;
    @XmlElement(name = "pubDate")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime pubDate;

    public net.eutkin.domain.News toPojo() {
        return new net.eutkin.domain.News()
                .title(title)
                .link(link)
                .pubDate(pubDate);
    }

    @Override
    public String toString() {
        return "News{" + "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", pubDate=" + pubDate +
                "}\r\n";
    }
}
