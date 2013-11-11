package puma.sp.mgmt.model.organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Model class for an organization. Examples of organizations modeled by the PUMA service are the application providers, 
 * their tenants, or any other organization for which the PUMA service might want to reason on access control about 
 * @author Jasper Bogaerts
 *
 */
@Entity
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(unique=true)
	private String name;
    
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
}
