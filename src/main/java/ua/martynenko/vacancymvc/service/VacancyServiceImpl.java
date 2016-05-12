package ua.martynenko.vacancymvc.service;

/**
 * Created by Martynenko on 22.04.2016.
 */
import java.sql.Date;
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
            entity.setSalary(vacancy.getSalary());
            entity.setLink(vacancy.getLink());
            entity.setDescription(vacancy.getDescription());
            entity.setKeyWord(vacancy.getKeyWord());
            entity.setSalary(vacancy.getSalary());
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

    public Vacancy findVacancyByLink(String link) {
        return dao.findVacancyByLink(link);
    }

    public boolean isVacancyLinkUnique(Integer id, String link) {
        Vacancy vacancy = findVacancyByLink(link);
        return ( vacancy == null || ((id != null) && (vacancy.getId() == id)));
    }

}