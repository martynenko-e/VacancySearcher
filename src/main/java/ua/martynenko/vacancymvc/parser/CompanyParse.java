package ua.martynenko.vacancymvc.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.martynenko.vacancymvc.model.Company;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martynenko on 19.05.2016.
 */
public class CompanyParse {
    public List<Company> getCompanies() {
        Document doc = null;
        ArrayList<Company> listOfCompanies = new ArrayList<>();
        String parseUrl = "https://jobs.dou.ua/ratings/";
        try {
            doc = Jsoup.connect(parseUrl).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").timeout(5000).get();
            Elements companyElements = doc.select("#ratingsTableId tr");
            for (Element element : companyElements) {
                if (!element.select(".company-name").text().isEmpty()) {
                    String companyName = element.select(".company-name").text();
                    String tempLink = element.select(".company-name a").attr("href");
                    String companyLink = tempLink.substring(0, tempLink.length() - 5);
                    doc = Jsoup.connect(companyLink).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").timeout(5000).get();
                    Element companyElement = doc.select(".company-info").first();
                    String companyLogo = companyElement.select(".logo").attr("src");
                    String companyOffices = companyElement.select(".offices").text();
                    String companySite = companyElement.select(".site a").attr("href");
                    String companyDescription = doc.select(".b-company-about .b-typo").first().text();
                    Company company = new Company();
                    company.setName(companyName);
                    company.setDescription(companyDescription);
                    company.setUrl(companySite);
                    company.setOffices(companyOffices);
                    String logoName = companyName.replaceAll("[^A-Za-z0-9]", "-").toLowerCase();
                    try {
                        getImages(companyLogo, logoName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    company.setLogo(String.format("/static/images/%s.png", logoName));
                    listOfCompanies.add(company);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfCompanies;
    }

    private static void getImages(String src, String name) throws IOException {
        String folder = "C:\\_project\\git\\java\\VacancySearcher\\src\\main\\webapp\\static\\images\\";
        //Open a URL Stream
        URL url = new URL(src);
        InputStream in = url.openStream();
        OutputStream out = new BufferedOutputStream(new FileOutputStream(folder + name + ".png"));
        for (int b; (b = in.read()) != -1; ) {
            out.write(b);
        }
        out.close();
        in.close();
    }
}
