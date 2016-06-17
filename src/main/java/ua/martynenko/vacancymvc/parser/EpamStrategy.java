package ua.martynenko.vacancymvc.parser;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.log4j.BasicConfigurator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.util.Assert;
import ua.martynenko.vacancymvc.model.Company;
import ua.martynenko.vacancymvc.model.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EpamStrategy  implements Strategy{

    ArrayList<Vacancy> listOfVacancies = new ArrayList<Vacancy>();
    Document doc;

    @Override
    public List<Vacancy> getVacancies() {
        try {
            BasicConfigurator.configure();
            WebClient webClient = new WebClient();

            webClient.waitForBackgroundJavaScriptStartingBefore(10000);
            HtmlPage page = webClient.getPage("https://jobs.dou.ua/companies");

            String javaScriptCode = "javascript:;";

            ScriptResult result = page.executeJavaScript(javaScriptCode);
            result.getJavaScriptResult();
            System.out.println("result: "+ result);

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