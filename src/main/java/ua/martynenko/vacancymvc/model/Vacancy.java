package ua.martynenko.vacancymvc.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


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

    @Column(name = "LOCATION", nullable = true)
    private String location;

    @Column(name = "PROJECT_DESCRIPTION", nullable = true, columnDefinition = "TEXT")
    private String project_description;

    @Column(name = "RESPONSIBILITIES", nullable = true, columnDefinition = "TEXT")
    private String responsibilities;

    @Column(name = "SKILLS_REQUIRED", nullable = true, columnDefinition = "TEXT")
    private String skills_required;

    @Column(name = "LINK", unique=true, nullable = false)
    private String link;

    @Column(name = "DATE", nullable = true)
    private Date date;

    @Column(name = "ACTIVE", nullable = true,  columnDefinition = "TINYINT(1)")
    private Boolean active;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getSkills_required() {
        return skills_required;
    }

    public void setSkills_required(String skills_required) {
        this.skills_required = skills_required;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
                ", location='" + location + '\'' +
                ", project_description='" + project_description + '\'' +
                ", responsibilities='" + responsibilities + '\'' +
                ", skills_required='" + skills_required + '\'' +
                ", link='" + link + '\'' +
                ", date=" + date +
                ", company=" + company +
                '}';
    }
}
