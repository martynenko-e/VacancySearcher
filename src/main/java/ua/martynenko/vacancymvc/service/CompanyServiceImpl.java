package ua.martynenko.vacancymvc.service;

/**
 * Created by Martynenko on 23.04.2016.
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.martynenko.vacancymvc.dao.CompanyDao;
import ua.martynenko.vacancymvc.model.Company;

@Service("companyService")
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao dao;

    public Company findById(int id) {
        return dao.findById(id);
    }

    public void saveCompany(Company company) {
        dao.saveCompany(company);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public void updateCompany(Company company) {
        Company entity = dao.findById(company.getId());
        if(entity!=null){
            entity.setName(company.getName());
            entity.setCity(company.getCity());
            entity.setCountry(company.getCountry());
            entity.setCity(company.getCity());
            entity.setDescription(company.getDescription());
            entity.setUrl(company.getUrl());
        }
    }

    public void deleteCompanyByUrl(String url) {
        dao.deleteCompanyByUrl(url);
    }

    public List<Company> findAllCompanies() {
        return dao.findAllCompanies();
    }

    public Company findCompanyByUrl(String url) {
        return dao.findCompanyByUrl(url);
    }

    public boolean isCompanyUrlUnique(Integer id, String url) {
        Company company = findCompanyByUrl(url);
        return ( company == null || ((id != null) && (company.getId() == id)));
    }

}