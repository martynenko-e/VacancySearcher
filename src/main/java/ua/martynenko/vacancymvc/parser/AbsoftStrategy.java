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

public class AbsoftStrategy implements Strategy {

    ArrayList<Vacancy> listOfVacancies = new ArrayList<Vacancy>();
    Document doc;

    @Override
    public List<Vacancy> getVacancies() {
        try {
            doc = Jsoup.connect("http://ab-soft.net/kandidat/otkrytye-vakansii").get();
            Elements elements = doc.select("[class=item col-lg-12 col-md-12 col-sm-12 col-xs-12]");
//            System.out.println(elements );
            for (Element element : elements) {
                String link = "http://ab-soft.net/kandidat/otkrytye-vakansii";
                String vacancyTitle = element.select(".clearfix head, h2").text();
                String description = element.select(".post_content p, ul").html();




                Vacancy vacancyObject = new Vacancy();
                vacancyObject.setLink(link);
                vacancyObject.setTitle(vacancyTitle);
                vacancyObject.setProject_description(description);
                vacancyObject.setDate(new Date());
                vacancyObject.setLocation("Odessa");
                vacancyObject.setType(vacancyTitle);
                listOfVacancies.add(vacancyObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return listOfVacancies;
    }
}

