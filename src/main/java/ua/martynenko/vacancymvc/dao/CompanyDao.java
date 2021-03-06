package ua.martynenko.vacancymvc.dao;

import ua.martynenko.vacancymvc.model.Company;

import java.util.List;

/**
 * Created by Martynenko on 23.04.2016.
 */
public interface CompanyDao {

    Company findById(Integer id);

    void saveCompany(Company company);

    void deleteCompanyByUrl(String url);

    List<Company> findAllCompanies();

    List<Company> findAllShowCompanies();

    Company findCompanyByUrl(String url);
}
