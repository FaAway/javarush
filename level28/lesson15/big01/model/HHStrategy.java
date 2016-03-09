package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FarAway on 07.03.2016.
 */
public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    public List<Vacancy> getVacancies(String searchString){

        List<Vacancy> result = new ArrayList<>();
        int page = 0;
        try {
            for (Document html = getDocument(searchString, page); html != null; html = getDocument(searchString, ++page)) {

                //Elements elements = html.select("[class^=search-result-item search-result-item]");
                Elements elements = html.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (elements.size() == 0) break;

                for (Element element : elements) {

                    Vacancy vacancy = new Vacancy();
                    Elements el;

                    el = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title");
                    vacancy.setTitle(el.size() != 0 ? el.first().text() : "");
                    vacancy.setUrl(el.size() != 0 ? el.attr("href") : "");

                    el = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation");
                    vacancy.setSalary(el.size() != 0 ? el.first().text() : "");

                    el = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address");
                    vacancy.setCity(el.size() != 0 ? el.first().text() : "");

                    el = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer");
                    vacancy.setCompanyName(el.size() != 0 ? el.first().text() : "");

                    //URL url = new URL(URL_FORMAT);
                    //vacancy.setSiteName(el.size() != 0 ? String.format("%s://%s%s", url.getProtocol(), url.getHost(), el.attr("href")) : "");
                    vacancy.setSiteName("http://hh.ua/");

                    result.add(vacancy);
                }
            }
        } catch (Exception e) {}

        return result;
    }

    public List<Vacancy> getVacanciesSelect(String searchString) throws IOException {

        List<Vacancy> result = new ArrayList<>();
        int page = 0;
        try {
            for (Document html = getDocument(searchString, page); html != null; html = getDocument(searchString, ++page)) {

                Elements elements = html.select("[data-qa=vacancy-serp__vacancy]");
                for (Element element : elements) {

                    Vacancy vacancy = new Vacancy();
                    Elements el;

                    el = html.select("[data-qa=vacancy-serp__vacancy-title]");
                    vacancy.setTitle(el.size() != 0 ? el.text() : "");
                    vacancy.setUrl(el.size() != 0 ? el.attr("href") : "");

                    el = html.select("[data-qa=vacancy-serp__vacancy-compensation]");
                    vacancy.setSalary(el.size() != 0 ? el.text() : "");

                    el = html.select("[data-qa=vacancy-serp__vacancy-address]");
                    vacancy.setCity(el.size() != 0 ? el.text() : "");

                    el = html.select("[data-qa=vacancy-serp__vacancy-employer]");
                    vacancy.setCompanyName(el.size() != 0 ? el.text() : "");
                    URL url = new URL(URL_FORMAT);
                    vacancy.setSiteName(el.size() != 0 ? String.format("%s://%s%s", url.getProtocol(), url.getHost(), el.attr("href")) : "");

                    result.add(vacancy);
                }
            }
        } catch (Exception e) {}

        return result;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document html;
        html = Jsoup.connect(String.format(URL_FORMAT, URLEncoder.encode(searchString, "UTF-8"), page))
                .userAgent("Mozilla/5.0 (jsoup)")
                .referrer("https://hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2")
                .timeout(5000)
                .get();

        return html;
    }
}
