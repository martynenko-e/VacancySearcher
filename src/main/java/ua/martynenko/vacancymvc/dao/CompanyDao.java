package ua.martynenko.vacancymvc.dao;

import ua.martynenko.vacancymvc.model.Company;

import java.util.List;

public interface CompanyDao {

    Company findById(Integer id);

    void saveCompany(Company company);

    void deleteCompanyByUrl(String url);

    List<Company> findAllCompanies();

    List<Company> findAllShowCompanies();

    Company findCompanyByUrl(String url);
}
