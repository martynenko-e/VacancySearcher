package ua.martynenko.vacancymvc.dao;

import java.util.List;

import ua.martynenko.vacancymvc.model.Vacancy;

public interface VacancyDao {

    Vacancy findById(int id);

    void saveVacancy(Vacancy vacancy);

    void deleteVacancyByLink(String link);

    List<Vacancy> findAllVacancies();

    Vacancy findVacancyByLink(String link);

    Vacancy findVacancyByLink(String link, String title);

    List<Vacancy> findVacanciesByCompany(int company_id);

}
