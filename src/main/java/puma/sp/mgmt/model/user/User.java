package puma.sp.mgmt.model.user;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import puma.sp.mgmt.model.attribute.Attribute;
import puma.sp.mgmt.model.organization.Tenant;
import puma.util.PasswordHasher;

/**
 * A user in 
 * @author jasper
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "User.all", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.byId", query = "SELECT u FROM User u WHERE u.id = :id"),
	@NamedQuery(name = "User.byTenant", query = "SELECT u FROM User u WHERE u.organization = :tenant"),	
	@NamedQuery(name = "User.byNameTenant", query = "SELECT u FROM User u WHERE u.loginName = :name AND u.organization = :tenant"),	
	@NamedQuery(name = "User.byNameTenantNULL", query = "SELECT u FROM User u WHERE u.loginName = :name AND u.organization IS NULL")
	})
@Table(name = "APPLICATION_USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String loginName;
	private byte[] passwordHash;
	private byte[] passwordSalt;	
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Attribute> attributes;
	@ManyToOne
    private Tenant organization;
        
	public User() {}
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLoginName() {
		return this.loginName;
	}
	
	public void setLoginName(String name) {
		this.loginName = name;
	}
	
	public void setPassword(String password) {
    	byte[] salt = PasswordHasher.generateSalt();
    	this.setPasswordSalt(salt);
    	this.setPasswordHash(PasswordHasher.getHashValue(password, salt));		
	}
	
	public byte[] getPasswordHash() {
		return this.passwordHash;
	}
	
	public void setPasswordHash(byte[] hash) {
		this.passwordHash = hash;
	}
	
	public byte[] getPasswordSalt() {
		return this.passwordSalt;
	}
	
	public void setPasswordSalt(byte[] salt) {
		this.passwordSalt = salt;
	}
        
    public Set<Attribute> getAttributes() {
        return this.attributes;
    }
    
    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }
    
    public void setOrganization(Tenant t) {
        this.organization = t;
        if (t != null && !t.getUsers().contains(this)) {
            t.getUsers().add(this);
        }
    }
    
    public Tenant getOrganization() {
        return this.organization;
    }
}