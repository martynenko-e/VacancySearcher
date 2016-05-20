package ua.martynenko.vacancymvc.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.martynenko.vacancymvc.model.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 05.01.2016.
 */
public class LuxoftStrategy implements Strategy {

    @Override
    public List<Vacancy> getVacancies() {
        ArrayList<Vacancy> listOfVacancies = new ArrayList<Vacancy>();
        Document doc;
        try {
            doc = Jsoup.connect("http://career.luxoft.com/job-opportunities/?countryID=780&set_filter=Y&PAGEN_1=1").timeout(1000 * 5).get();

            int pagination = doc.select(".pagination li").size() - 2;
            for (int j = 1; j <= pagination; j++) {

                String html = String.format("http://career.luxoft.com/job-opportunities/?countryID=780&set_filter=Y&PAGEN_1=%d", j);
                System.out.println(html);
                doc = Jsoup.connect(html).get();
                Elements jobElements = doc.select(".table-jobs tr"); // мы должны искать ссылку на вакансии(содержащее вакансии)

                for (Element job : jobElements) {
                    String title = job.select("[data-offers=title]").text();
                    String link = String.format("http://career.luxoft.com%s", job.select("[data-offers=title]").attr("href"));
                    String keyWords = job.select("[itemprop=occupationalCategory] span").text();
                    String address = job.select("[itemprop=address] span").text();


                    if (!title.isEmpty() && !link.isEmpty() && !keyWords.isEmpty() && !address.isEmpty()) {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setLink(link);
                        vacancy.setTitle(title);
                        vacancy.setSkills_required(keyWords);
                        listOfVacancies.add(vacancy);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfVacancies;
    }
}
