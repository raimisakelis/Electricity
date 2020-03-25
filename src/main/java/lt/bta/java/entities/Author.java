package lt.bta.java.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * Klase aprasanti Author objekta.
 */
@Entity
@Table(name = "authors", schema = "electricity")
//@IdClass(lt.bta.java.entities.AuthorPK.class)
public class Author implements Serializable {
    private int id;
    private String name;
    private String surname;
    private Collection<Work> works;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Metodas, skirtas nustatyti objekto unikaluma.
     * Ivertinimas atliekamas, lyginant unikalu Author
     * objekto id lauka.
     * @param o Author tipo objektas
     * @return true arba false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @OneToMany(mappedBy = "author")
    public Collection<Work> getWorks() {
        return works;
    }

    public void setWorks(Collection<Work> works) {
        this.works = works;
    }
}
