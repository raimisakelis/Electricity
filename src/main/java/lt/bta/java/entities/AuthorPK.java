package lt.bta.java.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AuthorPK implements Serializable {

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Id
    @Column(name = "surname", nullable = false)
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public AuthorPK(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorPK authorPK = (AuthorPK) o;
        return Objects.equals(name, authorPK.name) &&
                Objects.equals(surname, authorPK.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
