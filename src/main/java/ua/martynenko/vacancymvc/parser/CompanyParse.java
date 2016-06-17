package ua.martynenko.vacancymvc.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import ua.martynenko.vacancymvc.model.Company;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CompanyParse {
    public List<Company> getCompanies() {
        Document doc;
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
        int timeout = 10 * 1000;
        ArrayList<Company> listOfCompanies = new ArrayList<>();
        String parseUrl = "https://jobs.dou.ua/ratings/";
        try {
            doc = Jsoup.connect(parseUrl).userAgent(userAgent).timeout(timeout).get();
            Elements companyElements = doc.select("#ratingsTableId tr");
            for (Element element : companyElements) {
                if (!element.select(".company-name").text().isEmpty()) {
                    String companyName = element.select(".company-name").text();
                    String tempLink = element.select(".company-name a").attr("href");
                    String companyLink = tempLink.substring(0, tempLink.length() - 5);
                    doc = Jsoup.connect(companyLink).userAgent(userAgent).timeout(timeout).get();
                    Element companyElement = doc.select(".company-info").first();
                    String companyLogo = companyElement.select(".logo").attr("src");
                    String companyOffices = companyElement.select(".offices").text();
                    String companySite = companyElement.select(".site a").attr("href");
                    Whitelist whitelist = Whitelist.basic();
                    String companyDescription = Jsoup.clean(doc.select(".b-company-about .b-typo").first().html(), whitelist);
                    String alias = companyName.replaceAll("[^A-Za-z0-9]", "-").toLowerCase();
                    Company company = new Company();
                    company.setName(companyName);
                    company.setAlias(alias);
                    company.setDescription(companyDescription);
                    company.setUrl(companySite);
                    company.setOffices(companyOffices);
                    company.setShowOnMain(false);

                    try {
                        getImages(companyLogo, alias);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    company.setLogo(String.format("/static/images/%s.png", alias));
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
