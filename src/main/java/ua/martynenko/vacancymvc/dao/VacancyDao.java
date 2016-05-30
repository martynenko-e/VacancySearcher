package ua.martynenko.vacancymvc.dao;

/**
 * Created by Martynenko on 22.04.2016.
 */
import java.util.List;

import ua.martynenko.vacancymvc.model.Vacancy;

public interface VacancyDao {

    Vacancy findById(int id);

    void saveVacancy(Vacancy vacancy);

    void deleteVacancyByLink(String link);

    List<Vacancy> findAllVacancies();

    Vacancy findVacancyByLink(String link);

    List<Vacancy> findVacanciesByCompany(int company_id);

}
