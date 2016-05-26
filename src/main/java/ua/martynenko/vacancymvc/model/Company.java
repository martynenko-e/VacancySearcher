package ua.martynenko.vacancymvc.model;

/**
 * Created by Martynenko on 22.04.2016.
 */

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Table(name="COMPANY")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMPANY_ID")
    private int id;

    @Size(min=3, max=100)
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "LOGO", nullable = true)
    private String logo;

    @Column(name = "OFFICES", nullable = true)
    private String offices;

    @Column(name = "URL", nullable = false)
    private String url;

    @Column(name = "SHOWONMAIN", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean showOnMain;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getOffices() {
        return offices;
    }

    public void setOffices(String offices) {
        this.offices = offices;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getShowOnMain() {
        return showOnMain;
    }

    public void setShowOnMain(Boolean showOnMain) {
        this.showOnMain = showOnMain;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + url.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Company))
            return false;
        Company other = (Company) obj;
        if (id != other.id)
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                ", offices='" + offices + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
