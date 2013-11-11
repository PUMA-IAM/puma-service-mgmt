package puma.sp.mgmt.model.policy;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import puma.sp.mgmt.model.organization.Tenant;

@Entity
@Inheritance
@DiscriminatorColumn(name = "TYPE")
public abstract class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String content;
	@Enumerated(EnumType.STRING)
	private PolicyType type;
	@ManyToOne
	private Tenant definingOrganization;		// Should be of type Organization, which has subclasses [Provider?] Application Provider, Middleware Provider and Tenant.
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setDefiningOrganization(Tenant organization) {
		this.definingOrganization = organization;
	}
	
	public Tenant getDefiningOrganization() {
		return this.definingOrganization;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public String toXACML() {
		// Currently, we suppose the policy is stored as a XACML representation
		return this.getContent();
	}
	
	public void setType(PolicyType type) {
		this.type = type;
	}
	
	public PolicyType getType() {
		return this.type;
	}
	
}
