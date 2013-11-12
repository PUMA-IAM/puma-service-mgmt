package puma.sp.mgmt.model.attribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jasper
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "AttributeType.all", query = "SELECT a FROM AttributeType a"),
	@NamedQuery(name = "AttributeType.byId", query = "SELECT a FROM AttributeType a WHERE a.id = :id"),
	@NamedQuery(name = "AttributeType.byName", query = "SELECT a FROM AttributeType a WHERE a.name = :name")
	})
@Table(name = "SP_ATTRTYPE")
public class AttributeType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String name;
	@Enumerated(EnumType.STRING)
	private TypeFamily family;

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
    
    public TypeFamily getFamily() {
    	return this.family;
    }
    
    public void setFamily(TypeFamily family) {
    	this.family = family;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AttributeType)) {
            return false;
        }
        AttributeType other = (AttributeType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.AttributeType[ id=" + id + " ]";
    }
    
}