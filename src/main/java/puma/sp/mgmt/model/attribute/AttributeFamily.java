package puma.sp.mgmt.model.attribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import puma.sp.mgmt.model.organization.Organization;

/**
 *
 * @author jasper
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "AttributeFamily.all", query = "SELECT a FROM AttributeFamily a"),
	@NamedQuery(name = "AttributeFamily.byId", query = "SELECT a FROM AttributeFamily a WHERE a.id = :id"),
	@NamedQuery(name = "AttributeFamily.byName", query = "SELECT a FROM AttributeFamily a WHERE a.name = :name"),
	@NamedQuery(name = "AttributeFamily.byOrganization", query = "SELECT a FROM AttributeFamily a WHERE a.definedBy = :organization")	
	})
@Table(name = "SP_ATTRTYPE")
public class AttributeFamily {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String name;
    
    @Column
    private String xacmlIdentifier;
	
    @Enumerated(EnumType.STRING)
	private Multiplicity multiplicity;
    
    @Enumerated(EnumType.STRING)
    private RetrievalStrategy retrievalStrategy;

    @Enumerated(EnumType.STRING)
    private DataType dataType;
    
    @ManyToOne
    private Organization definedBy;

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
    
    public Multiplicity getMultiplicity() {
    	return this.multiplicity;
    }
    
    public void setMultiplicity(Multiplicity multiplicity) {
    	this.multiplicity = multiplicity;
    }
    
    public RetrievalStrategy getRetrievalStrategy() {
    	return this.retrievalStrategy;
    }
    
    public void setRetrievalStrategy(RetrievalStrategy strategy) {
    	this.retrievalStrategy = strategy;
    }

    public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

    public Organization getDefinedBy() {
		return definedBy;
	}

	public void setDefinedBy(Organization definedBy) {
		this.definedBy = definedBy;
	}
	
	public String getXacmlIdentifier() {
		return this.xacmlIdentifier;
	}
	
	public void setXacmlIdentifier(String other) {
		this.xacmlIdentifier = other;
	}
	
	public AttributeFamily() {
		
	}
	
	public AttributeFamily(String name, Multiplicity multiplicity, DataType dataType, Organization definedBy) {
		this.name = name;
		this.multiplicity = multiplicity;
		this.dataType = dataType;
		this.definedBy = definedBy;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AttributeFamily)) {
            return false;
        }
        AttributeFamily other = (AttributeFamily) object;
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