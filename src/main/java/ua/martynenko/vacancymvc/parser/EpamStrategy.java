package ua.martynenko.vacancymvc.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.martynenko.vacancymvc.model.Company;
import ua.martynenko.vacancymvc.model.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nikola on 01.06.2016.
 */
public class EpamStrategy implements Strategy {

    ArrayList<Vacancy> listOfVacancies = new ArrayList<Vacancy>();
    Document doc;
    Document docs;
    int count = 0;
    int count1 = 0;
    @Override
    public List<Vacancy> getVacancies() {
        try {
            doc = Jsoup.connect("https://jobs.dou.ua/companies").timeout(2000).get();
            Element elements = doc.select(".more-btn").first();
            System.out.println(elements);
//            Elements listCompany = doc.select(".h2").select("a[href]:first-child");
//            for (int i = 0; i < listCompany.size(); i++){
//                count++;
//                System.out.println(count + ". " + listCompany.get(i).text());
//
//            }





//           for (Element element : listCompany) {
//                docs = Jsoup.connect(element.attr("href").toString()).timeout(2000).get();
//                Elements listVacancy = docs.select(".vt").select("a[href]");
//                //Look in setResponsibilities
//                String linkCompany = docs.getElementsByClass("site").select("a").attr("href");
//
//                for (Element vacancyLink : listVacancy) {
//                    Document vacancy = Jsoup.connect(vacancyLink.attr("href")).timeout(2000).get();
//                    String title = vacancy.getElementsByClass("g-h2").html();
//                    String location = vacancy.getElementsByClass("place").text();
//                    String description = vacancy.select(".vacancy-section, .project").html();
//
//                    Vacancy vacancyObject = new Vacancy();
//                    vacancyObject.setLink(vacancyLink.attr("href"));
//                    vacancyObject.setTitle(title);
//                    vacancyObject.setProject_description(description);
//                    vacancyObject.setDate(new Date());
//                    vacancyObject.setLocation(location);
//                    vacancyObject.setType(title);
//                    //linkCompany.
//                    vacancyObject.setResponsibilities(linkCompany);
//                    listOfVacancies.add(vacancyObject);
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfVacancies;
    }
}