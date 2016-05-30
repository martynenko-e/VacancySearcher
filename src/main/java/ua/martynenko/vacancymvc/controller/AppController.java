package ua.martynenko.vacancymvc.controller;

/**
 * Created by Martynenko on 22.04.2016.
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.*;

import ua.martynenko.vacancymvc.model.Company;
import ua.martynenko.vacancymvc.model.Vacancy;
import ua.martynenko.vacancymvc.parser.CiklumStrategy;
import ua.martynenko.vacancymvc.parser.CompanyParse;
import ua.martynenko.vacancymvc.parser.LuxoftStrategy;
import ua.martynenko.vacancymvc.service.CompanyService;
import ua.martynenko.vacancymvc.service.VacancyService;

@Controller
@RequestMapping("/")
@SessionAttributes("companies")
public class AppController {

    @Autowired
    VacancyService vacancyService;
    @Autowired
    CompanyService serviceCompany;
    @Autowired
    MessageSource messageSource;


    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        List<Company> companies = serviceCompany.findAllShowCompanies();
        model.addAttribute("companies", companies);
        return "index";
    }

    @RequestMapping(value = {"/vacancy/", "/vacancy/list/"}, method = RequestMethod.GET)
    public String listVacancies(ModelMap model) {

        List<Vacancy> vacancies = vacancyService.findAllVacancies();
        model.addAttribute("vacancies", vacancies);
        return "allvacancies";
    }

    /*
     * This method will delete an vacancy by it's link value.
     */
    @RequestMapping(value = {"/vacancy/delete-{id}-vacancy/"}, method = RequestMethod.GET)
    public String deleteVacancy(@PathVariable String id) {
        String link = vacancyService.findById(Integer.valueOf(id)).getLink();
        vacancyService.deleteVacancyByLink(link);
        return "redirect:/vacancy/list/";
    }


    @RequestMapping(value = {"/company/", "/company/list/"}, method = RequestMethod.GET)
    public String listCompanies(ModelMap model) {

        List<Company> companies = serviceCompany.findAllCompanies();
        model.addAttribute("companies", companies);
        return "allcompanies";
    }

    @RequestMapping(value = {"/company/{id}-about/"}, method = RequestMethod.GET)
    public String aboutCompany(ModelMap model, @PathVariable Integer id) {
        Company company = serviceCompany.findById(id);
        List<Vacancy> vacancies = vacancyService.findVacanciesByCompany(id);
        model.addAttribute("company", company);
        model.addAttribute("vacancies", vacancies);
        return "companyinfo";
    }

    /*
     * This method will delete an company by it's Url value.
     */
    @RequestMapping(value = {"/company/delete-{url}-company/"}, method = RequestMethod.GET)
    public String deleteCompany(@PathVariable String url) {
        serviceCompany.deleteCompanyByUrl(url);
        return "redirect:/company/list/";
    }

    /*
    * This method initialize all companies for any controller
    */
    @ModelAttribute("companies")
    public List<Company> initializeProfiles() {
        return serviceCompany.findAllCompanies();
    }


    @RequestMapping(value = {"/parse/{name}-company/"}, method = RequestMethod.GET)
    public String parseCompany(@PathVariable String name) {
        if (name.equals("add")) {
            for (Company company: new CompanyParse().getCompanies()) {
                if (serviceCompany.findCompanyByUrl(company.getUrl()) == null) {
                    serviceCompany.saveCompany(company);
                }
            }
            return "redirect:/company/list/";
        }
        if (name.equals("ciklum")) {
            for (Vacancy vacancy: new CiklumStrategy().getVacancies()) {
                if (vacancyService.findVacancyByLink(vacancy.getLink()) == null){
                    vacancy.setCompany(serviceCompany.findCompanyByUrl("http://www.ciklum.com/"));
                    vacancyService.saveVacancy(vacancy);
                }
            }
        }

        if (name.equals("luxoft")) {
            for (Vacancy vacancy: new LuxoftStrategy().getVacancies()) {
                if (vacancyService.findVacancyByLink(vacancy.getLink()) == null){
                    vacancy.setCompany(serviceCompany.findCompanyByUrl("http://www.luxoft.com/"));
                    vacancy.setActive(true);
                    vacancyService.saveVacancy(vacancy);
                } else {
                    vacancy.setActive(false);
                    vacancyService.updateVacancy(vacancy);
                }
            }
        }

        return "redirect:/vacancy/list/";
    }

}
