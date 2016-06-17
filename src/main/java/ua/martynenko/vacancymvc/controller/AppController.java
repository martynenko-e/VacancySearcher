package ua.martynenko.vacancymvc.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import ua.martynenko.vacancymvc.model.Company;
import ua.martynenko.vacancymvc.model.Vacancy;
import ua.martynenko.vacancymvc.parser.AstoundStrategy;
import ua.martynenko.vacancymvc.parser.CompanyParse;
import ua.martynenko.vacancymvc.parser.IntroproStrategy;
import ua.martynenko.vacancymvc.parser.LuxoftStrategy;
import ua.martynenko.vacancymvc.service.CompanyService;
import ua.martynenko.vacancymvc.service.VacancyService;

@Controller
@RequestMapping("/")
@SessionAttributes("companies")
public class AppController {
    private static final Logger log = LoggerFactory.getLogger(AppController.class);
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
        if (name.equals("astound")) {
            for (Vacancy vacancy: new AstoundStrategy().getVacancies()) {
                if (vacancyService.findVacancyByLink(vacancy.getLink()) == null){
                    vacancy.setCompany(serviceCompany.findCompanyByUrl("http://www.astound.com.ua/"));
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

        if(name.equals("intropro-llc")) {
            List<Vacancy> vacancyListIntropro = new IntroproStrategy().getVacancies();
            for (int i = 0; i < vacancyListIntropro.size(); i++) {
                if (vacancyService.findVacancyByLink(vacancyListIntropro.get(i).getLink()) == null) {
                    vacancyListIntropro.get(i).setCompany(serviceCompany.findCompanyByUrl("http://www.intropro.com/"));
                    vacancyListIntropro.get(i).setActive(true);
                    vacancyService.saveVacancy(vacancyListIntropro.get(i));
                }else {
                    vacancyListIntropro.get(i).setActive(false);
                }
            }
        }

        if(name.equals("ab-soft")) {
            List<Vacancy> vacancyListAbSoft = new IntroproStrategy().getVacancies();
            for (int i = 0; i < vacancyListAbSoft.size(); i++) {
                if (vacancyService.findVacancyByLink(vacancyListAbSoft.get(i).getLink(), vacancyListAbSoft.get(i).getTitle()) == null) {
                    vacancyListAbSoft.get(i).setCompany(serviceCompany.findCompanyByUrl("http://ab-soft.net/"));
                    vacancyListAbSoft.get(i).setActive(true);
                    vacancyService.saveVacancy(vacancyListAbSoft.get(i));
                }else {
                    vacancyListAbSoft.get(i).setActive(false);
                }
            }
        }



        return "redirect:/vacancy/list/";
    }
    /*
    This method will refresh allVacancy, adding new
     */
//    @RequestMapping(value = {"/parse/{name}-company/"}, method = RequestMethod.GET)
//    public String refresh(@PathVariable String name) {
//        if(name.equals("intropro-llc")) {
//            List<Vacancy> vacancyListIntropro = new IntroproStrategy().getVacancies();
//            for (int i = 0; i < vacancyListIntropro.size(); i++) {
//                if (vacancyService.findVacancyByLink(vacancyListIntropro.get(i).getLink()) == null) {
//                    vacancyListIntropro.get(i).setCompany(serviceCompany.findCompanyByUrl("http://www.intropro.com/"));
//                    vacancyService.saveVacancy(vacancyListIntropro.get(i));
//                }
//            }
//        }
//        List<Vacancy> vacancyListAstound = new AstoundStrategy().getVacancies();
//        for (int i = 0; i < vacancyListAstound.size(); i++) {
//            if (!vacancyService.isVacancyLinkUnique(vacancyListAstound.get(i).getLink())) {
//                vacancyListAstound.get(i).setCompany(serviceCompany.findCompanyByUrl("http://astoundcommerce.com"));
//                vacancyService.saveVacancy(vacancyListAstound.get(i));
//            }
//        }
//        List<Vacancy> vacancyListLuxoft = new LuxoftStrategy().getVacancies();
//        for (int i = 0; i < vacancyListLuxoft.size(); i++) {
//            if (!vacancyService.isVacancyLinkUnique(vacancyListLuxoft.get(i).getLink())) {
//                vacancyListLuxoft.get(i).setCompany(serviceCompany.findCompanyByUrl("http://www.luxoft.com"));
//                vacancyService.saveVacancy(vacancyListLuxoft.get(i));
//            }
//        }
//
//        List<Vacancy> vacancyListAbsoft = new AbsoftStrategy().getVacancies();
//        for (int i = 0; i < vacancyListAbsoft.size(); i++) {
//            if (!vacancyService.isVacancyLinkUnique(vacancyListAbsoft.get(i).getLink())) {
//                vacancyListAbsoft.get(i).setCompany(serviceCompany.findCompanyByUrl("http://www.Ab-soft.net"));
//                vacancyService.saveVacancy(vacancyListAbsoft.get(i));
//            }
//        }
//        return "redirect:/vacancy/list/";
//    }



}
