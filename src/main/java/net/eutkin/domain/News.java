package net.eutkin.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Создан 04.10.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Getter @Setter  @Accessors(chain = true, fluent = true)
public class News {

    private String title;
    private String link;
    private LocalDateTime pubDate;

    @Override
    public String toString() {
        return "News{" + "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", pubDate=" + pubDate +
                "}\r\n";
    }
}
