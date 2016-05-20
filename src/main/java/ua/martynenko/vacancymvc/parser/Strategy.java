package ua.martynenko.vacancymvc.parser;

import ua.martynenko.vacancymvc.model.Vacancy;

import java.util.List;

/**
 * Created by Martynenko on 21.01.16.
 */
public interface Strategy {
    List<Vacancy> getVacancies();
}
