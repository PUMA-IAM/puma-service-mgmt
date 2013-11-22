package puma.sp.mgmt.model.application;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import puma.sp.mgmt.model.attribute.AttributeFamily;

/**
 *
 * @author jasper
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Service.byId", query = "SELECT s FROM Service s WHERE s.id = :id"),
	@NamedQuery(name = "Service.byName", query = "SELECT s FROM Service s WHERE s.name = :name")
	})
@Table(name = "SP_SERVICE")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<puma.sp.mgmt.model.attribute.AttributeFamily> attributes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String theName) {
        this.name = theName;
    }
    
    public Set<AttributeFamily> getAttributes() {
        return this.attributes;
    }
    
    public void setAttributes(Set<AttributeFamily> theList) {
        this.attributes = theList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Service[ id=" + id + " ]";
    }
    
}