package ua.martynenko.vacancymvc.parser;

import ua.martynenko.vacancymvc.model.Vacancy;

import java.util.List;

public interface Strategy {
    List<Vacancy> getVacancies();
}
