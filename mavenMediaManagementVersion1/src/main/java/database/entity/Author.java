
package database.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "Author")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a"),
    @NamedQuery(name = "Author.findByAuthorId", query = "SELECT a FROM Author a WHERE a.authorId = :authorId"),
    @NamedQuery(name = "Author.findByName", query = "SELECT a FROM Author a WHERE a.name = :name")})
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "author_id")
    private Integer authorId;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "background")
    private String background;
    @JoinTable(name = "Book_has_Author", joinColumns = {
        @JoinColumn(name = "Author_author_id", referencedColumnName = "author_id")}, inverseJoinColumns = {
        @JoinColumn(name = "Book_UPC", referencedColumnName = "UPC")})
    @ManyToMany
    private Collection<Book> bookCollection;

    public Author() {
    }

    public Author(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    @XmlTransient
    public Collection<Book> getBookCollection() {
        return bookCollection;
    }

    public void setBookCollection(Collection<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authorId != null ? authorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Author)) {
            return false;
        }
        Author other = (Author) object;
        if ((this.authorId == null && other.authorId != null) || (this.authorId != null && !this.authorId.equals(other.authorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.entity.Author[ authorId=" + authorId + " ]";
    }

}
