package net.eutkin;

import net.eutkin.domain.News;
import net.eutkin.parser.JAXBParser;
import net.eutkin.parser.SaxParser;
import net.eutkin.parser.StaxParser;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ParserApplication {

    public static void main(String[] args) {
        BeanFactory ctx = SpringApplication.run(ParserApplication.class, args);

        SaxParser saxParser = ctx.getBean(SaxParser.class);
        List<News> news = saxParser.parse(ClassLoader.getSystemResourceAsStream("rss.xml"));
        System.out.println(news);

        StaxParser staxParser = ctx.getBean(StaxParser.class);
        news = staxParser.parse(ClassLoader.getSystemResourceAsStream("rss.xml"));
        System.out.println(news);

        JAXBParser jaxbParser = ctx.getBean(JAXBParser.class);
        news = jaxbParser.parse(ClassLoader.getSystemResourceAsStream("rss.xml"));
        System.out.println(news);

    }
}
