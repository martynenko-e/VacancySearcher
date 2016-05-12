package ua.martynenko.vacancymvc.model;

/**
 * Created by Martynenko on 22.04.2016.
 */
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;


@Entity
@Table(name="VACANCY")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VACANCY_ID", nullable = false)
    private int id;

    @Size(min=3, max=100)
    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "TYPE", nullable = true)
    private String type;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Type(type="date")
    @Column(name = "DATE", nullable = true)
    private Date date;

    @Column(name = "SALARY", nullable = true)
    private float salary;

    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    @Column(name = "KEYWORD", nullable = true)
    private String keyWord;

    @Column(name = "LINK", unique=true, nullable = false)
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID", nullable = false)
    private Company company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + link.hashCode();
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacancy vacancy = (Vacancy) o;

        return title.equals(vacancy.title) && link.equals(vacancy.link) && company.equals(vacancy.company);

    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", salary=" + salary +
                ", description='" + description + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", link='" + link + '\'' +
                ", company=" + company +
                '}';
    }
}
