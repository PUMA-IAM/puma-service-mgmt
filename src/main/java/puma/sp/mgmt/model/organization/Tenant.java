package puma.sp.mgmt.model.organization;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import puma.sp.mgmt.model.application.Service;
import puma.sp.mgmt.model.user.User;

/**
 *
 * @author jasper
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Tenant.all", query = "SELECT t FROM Tenant t"),
	@NamedQuery(name = "Tenant.byId", query = "SELECT t FROM Tenant t WHERE t.id = :id"),
	@NamedQuery(name = "Tenant.byName", query = "SELECT t FROM Tenant t WHERE t.name = :name"),
	@NamedQuery(name = "Tenant.subtenants", query = "SELECT t FROM Tenant t WHERE t.superTenant = :tenant")
	})
public class Tenant {
	// TODO Subclass properly and efficiently to Organization. Which method is best used? (Single Table, Join-per-subclass, Table-per-class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(unique=true)
	private String name;
    private String authnRequestEndpoint;
    private String attrRequestEndpoint;
    private String authzRequestEndpoint;
	private String imageName;
	private Boolean locallyManaged; // For Federated, hierarchical authentication use IdP Proxying (https://spaces.internet2.edu/display/GS/SAMLIdPProxy)
    private String identityProviderPublicKey;
    @ManyToMany(targetEntity = Service.class) 
    private List<Service> subscribedTo;
    @ManyToOne
    private Tenant superTenant;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<puma.sp.mgmt.model.user.User> users;
	
	public Tenant() {
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
        
    public List<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(List<User> users) {
        this.users = users;
    }
	
	public String getAuthnRequestEndpoint() {
		return this.authnRequestEndpoint;
	}
	
	public void setAuthnRequestEndpoint(String redirect) {
		this.authnRequestEndpoint = redirect;
	}
        
    public String getAttrRequestEndpoint() {
        return this.attrRequestEndpoint;
    }
    
    public void setAttrRequestEndpoint(String endpoint) {
        this.attrRequestEndpoint = endpoint;
    }
    
    public String getAuthzRequestEndpoint() {
    	return this.authzRequestEndpoint;
    }
    
    public void setAuthzRequestEndpoint(String endpoint) {
    	this.authzRequestEndpoint = endpoint;
    }

	public Boolean isLocallyManaged() {
		return this.locallyManaged.booleanValue();
	}
	
	public void setLocallyManaged(Boolean mgmt) {
		this.locallyManaged = mgmt;
	}
	
	public Boolean hasFederatedAuthorization() {
		return this.getAuthzRequestEndpoint() != null;
	}

	public String getImageName() {
		return this.imageName;
	}
	
	public void setImageName(String name) {
            this.imageName = name;
	}
        
    public String getIdentityProviderPublicKey() {
        return this.identityProviderPublicKey;
    }
    
    public void setIdentityProviderPublicKey(String key) {
        this.identityProviderPublicKey = key;
    }
    
    public List<Service> getSubscribedTo() {
        return this.subscribedTo;
    }
    
    public void setSubscribedTo(List<Service> services) {
        this.subscribedTo = services;
    }
    
    /*
    public Boolean isSubscribedToApplication(Application app) {
    	for (Service service: this.getSubscribedTo())
    		if (app.getServices().contains(service))
    			return true;
    	return false;
    }
    */
    
    public Tenant getSuperTenant() {
        return this.superTenant;
    }
    
    public void setSuperTenant(Tenant tenant) {
    	if (tenant.hasAncestor(this))
    		throw new RuntimeException("Could not set new supertenant, already amongst ancestors");
        this.superTenant = tenant;
    }
    
    public Boolean hasAncestor(Tenant other) {
    	Tenant check = this.getSuperTenant();
    	while (check != null) {
    		if (check.equals(other))
    			return true;
    		check = check.getSuperTenant();
    	}
    	return false;
    }
    
    public String toHierarchy() {
    	String result = this.getName();
        Tenant current = this.getSuperTenant();
        while (current != null) {
            result = result + "." + current.getName();
            current = current.getSuperTenant();
        }
        return result;
    }
    
    @Override
    public boolean equals(Object o) {
        Tenant other;
        if (o instanceof Tenant) {
            other = (Tenant) o;
            return this.getId() == other.getId();
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
 }
