package ua.martynenko.vacancymvc.service;

/**
 * Created by Martynenko on 22.04.2016.
 */
import java.util.List;

import ua.martynenko.vacancymvc.model.Vacancy;

public interface VacancyService {

    Vacancy findById(int id);

    void saveVacancy(Vacancy vacancy);

    void updateVacancy(Vacancy vacancy);

    void deleteVacancyByLink(String link);

    List<Vacancy> findAllVacancies();

    List<Vacancy> findVacanciesByCompany(int company_id);

    Vacancy findVacancyByLink(String link);

    Vacancy findVacancyByLink(String link, String title);

    boolean isVacancyLinkUnique(Integer id, String link);

}
