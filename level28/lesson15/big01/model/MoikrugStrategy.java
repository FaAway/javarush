package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FarAway on 07.03.2016.
 */
public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    public List<Vacancy> getVacancies(String searchString){

        List<Vacancy> result = new ArrayList<>();
        int page = 1;
        try {
            for (Document html = getDocument(searchString, page); html != null; html = getDocument(searchString, ++page)) {
            //Document html = getDocumentFromFile(searchString, page);
                Elements elements = html.select("[id^=job_]");
                if (elements.size() == 0) break;

                for (Element element : elements) {

                    Vacancy vacancy = new Vacancy();

                    vacancy.setSiteName("https://moikrug.ru/");

                    Elements el;

                    el = element.getElementsByClass("title").first().getElementsByTag("a");
                    vacancy.setTitle(el.first().text());
                    vacancy.setUrl(vacancy.getSiteName() + el.attr("href").substring(1));

                    el = element.getElementsByClass("salary");
                    vacancy.setSalary(el.size() != 0 ? el.first().getElementsByTag("div").first().text() : "");

                    el = element.getElementsByClass("location");
                    vacancy.setCity(el.size() != 0 ? el.first().getElementsByTag("a").first().text() : "");

                    el = element.getElementsByClass("company_name").first().getElementsByTag("a");
                    vacancy.setCompanyName(el.first().text());

                    result.add(vacancy);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document html;
        html = Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (jsoup)")
                .referrer("https://hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2")
                .get();

        return html;
    }

    protected Document getDocumentFromFile(String searchString, int page) throws IOException {
        Document html;
        html = Jsoup.parse(new File("moikrug.html"), "UTF-8");

        return html;
    }
}
