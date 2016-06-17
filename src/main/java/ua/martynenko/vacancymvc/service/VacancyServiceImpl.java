package ua.martynenko.vacancymvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.martynenko.vacancymvc.dao.VacancyDao;
import ua.martynenko.vacancymvc.model.Vacancy;

@Service("vacancyService")
@Transactional
public class VacancyServiceImpl implements VacancyService {

    @Autowired
    private VacancyDao dao;

    public Vacancy findById(int id) {
        return dao.findById(id);
    }

    public void saveVacancy(Vacancy vacancy) {
        dao.saveVacancy(vacancy);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public void updateVacancy(Vacancy vacancy) {
        Vacancy entity = dao.findById(vacancy.getId());
        if(entity!=null){
            entity.setTitle(vacancy.getTitle());
            entity.setDate(vacancy.getDate());
            entity.setLink(vacancy.getLink());
            entity.setLocation(vacancy.getLocation());
            entity.setResponsibilities(vacancy.getResponsibilities());
            entity.setSkills_required(vacancy.getSkills_required());
            entity.setProject_description(vacancy.getProject_description());
            entity.setType(vacancy.getType());
            entity.setCompany(vacancy.getCompany());
        }
    }

    public void deleteVacancyByLink(String link) {
        dao.deleteVacancyByLink(link);
    }

    public List<Vacancy> findAllVacancies() {
        return dao.findAllVacancies();
    }

    public List<Vacancy> findVacanciesByCompany(int company_id) {
        return dao.findVacanciesByCompany(company_id);
    }

    public Vacancy findVacancyByLink(String link) {
        return dao.findVacancyByLink(link);
    }

    public Vacancy findVacancyByLink(String link, String title) {
        return dao.findVacancyByLink(link, title);
    }

    public boolean isVacancyLinkUnique(Integer id, String link) {
        Vacancy vacancy = findVacancyByLink(link);
        return ( vacancy == null || ((id != null) && (vacancy.getId() == id)));
    }

}