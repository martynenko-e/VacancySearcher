package ua.martynenko.vacancymvc.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.martynenko.vacancymvc.model.Company;

import java.util.List;

@Repository("companyDao")
public class CompanyDaoImpl extends AbstractDao<Integer, Company> implements CompanyDao {
    public Company findById(Integer id) {
        return getByKey(id);
    }

    public void saveCompany(Company company) {
        persist(company);
    }

    public void deleteCompanyByUrl(String url) {
        Query query = getSession().createSQLQuery("delete from Company where url = :url");
        query.setString("url", url);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<Company> findAllCompanies() {
        Criteria criteria = createEntityCriteria();
        return (List<Company>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<Company> findAllShowCompanies() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("showOnMain", Boolean.TRUE));
        return (List<Company>) criteria.list();
    }

    public Company findCompanyByUrl(String url) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("url", url));
        return (Company) criteria.uniqueResult();
    }
}
