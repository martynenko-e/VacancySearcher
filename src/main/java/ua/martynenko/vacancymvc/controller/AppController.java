package ua.martynenko.vacancymvc.controller;

/**
 * Created by Martynenko on 22.04.2016.
 */
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import ua.martynenko.vacancymvc.model.Company;
import ua.martynenko.vacancymvc.model.Vacancy;
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

    /*
     * This method will list all existing employees.
     */
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("message", "Hello on main page");
        return "index";
    }

    @RequestMapping(value = {"/vacancy/", "/vacancy/list/"}, method = RequestMethod.GET)
    public String listVacancies(ModelMap model) {

        List<Vacancy> vacancies = vacancyService.findAllVacancies();
        model.addAttribute("vacancies", vacancies);
        return "allvacancies";
    }

    /*
     * This method will provide the medium to add a new employee.
     */
    @RequestMapping(value = {"/vacancy/new/"}, method = RequestMethod.GET)
    public String newVacancy(ModelMap model) {
        Vacancy vacancy = new Vacancy();
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("edit", false);
        return "addvacancy";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * saving vacancy in database. It also validates the user input
     */
    @RequestMapping(value = {"/vacancy/new/"}, method = RequestMethod.POST)
    public String saveVacancy(Vacancy vacancy, BindingResult result,
                              ModelMap model) {

        if (result.hasErrors()) {
            return "addvacancy";
        }
        /*
         * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation
         * and applying it on field [ssn] of Model class [Vacancy].
         *
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         *
         */
        if (!vacancyService.isVacancyLinkUnique(vacancy.getId(), vacancy.getLink())) {
            FieldError linkError = new FieldError("vacancy", "link", messageSource.getMessage("non.unique.link", new String[]{vacancy.getLink()}, Locale.getDefault()));
            result.addError(linkError);
            return "addvacancy";
        }
        vacancyService.saveVacancy(vacancy);
        model.addAttribute("success", "Vacancy " + vacancy.getTitle() + " registered successfully");
        return "success";
    }


    /*
     * This method will provide the medium to update an existing employee.
     */
    @RequestMapping(value = {"/vacancy/edit-{id}-vacancy/"}, method = RequestMethod.GET)
    public String editVacancy(@PathVariable String id, ModelMap model) {
        Vacancy vacancy = vacancyService.findById(Integer.valueOf(id));
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("edit", true);
        return "addvacancy";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * updating vacancy in database. It also validates the user input
     */
    @RequestMapping(value = {"/vacancy/edit-{id}-vacancy/"}, method = RequestMethod.POST)
    public String updateVacancy(@Valid Vacancy vacancy, BindingResult result,
                                ModelMap model, @PathVariable String id) {

        if (result.hasErrors()) {
            return "addvacancy";
        }

        if (!vacancyService.isVacancyLinkUnique(vacancy.getId(), vacancy.getLink())) {
            FieldError linkError = new FieldError("vacancy", "link", messageSource.getMessage("non.unique.link", new String[]{vacancy.getLink()}, Locale.getDefault()));
            result.addError(linkError);
            return "addvacancy";
        }

        vacancyService.updateVacancy(vacancy);

        model.addAttribute("success", "Vacancy " + vacancy.getTitle() + " updated successfully");
        return "success";
    }


    /*
     * This method will delete an employee by it's SSN value.
     */
    @RequestMapping(value = {"/vacancy/delete-{id}-vacancy/"}, method = RequestMethod.GET)
    public String deleteVacancy(@PathVariable String id) {
        String link = vacancyService.findById(Integer.valueOf(id)).getLink();
        vacancyService.deleteVacancyByLink(link);
        return "redirect:/vacancy/list";
    }


    @RequestMapping(value = {"/company/", "/company/list/"}, method = RequestMethod.GET)
    public String listCompanies(ModelMap model) {

        List<Company> companies = serviceCompany.findAllCompanies();
        model.addAttribute("companies", companies);
        return "allcompanies";
    }

    /*
     * This method will provide the medium to add a new company.
     */
    @RequestMapping(value = {"/company/new/"}, method = RequestMethod.GET)
    public String newCompany(ModelMap model) {
        Company company = new Company();
        model.addAttribute("company", company);
        model.addAttribute("edit", false);
        return "addcompany";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * saving company in database. It also validates the user input
     */
    @RequestMapping(value = {"/company/new/"}, method = RequestMethod.POST)
    public String saveCompany(@Valid Company company, BindingResult result,
                              ModelMap model) {

        if (result.hasErrors()) {
            return "addcompany";
        }

        /*
         * Preferred way to achieve uniqueness of field [url] should be implementing custom @Unique annotation
         * and applying it on field [ssn] of Model class [Company].
         *
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         *
         */
        if (!serviceCompany.isCompanyUrlUnique(company.getId(), company.getUrl())) {
            return "addcompany";
        }

        serviceCompany.saveCompany(company);

        model.addAttribute("success", "Company " + company.getName() + " company added successfully");
        return "success";
    }


    /*
     * This method will provide the medium to update an existing company.
     */
    @RequestMapping(value = {"/company/edit-{url}-company/"}, method = RequestMethod.GET)
    public String editCompany(@PathVariable String url, ModelMap model) {
        Company company = serviceCompany.findCompanyByUrl(url);
        model.addAttribute("company", company);
        model.addAttribute("edit", true);
        return "addcompany";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * updating company in database. It also validates the user input
     */
    @RequestMapping(value = {"/company/edit-{url}-company/"}, method = RequestMethod.POST)
    public String updateCompany(@Valid Company company, BindingResult result,
                                ModelMap model, @PathVariable String url) {

        if (result.hasErrors()) {
            return "addcompany";
        }

        if (!serviceCompany.isCompanyUrlUnique(company.getId(), company.getUrl())) {
            FieldError ssnError = new FieldError("company", "url", messageSource.getMessage("non.unique.ssn", new String[]{company.getUrl()}, Locale.getDefault()));
            result.addError(ssnError);
            return "addcompany";
        }

        serviceCompany.updateCompany(company);

        model.addAttribute("success", "Company " + company.getName() + " updated successfully");
        return "success";
    }


    /*
     * This method will delete an company by it's Url value.
     */
    @RequestMapping(value = {"/company/delete-{url}-company/"}, method = RequestMethod.GET)
    public String deleteCompany(@PathVariable String url) {
        serviceCompany.deleteCompanyByUrl(url);
        return "redirect:/company/list";
    }

    @ModelAttribute("companies")
    public List<Company> initializeProfiles() {
        return serviceCompany.findAllCompanies();
    }

}
