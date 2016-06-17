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

public class IntroproStrategy implements Strategy{

    ArrayList<Vacancy> listOfVacancies = new ArrayList<Vacancy>();
    Document doc;

    @Override
    public List<Vacancy> getVacancies() {
        try{
            doc = Jsoup.connect("https://www.intropro.com/component/tags/tag/2-kiev-ukraine").get();
            Elements elements = doc.select(".shouldBeCorrected");
            for(Element element : elements){

                String vacancyLink = "https://www.intropro.com/careers/we-are-hiring" + element.attr("href");
                Document vacancySite = Jsoup.connect(vacancyLink).get();
                String description = vacancySite.select(".item-page [itemprop=articleBody]").text();
                String responsibilities = vacancySite.select("[itemprop=articleBody] ul:first-of-type").html();
                String vacancyTitle = element.text();
                String skills = vacancyTitle.substring(0, vacancyTitle.indexOf("-"));


                Vacancy vacancyObject = new Vacancy();
                vacancyObject.setLink(vacancyLink);
                vacancyObject.setTitle(vacancyTitle);
                vacancyObject.setProject_description(description);
                vacancyObject.setDate(new Date());
                vacancyObject.setLocation("Kiev");
                vacancyObject.setResponsibilities(responsibilities);
                vacancyObject.setType(skills);
                listOfVacancies.add(vacancyObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return listOfVacancies;
    }
}