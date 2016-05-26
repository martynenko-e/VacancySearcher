package ua.martynenko.vacancymvc.service;

/**
 * Created by Martynenko on 23.04.2016.
 */
import java.util.List;

import ua.martynenko.vacancymvc.model.Company;

public interface CompanyService {

    Company findById(int id);

    void saveCompany(Company company);

    void updateCompany(Company company);

    void deleteCompanyByUrl(String ssn);

    List<Company> findAllCompanies();

    List<Company> findAllShowCompanies();

    Company findCompanyByUrl(String url);

    boolean isCompanyUrlUnique(Integer id, String url);

}
