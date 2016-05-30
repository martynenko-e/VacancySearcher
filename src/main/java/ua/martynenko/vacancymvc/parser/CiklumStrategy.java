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


public class CiklumStrategy implements Strategy {

    @Override
    public List<Vacancy> getVacancies() {
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2";
        int timiout = 10 * 1000;
        Document doc = null;
        ArrayList<Vacancy> listOfVacancies = new ArrayList<>();
        try {
            doc = Jsoup.connect("https://jobs.ciklum.com/?s=&location=&location=Kyiv&job_cat=&ptype=job_listing&latitude=&longitude=&full_address=&north_east_lng=&south_west_lng=&north_east_lat=&south_west_lat=").timeout(timiout).userAgent(userAgent).get();

            Elements elements = doc.select("ol.jobs.searchapply .row .position-name a");

            for (Element element : elements) {

                Document vacancySite = Jsoup.connect(element.attr("href")).timeout(timiout).userAgent(userAgent).get();

                String title = vacancySite.select(".section_header h1").first().text();
                String description = "";
                try {
                    description = vacancySite.select("#project-client-details .panel-body").first().html();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                String type = "";
                try {
                    type = vacancySite.select("p.job-cat span").first().text();
                    type = type.substring(0, type.length() - 10);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
//                String location = vacancySite.select(".section_header h2 strong").first().text();
                String link = element.attr("href");

                Vacancy vacancyObject = new Vacancy();
                vacancyObject.setLink(link);
                vacancyObject.setTitle(title);
                if (!type.equals("")) vacancyObject.setType(type);
                vacancyObject.setLocation("Kiev");
                if (!description.equals("")) vacancyObject.setProject_description(description);
                vacancyObject.setDate(new Date());
                listOfVacancies.add(vacancyObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfVacancies;
    }
}
