package ua.martynenko.vacancymvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ua.martynenko.vacancymvc.model.Vacancy;

@Repository("vacancyDao")
public class VacancyDaoImpl extends AbstractDao<Integer, Vacancy> implements VacancyDao {

    public Vacancy findById(int id) {
        return getByKey(id);
    }

    public void saveVacancy(Vacancy vacancy) {
        persist(vacancy);
    }

    public void deleteVacancyByLink(String link) {
        Query query = getSession().createSQLQuery("delete from Vacancy where link = :link");
        query.setString("link", link);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<Vacancy> findAllVacancies() {
        Criteria criteria = createEntityCriteria();
        return (List<Vacancy>) criteria.list();
    }

    public Vacancy findVacancyByLink(String link) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("link", link));
        return (Vacancy) criteria.uniqueResult();
    }
        // specially for AbSoft
    public Vacancy findVacancyByLink(String link, String title) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("link", link));
        criteria.add(Restrictions.eq("title", title));
        return (Vacancy) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Vacancy> findVacanciesByCompany(int company_id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("company.id", company_id));
        return (List<Vacancy>) criteria.list();
    }
}