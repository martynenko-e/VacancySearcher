package ua.martynenko.vacancymvc.service;

import java.util.List;

import ua.martynenko.vacancymvc.model.Company;

public interface CompanyService {

    Company findById(Integer id);

    void saveCompany(Company company);

    void updateCompany(Company company);

    void deleteCompanyByUrl(String ssn);

    List<Company> findAllCompanies();

    List<Company> findAllShowCompanies();

    Company findCompanyByUrl(String url);

    boolean isCompanyUrlUnique(Integer id, String url);


}
