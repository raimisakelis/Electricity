package lt.bta.java.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Klase aprasanti Work objekta.
 */
@Entity
@Table(name = "works", schema = "electricity")
public class Work implements Serializable {
    private int id;
    private int authorId;
    private int yearOfWork;
    private String contentOfTheWork;
    private Author author;

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
    @Column(name = "author_id", nullable = false)
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Basic
    @Column(name = "year_of_work")
    public int getYearOfWork() {
        return yearOfWork;
    }

    public void setYearOfWork(int yearOfWork) {
        this.yearOfWork = yearOfWork;
    }

    @Basic
    @Column(name = "content_of_the_work")
    public String getContentOfTheWork() {
        return contentOfTheWork;
    }

    public void setContentOfTheWork(String contentOfTheWork) {
        this.contentOfTheWork = contentOfTheWork;
    }

    /**
     * Metodas, skirtas nustatyti objekto unikaluma.
     * Ivertinimas atliekamas, lyginant unikalu Work
     * objekto id lauka.
     * @param o Work tipo objektas
     * @return true arba false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Work work = (Work) o;
        return id == work.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @JsonIgnoreProperties("works")
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
