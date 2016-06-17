package ua.martynenko.vacancymvc.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.martynenko.vacancymvc.model.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LuxoftStrategy implements Strategy {
    @Override
    public List<Vacancy> getVacancies() {
        int threadSleap = 500;
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2";
        int timiout = 10 * 1000;
        ArrayList<Vacancy> listOfVacancies = new ArrayList<Vacancy>();
        Document doc;
        try {
            doc = Jsoup.connect("http://career.luxoft.com/job-opportunities/?countryID=780&set_filter=Y&PAGEN_1=1").timeout(timiout).userAgent(userAgent).get();
            int pagination = doc.select(".pagination li").size() - 2;
            for (int j = 1; j <= pagination; j++) {
                String pageWithVacancies = String.format("http://career.luxoft.com/job-opportunities/?countryID=780&set_filter=Y&PAGEN_1=%d", j);
                doc = Jsoup.connect(pageWithVacancies).timeout(timiout).userAgent(userAgent).get();
                Elements jobElements = doc.select(".table-jobs tr");
                for (Element job : jobElements) {
                    try {
                        Thread.sleep(threadSleap);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String title = job.select("[data-offers=title]").text();
                    String link = String.format("http://career.luxoft.com%s", job.select("[data-offers=title]").attr("href"));
                    String type = job.select("[itemprop=occupationalCategory] span").text();
                    String location = job.select("[itemprop=address] span").text();

                    doc = Jsoup.connect(link).timeout(timiout).userAgent(userAgent).get();
                    String description = "";
                    String responsibilities = "";
                    String skillsRequired = "";

                    try {
                        description = doc.select(".p-style").first().html();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    try {
                        responsibilities = doc.select("[itemprop=description]").html();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    try {
                        skillsRequired = doc.select("[itemprop=responsibilities]").outerHtml();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    if (!title.isEmpty() && !link.isEmpty()) {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setLink(link);
                        vacancy.setTitle(title);

                        if (!type.isEmpty())vacancy.setType(type);
                        if (!location.isEmpty())vacancy.setLocation(location);
                        if (!description.isEmpty()) vacancy.setProject_description(description);
                        if (!responsibilities.isEmpty()) vacancy.setResponsibilities(responsibilities);
                        if (!skillsRequired.isEmpty()) vacancy.setSkills_required(skillsRequired);

                        vacancy.setDate(new Date());
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
